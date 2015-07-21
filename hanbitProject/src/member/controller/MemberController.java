package member.controller;

import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.CheckRequest;
import member.model.LoginRequest;
import member.model.MemberInfo;
import member.model.MemberListModel;
import member.model.UpdateMemberRequest;
import member.service.CheckMemberService;
import member.service.DeleteMemberService;
import member.service.InvalidPasswordException;
import member.service.JoinMemberService;
import member.service.ListMemberService;
import member.service.LoginException;
import member.service.LoginMemberService;
import member.service.MemberNotFoundException;
import member.service.ReadMemberService;
import member.service.UpdateMemberService;
import member.validator.CheckInfoValidator;
import member.validator.LoginCommandValidator;
import member.validator.MemberInfoValidator;
import member.validator.Up_Del_Validator;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cl.service.ClNotFoundException;

@Controller
public class MemberController {

	JoinMemberService joinMemberService;
	LoginMemberService loginMemberService;
	ReadMemberService readMemberService;
	ListMemberService listMemberService;
	UpdateMemberService updateMemberService;
	CheckMemberService checkMemberService;
	DeleteMemberService deleteMemberService;

	public void setJoinMemberService(JoinMemberService joinMemberService) {
		this.joinMemberService = joinMemberService;
	}

	public void setLoginMemberService(LoginMemberService loginMemberService) {
		this.loginMemberService = loginMemberService;
	}

	public void setReadMemberService(ReadMemberService readMemberService) {
		this.readMemberService = readMemberService;
	}

	public void setListMemberService(ListMemberService listMemberService) {
		this.listMemberService = listMemberService;
	}

	public void setUpdateMemberService(UpdateMemberService updateMemberService) {
		this.updateMemberService = updateMemberService;
	}

	public void setCheckMemberService(CheckMemberService checkMemberService) {
		this.checkMemberService = checkMemberService;
	}

	public void setDeleteMemberService(DeleteMemberService deleteMemberService) {
		this.deleteMemberService = deleteMemberService;
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////

	// join controller
	@RequestMapping(value = "/join.do", method = RequestMethod.GET)
	public String joinForm() {
		return "join_form";
	}

	@ModelAttribute("join_command")
	public MemberInfo formBacking1() { // 정보를 입력하고 로그인을 클릭했을때 정보를 보여주는 메서드
		return new MemberInfo();
	}

	@RequestMapping(value = "/join.do", method = RequestMethod.POST)
	public ModelAndView join(@ModelAttribute("join_command") MemberInfo memberInfo, BindingResult result, HttpSession session) {

		ModelAndView mav = new ModelAndView();
		
		if(session.getAttribute("sessionId")!=null){
			mav.setViewName("join_not");
			return mav;
		}

		try {

			// validate
			new MemberInfoValidator().validate(memberInfo, result); // 유효성 검사 실행
			if (result.hasErrors()) {
				mav.setViewName("join_form"); // 에러 발생시에 다시 form으로 되돌리기
				return mav;
			}
			
			// 유효성 검사가 완료되었으면 회원가입 메서드 실행
			memberInfo = joinMemberService.join(memberInfo);
			mav.setViewName("join_success"); // 에러가 발생하지 않고 가입 메서드가 실행되면
			
			// "join_success" 페이지로 이동
			mav.addObject("st_id", memberInfo.getSt_id());
			return mav;

		} catch (SQLException e) {
			mav.setViewName("join_error");
			return mav;
		} // 회원가입 메서드 실행

	}

	// IdCheck
	@RequestMapping("/idCheck.do")
	public ModelAndView idCheck(@RequestParam("st_id") String st_id){
		System.out.println("id:"+st_id);
		
		ModelAndView mav=new ModelAndView();
		int checkCount = joinMemberService.IdCheck(st_id);
		
		
		mav.addObject("checkCount",checkCount);
		mav.addObject("st_id", st_id);
			//결과페이지는 viewName으로 지정
			mav.setViewName("id_check");
			return mav;
		}
	
	
	//ajaxIdCheck
	@RequestMapping("/ajaxIdCheck.do")
	public ModelAndView execute_ajax(@RequestParam("st_id")String st_id,
			HttpServletResponse response){	
		
		int id_1=joinMemberService.IdCheck(st_id);
		//id_1이 널인경우 중복되지 않음!!
		//뷰페이지로 이동시 어떤값을 가지고 이동?
		boolean ans = true;//중복
		if(id_1 == 0)
			ans=false; //중복 안됨
			response.setContentType("text/text;charset=euc-kr");
		try{
		PrintWriter out=response.getWriter();
		  if(ans){
			  out.write("1"); //중복되는 경우 
		  }else{
			  out.write("0"); //중복되지 않는 경우
		  }
		}catch(Exception ex){}
		return null;
	}
		
	// main controller
	/*
	 * @RequestMapping("/main.do") public ModelAndView main(HttpSession
	 * session){ ModelAndView mav = new ModelAndView();
	 * mav.addObject("sessionName", session.getAttribute("st_name")); return
	 * mav; }
	 */

	// login controller
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String loginForm() {
		return "login_form";
	}

	@ModelAttribute("login_command")
	public LoginRequest formBacking2() {// 정보를 입력하고 로그인을 클릭했을때 정보를 보여주는 메서드
		return new LoginRequest();
	}

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("login_command") LoginRequest loginRequest, BindingResult result, HttpSession session) {

		ModelAndView mav = new ModelAndView();

		// ** 만일 필요시 session,request,reponse 를
		// ** 적절히 매개변수로 지정시 얻어쓸수 있음

		try {
			// validate
			new LoginCommandValidator().validate(loginRequest, result);

			if (result.hasErrors()) {
				mav.setViewName("login_form");
				return mav;
			}
			
			// 에러 없으면 login 진행
			MemberInfo checkInfo = loginMemberService.login(loginRequest);

			// session
			session.setAttribute("sessionName", checkInfo.getSt_name());
			session.setAttribute("sessionId", checkInfo.getSt_id());

			mav.setViewName("loginEmpty");
			mav.addObject("checkInfo", checkInfo);
			return mav;
		} catch (SQLException | LoginException e) {
			e.printStackTrace();
			mav.setViewName("login_error");
			return mav;
		}
	}

	@RequestMapping("logout.do")
	public String logOut(HttpSession session) {
		session.invalidate();
		return "logout";
	}

	// id/pw 찾기
	@RequestMapping(value = "/check_id_pw.do", method = RequestMethod.GET)
	public String searchForm() {
		return "search_id_pw_form";
	}

	@ModelAttribute("check_command")
	public MemberInfo formBacking3() {// 정보를 입력하고 로그인을 클릭했을때 정보를 보여주는 메서드
		return new MemberInfo();
	}

	@RequestMapping(value = "/check_id_pw.do", method = RequestMethod.POST)
	public ModelAndView memberView(
			@ModelAttribute("check_command") MemberInfo memberInfo,
			BindingResult result,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "st_tel") String st_tel) {

		ModelAndView mav = new ModelAndView();

		// validate
		new CheckInfoValidator().validate(memberInfo, result);

		if (result.hasErrors()) {
			mav.setViewName("search_id_pw_form");
			return mav;
		}

		MemberListModel memberList = listMemberService.getMemberList(page,
				st_tel, memberInfo);

		mav.setViewName("search_result");
		mav.addObject("list", memberList);
		return mav;

		/*
		 * if (memberList.getTotalPageCount() > 0) { int beginPageNumber =
		 * (memberList.getRequestPage() - 1) / 10 * 10 + 1; int endPageNumber =
		 * beginPageNumber + 9; if (endPageNumber >
		 * memberList.getTotalPageCount()) { endPageNumber =
		 * memberList.getTotalPageCount(); } mav.addObject("beginPage",
		 * beginPageNumber); mav.addObject("endPage", endPageNumber); }
		 */

	}

	// 회원탈퇴
	@RequestMapping(value = "/deleteMember.do", method = RequestMethod.GET)
	public ModelAndView MemberDeleteForm(@RequestParam("st_id") String st_id) {

		ModelAndView mav = new ModelAndView();
		mav.addObject("st_id", st_id);
		mav.setViewName("delete_member_form");

		return mav;
	}

	@ModelAttribute("delete_command")
	public CheckRequest formBacking4() {
		return new CheckRequest();
	}

	@RequestMapping(value = "/deleteMember.do", method = RequestMethod.POST)
	public ModelAndView MemberDelete(
			@ModelAttribute("delete_command") CheckRequest checkRequest,
			BindingResult result, HttpSession session) {

		ModelAndView mav = new ModelAndView();
		
		if(session.getAttribute("sessionId")==null){
			mav.setViewName("login_form");
			return mav;
		}

		try {
			// validate
			new Up_Del_Validator().validate(checkRequest, result);

			if (result.hasErrors()) {
				mav.addObject("st_id", checkRequest.getSt_id());
				mav.setViewName("delete_member_form");
				return mav;
			}

			deleteMemberService.deleteMember(checkRequest);
			session.invalidate();
			mav.setViewName("delete_member_success");
			return mav;
		} catch (MemberNotFoundException | InvalidPasswordException e) {
			e.printStackTrace();

			mav.setViewName("delete_member_error");
			return mav;
		}

	}

	// 비밀번호 확인
	@RequestMapping(value = "/check.do", method = RequestMethod.GET)
	public ModelAndView memberCheckForm() {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("confirm_member_edit");

		return mav;
	}

	@ModelAttribute("checkPw_command")
	public CheckRequest formBacking5() {// 정보를 입력하고 로그인을 클릭했을때 정보를 보여주는 메서드
		return new CheckRequest();
	}

	@RequestMapping(value = "/check.do", method = RequestMethod.POST)
	public ModelAndView memberCheck(
			@ModelAttribute("checkPw_command") CheckRequest checkRequest,
			BindingResult result,
			@RequestParam("st_id") String st_id) {

		ModelAndView mav = new ModelAndView();

		try {

			// validate
			new Up_Del_Validator().validate(checkRequest, result);

			if (result.hasErrors()) {
				mav.addObject("checkId", checkRequest.getSt_id());
				mav.setViewName("confirm_member_edit");
				return mav;
			}

			checkMemberService.checkMemeber(checkRequest);

			mav.setViewName("edit_member_form");
			mav.addObject("st_id", st_id);
			mav.addObject("member",
					readMemberService.readMember(checkRequest.getSt_id()));

			return mav;
		} catch (MemberNotFoundException | InvalidPasswordException
				| SQLException e) {
			e.printStackTrace();
			mav.setViewName("check_member_error");
			return mav;
		}
	}

	// my page
	@RequestMapping("my_page.do")
	public ModelAndView viewMyPage(@RequestParam("st_id") String st_id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("my_page");
		try {
			mav.addObject("myInfo", readMemberService.readMember(st_id));
			mav.addObject("classInfo", readMemberService.selectClass(st_id));

		} catch (MemberNotFoundException | ClNotFoundException e) {
			e.printStackTrace();
			mav.setViewName("check_member_error");
			return mav;
		}
		return mav;
	}

	// 회원정보 수정

	/*@ModelAttribute("update_command")
	public UpdateMemberRequest formBacking6() {
		return new UpdateMemberRequest();
	}*/

	@RequestMapping("/edit.do")
	public ModelAndView MemberEdit(UpdateMemberRequest updateMemberRequest) {

		ModelAndView mav = new ModelAndView();

		try {

			// validator
			/*new UpdateValidator().validate(updateMemberRequest, result);

			if (result.hasErrors()) {
				mav.setViewName("edit_member_form");
				return mav;
			}*/

			updateMemberService.update(updateMemberRequest);
			mav.setViewName("edit_member_success");
			return mav;
		} catch (InvalidPasswordException | MemberNotFoundException e) {
			e.printStackTrace();
			mav.addObject("error", e);
			mav.setViewName("edit_member_error");
			return mav;
		}
	}
}
