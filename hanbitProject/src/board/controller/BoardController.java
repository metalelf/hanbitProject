package board.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import board.model.Article;
import board.model.ArticleListModel;
import board.model.TestDAO;
import board.service.ArticleNotFoundException;
import board.service.CannotReplyArticleException;
import board.service.DeleteArticleService;
import board.service.DeleteRequest;
import board.service.IdGenerationFailedException;
import board.service.InvalidPasswordException;
import board.service.LastChildAleadyExistsException;
import board.service.ListArticleService;
import board.service.ReadArticleService;
import board.service.ReplyArticleService;
import board.service.ReplyingRequest;
import board.service.UpdateArticleService;
import board.service.UpdateRequest;
import board.service.WriteArticleService;
import board.service.WritingRequest;

@Controller
public class BoardController {
	
	private TestDAO testDAO;
	
	public TestDAO getTestDAO(){
		return testDAO;
	}
	
	public void setTestDAO(TestDAO testDAO){
		this.testDAO = testDAO;
	}
	
	//List Controller
	ListArticleService listArticleService;
	
	public void setListArticleService(ListArticleService listArticleService) {
		this.listArticleService = listArticleService;
	}
	
	@RequestMapping(value = "/main.do")
	public String main(HttpSession session){
		
		String pageinfo = "/board/main.do";
		session.setAttribute("sessionPage", pageinfo);
		return "main";
	}
	
	@RequestMapping(value = "/list.do")
	public ModelAndView listView(@RequestParam(value = "page", defaultValue = "1") int page, 
								@RequestParam(value = "select", defaultValue ="") String select,
								@RequestParam(value = "search", defaultValue = "3") int search, HttpSession session) throws UnsupportedEncodingException {
		
		String pageinfo = "/board/list.do";
		session.setAttribute("sessionPage", pageinfo);
		
		select = URLEncoder.encode(select, "UTF-8");
		ArticleListModel alm = listArticleService.getArticleList(page, select, search);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("list");
		mav.addObject("list", alm);

		if (alm.getTotalPageCount() > 0) {
			int beginPageNumber = (alm.getRequestPage() - 1) / 10 * 10 + 1;
			int endPageNumber = beginPageNumber + 9;
			
			if (endPageNumber > alm.getTotalPageCount()) {
				endPageNumber = alm.getTotalPageCount();
			}
			mav.addObject("beginPage", beginPageNumber);
			mav.addObject("endPage", endPageNumber);
		}
		
		return mav;
	}
	
	//Read Controller
	/*ReadArticleService readArticleService;
	
	public void setReadArticleService(ReadArticleService readArticleService) {
		this.readArticleService = readArticleService;
	}*/
	
	@RequestMapping("/read.do")
	public ModelAndView BoardArticleRead(@RequestParam("articleId") int articleId,
										@RequestParam(value = "page", defaultValue = "1")int page, HttpSession session) {
				
		ModelAndView mav = new ModelAndView();
		
		String pageinfo = "/board/read.do?articleId=" + articleId + "&page=" + page;
		session.setAttribute("sessionPage", pageinfo);
		
		mav.addObject("article", testDAO.read(articleId));
		
		/*if(session.getAttribute("sessionId")==null){
			mav.setViewName("login_form");
			return mav;
		}*/
		
		/*try {
			mav.addObject("article", readArticleService.readArticle(articleId));
			
		}
		catch (ArticleNotFoundException e) {
			e.printStackTrace();
			mav.setViewName("readError");
			return mav;
		}*/
		mav.setViewName("read");
		return mav;
	}
	
	//Write Controller
	WriteArticleService writeArticleService;
	
	public void setWriteArticleService(WriteArticleService writeArticleService) {
		this.writeArticleService = writeArticleService;
	}
	
	@RequestMapping(value = "/write.do", method = RequestMethod.GET)
	public String boardWriteFrom() {
		return "writeForm";
	}

	@RequestMapping(value = "/write.do", method = RequestMethod.POST)
	public ModelAndView boardWrite(@ModelAttribute WritingRequest writingRequest) {
		System.out.println(writingRequest.getTitle());
		ModelAndView mav = new ModelAndView();
		Article article;
		try {
			article = writeArticleService.write(writingRequest);
			mav.setViewName("write");
			mav.addObject("articleId", article.getId());
			return mav;
		} catch (IdGenerationFailedException | SQLException e) {
			e.printStackTrace();
			mav.setViewName("writeError");
			return mav;
		}
	}
	
	//Delete Controller
	DeleteArticleService deleteArticleService;
	
	public void setDeleteArticleService(DeleteArticleService deleteArticleService) {
		this.deleteArticleService = deleteArticleService;
	}
	
	@RequestMapping(value = "/delete.do", method = RequestMethod.GET)
	public ModelAndView BoardArticleDeleteForm(@RequestParam("articleId") int articleId) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("articleId", articleId);
		mav.setViewName("delete_form");
		return mav;
	}

	@RequestMapping(value = "/delete.do", method = RequestMethod.POST)
	public ModelAndView BoardArticleDelete(DeleteRequest deleteRequest) {

		ModelAndView mav = new ModelAndView();
		try {
			deleteArticleService.deleteArticle(deleteRequest);
			mav.setViewName("delete_success");
			return mav;
		} catch (ArticleNotFoundException | InvalidPasswordException e) {
			e.printStackTrace();
			mav.addObject("error", e);
			mav.setViewName("delete_error");
			return mav;
		}
	}
	
	//Update Controller
	UpdateArticleService updateArticleService;
	
	public void setUpdateArticleService(UpdateArticleService updateArticleService){
		this.updateArticleService = updateArticleService;
	}
	
	@RequestMapping(value = "/update.do", method = RequestMethod.GET)
	public ModelAndView BoardArticleEditForm(@RequestParam("articleId") int articleId) {

		ModelAndView mav = new ModelAndView();
		mav.addObject("article", testDAO.read(articleId));
		mav.setViewName("update_form");
		return mav;
		/*try {
			
		} catch (ArticleNotFoundException e) {
			e.printStackTrace();
			mav.setViewName("update_error");
			return mav;
		}*/
	}
	
	@RequestMapping(value = "/update.do", method = RequestMethod.POST)
	public ModelAndView BoardArticleEdit(UpdateRequest updateRequest, @RequestParam("page") int page, @RequestParam("articleId") int articleId) {

		ModelAndView mav = new ModelAndView();
		try {
			updateArticleService.update(updateRequest);
			mav.setViewName("update_success");
			mav.addObject("page", page);
			mav.addObject("articleId", articleId);
			return mav;
		} catch (ArticleNotFoundException | InvalidPasswordException e) {
			e.printStackTrace();
			mav.addObject("error", e);
			mav.addObject("page", page);
			mav.setViewName("update_error");
			return mav;
		}
	}

	//Reply Controller
	ReplyArticleService replyArticleService;
	
	public void setReplyArticleService(ReplyArticleService replyArticleService) {
		this.replyArticleService = replyArticleService;
	}
	
	@RequestMapping(value = "/reply.do", method = RequestMethod.GET)
	public ModelAndView replyFrom(@RequestParam("parentId") int parentId,
			@RequestParam("page") int page) {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("reply_form");
		mav.addObject("parentId", parentId);
		mav.addObject("page", page);

		return mav;
	}

	@RequestMapping(value = "/reply.do", method = RequestMethod.POST)
	public ModelAndView boardReply(@ModelAttribute ReplyingRequest replyingRequest, @RequestParam("page") int page) throws SQLException {

		ModelAndView mav = new ModelAndView();
		Article article;
		try {
			article = replyArticleService.reply(replyingRequest);
			mav.setViewName("reply_success");
			mav.addObject("articleId", article.getId());
			mav.addObject("page", page);
			return mav;
			
		} catch (ArticleNotFoundException | CannotReplyArticleException | LastChildAleadyExistsException e) {
			e.printStackTrace();
			mav.addObject("replyException", e);
			mav.setViewName("reply_error");
			return mav;
		}
	}
	
	//pds
	@RequestMapping("/pds.do")
	public ModelAndView pds(HttpSession session){
		String pageinfo = "/board/pds.do";
		session.setAttribute("sessionPage", pageinfo);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/pds/list");
		return mav;
	}
	
	@RequestMapping("/pds_upload.do")
	public String upload() {
		return "/pds/uploadForm";
	}
	
	@RequestMapping("/uploaded.do")
	public String uploaded() {
		return "/pds/uploaded";
	}
	
	@RequestMapping("/invalid.do")
	public String invalid() {
		return "/pds/invalid";
	}
	
	@RequestMapping("/download.do")
	public String download() {
		return "/pds/download";
	}
	
}
