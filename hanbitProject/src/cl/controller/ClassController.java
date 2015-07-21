package cl.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cl.model.BuyDelete;
import cl.model.BuyListModel;
import cl.model.BuyRequest;
import cl.model.ClArticleListModel;
import cl.model.ClwriteArticle;
import cl.service.BuyDeleteService;
import cl.service.BuyListService;
import cl.service.BuyService;
import cl.service.ClNotFoundException;
import cl.service.DeleteService;
import cl.service.ListService;
import cl.service.ReadService;
import cl.service.UpdateService;
import cl.service.WriteService;

@Controller
public class ClassController {
		
	//List Controller
	ListService listService;
		
	public void setListService(ListService listService) {
		this.listService = listService;
	}
	
	@RequestMapping(value = "/cl_list.do")
	public ModelAndView listView(@RequestParam(value = "page", defaultValue = "1") int page, 
								@RequestParam(value = "select", defaultValue ="") String select,
								@RequestParam(value = "search", defaultValue = "3") int search, HttpSession session) {
		
		String pageinfo = "/board/cl_list.do";
		session.setAttribute("sessionPage", pageinfo);
		
		ClArticleListModel alm = listService.getClArticleList(page, select, search);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("cl_list");
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
	ReadService readService;
		
	public void setReadService(ReadService readService) {
		this.readService = readService;
	}
	
	@RequestMapping("/cl_read.do")
	public ModelAndView BoardArticleRead(@RequestParam("cl_num") int cl_num, HttpSession session) throws SQLException {

		ModelAndView mav = new ModelAndView();
		
		/*if(session.getAttribute("sessionId")==null){
			mav.setViewName("login_form");
			return mav;
		}*/
		
		try {
			mav.addObject("article", readService.readClArticle(cl_num));
			mav.addObject("num", readService.selectbuy((String)session.getAttribute("sessionId")));
			mav.addObject("cl", readService.clCheck((String)session.getAttribute("sessionId"), cl_num));
		}
		catch (ClNotFoundException e) {
			e.printStackTrace();
			mav.setViewName("readError");
			return mav;
		}
		mav.setViewName("cl_read");
		return mav;
	}	
	
	
	//Write Controller
	WriteService writeService;
	
	public void setWriteService(WriteService writeService) {
		this.writeService = writeService;
	}
	
	@RequestMapping(value = "/cl_write.do", method = RequestMethod.GET)
	public ModelAndView writeForm() throws SQLException {
		ModelAndView mav = new ModelAndView();
		mav.addObject("teacher", writeService.teacherRead());
		mav.setViewName("cl_writeForm");
		return mav;
	}
	
	@RequestMapping(value="/cl_write.do", method = RequestMethod.POST)
	public ModelAndView classwrite(ClwriteArticle clwriteArticle) throws SQLException {
		ModelAndView mav = new ModelAndView();
		writeService.write(clwriteArticle);
		mav.setViewName("cl_writeSuccess");
		return mav;
	}
	
	//Delete Controller
	DeleteService deleteService;
	
	public void setDeleteService(DeleteService deleteService) {
		this.deleteService = deleteService;
	}
	
	@RequestMapping("/cl_delete.do")
	public ModelAndView delete(@RequestParam(value = "cl_num") int cl_num) throws SQLException {
		ModelAndView mav = new ModelAndView();
		deleteService.delete(cl_num);
		mav.setViewName("cl_deleteSuccess");
		return mav;
	}
	
	//Update Controller
	UpdateService updateService;
	
	public void setUpdateService(UpdateService updateService) {
		this.updateService = updateService;
	}
	
	@RequestMapping(value = "/cl_update.do", method = RequestMethod.GET)
	public ModelAndView updateForm(@RequestParam(value = "cl_num") int cl_num) throws SQLException {
		ModelAndView mav = new ModelAndView();
		try {
			mav.addObject("teacher", writeService.teacherRead());
			mav.addObject("article", updateService.readClwriteArticle(cl_num));
		}
		catch (ClNotFoundException e) {
			e.printStackTrace();
			mav.setViewName("readError");
			return mav;
		}
		mav.setViewName("cl_updateForm");
		return mav;
	}

	@RequestMapping(value = "/cl_update.do", method = RequestMethod.POST)
	public ModelAndView update(ClwriteArticle clwriteArticle) throws SQLException {
		ModelAndView mav = new ModelAndView();
		System.out.println(clwriteArticle.getCl_name());
		updateService.update(clwriteArticle);
		mav.setViewName("cl_updateSuccess");
		return mav;
	}
	
	
	//buyController
	BuyService buyService;
	
	public void setBuyService(BuyService buyService) {
		this.buyService = buyService;
	}
	
	@RequestMapping("/cl_buy.do")
	public ModelAndView buy (BuyRequest buyRequest, HttpSession session) throws SQLException {
		ModelAndView mav = new ModelAndView();
		buyService.buy(buyRequest, (String)session.getAttribute("sessionId"));
		mav.setViewName("buySuccess");
		return mav;
	}
	
	BuyListService buyListService;
	
	public void setBuyListService(BuyListService buyListService) {
		this.buyListService = buyListService;
	}
	
	@RequestMapping(value = "/cl_buy_list.do")
	public ModelAndView buyList(@RequestParam(value = "page", defaultValue = "1") int page, 
								@RequestParam(value = "select", defaultValue ="") String select,
								@RequestParam(value = "search", defaultValue = "3") int search ){
		
		BuyListModel alm = buyListService.getBuyList(page, select, search);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("buyList");
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
	
	BuyDeleteService buyDelteService;
	
	public void setBuyDeleteService(BuyDeleteService buyDelteService) {
		this.buyDelteService = buyDelteService;
	}
	
	@RequestMapping(value="/buy_delete.do")
	public ModelAndView buydelete(BuyDelete buyDelete, HttpSession session) throws SQLException {
		
		ModelAndView mav = new ModelAndView();
		
		if(session.getAttribute("sessionId")==null){
			mav.setViewName("login_form");
			return mav;
		}
		
		buyDelteService.delete(buyDelete);
		
		mav.setViewName("buyDelete");
		return mav;
	}
}
