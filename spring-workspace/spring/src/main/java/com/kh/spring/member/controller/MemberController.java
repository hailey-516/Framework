package com.kh.spring.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.spring.member.model.vo.Member;
import com.kh.spring.member.service.MemberService;

@Controller	// Controller 어노테이션 추가 시 빈 스캐닝을 통해 자동으로 빈 등록이 됨
@RequestMapping("/member")	// 공통적으로 사용될 url 주소
public class MemberController {
	// private MemberService mService = new MemberServiceImpl();
	/*
	 * * 기존 객체 생성 방식 : 객체 간의 결합도가 높아짐 (소스코드 수정 시 사용된 부분을 전부 변경)
	 * 					  서비스가 동시에 많이 사용되었을 경우 그만큼 객체 생성
	 * 
	 * * Spring을 이용한 방식 (DI: Denpendency Injection)
	 *   : 객체를 생성하여 주입해줌
	 *   new 키워드 없이 @Autowired만 사용
	 *   
	 *   - 필드 주입 방식
	 *     : 스프링 컨테이너에서 객체를 생성한 후 @Autowired가 붙은 필드에 의존성을 주입
	 *     
	 *   - 생성자 주입 방식
	 *     : 스프링 컨테이너에서 해당 객체를 생성할 때 생성자를 통해서 필요한 의존성을 주입
	 */
	
	private final MemberService mService;
	
	@Autowired
	public MemberController(MemberService mService) {
		this.mService = mService;
	}
	
	
	/*
	@RequestMapping("/login")	// RequestMapping 어노테이션 추가 시 HandlerMapping 등록
	public void loginMember() {
		System.out.println("로그인 요청 성공!");
	}
	*/
	
	/*
	 * * 요청 시 전달되는 데이터에 대한 처리 방법
	 * 
	 *   1) HttpServletRequest 이용 (기존 servlet 방식)
	 *   	: 해당 메소드의 매개변수로 HttpServletRequest 타입을 작성하면
	 *   	  스프링 컨테이너에서 해당 메소드 호출 시 자동으로 객체를 생성하여 전달해줌
	 */
	/*
	@RequestMapping("/login")
	public String loginMember(HttpServletRequest request) {
		String id = request.getParameter("userId");
		String pwd = request.getParameter("userPwd");
		
		System.out.println("ID ---> " + id);
		System.out.println("PW ---> " + pwd);
		
		return "main";
	}
	*/
	
	/*
	 * 2) RequestParam 어노테이션 이용
	 *    : request.getParameter("키값") : 밸류 <<- 이 작업을 대신해주는 어노테이션
	 */
	/*
	@RequestMapping("/login")
	public String loginMember(@RequestParam(value="userId", defaultValue="xxx") String id, 
							@RequestParam("userPwd") String pwd) {
		System.out.println("id : " + id);
		System.out.println("pwd : " + pwd);
		
		return "main";
	}
	*/
	
	/*
	 * 3) RequestParam 생략
	 *    => 주의! 매개변수명 요청 시 전달되는 키값과 동일하게 작성해야 함!
	 */
	/*
	@RequestMapping("/login")
	public String loginMember(String userId, String userPwd) {
		System.out.println("id : " + userId);
		System.out.println("pwd : " + userPwd);
		
		return "main";
	}
	*/
	
	/*
	 * 4) 커맨드 객체 방식
	 *    : 요청 시 전달되는 데이터를 vo클래스 타입으로 받고자 하는 경우
	 *    
	 *    매개변수 타입을 vo클래스타입으로 작성.
	 *    전달되는 데이터의 키값을 받고자하는 vo클래스의 필드명과 일치하도록 해줘야함
	 *    
	 *    스프링 컨테이너가 해당 vo객체를 기본생성자로 생성 후
	 *    setter 메소드를 사용하여 요청 시 전달 값을 해당 필드에 저장함
	 *    
	 *    *주의* 요청 키값을 필드명과 동일하게 전달해야 함!
	 */
	@RequestMapping("/login")
	public String loginMember(Member m, Model model, HttpSession session) {
		
		System.out.println("id --> " + m.getUserId());
		System.out.println("pwd --> " + m.getUserPwd());
		
		Member loginUser = mService.loginMember(m);
		
		if (loginUser != null) {	// 로그인 성공
			// 세선 영역에 로그인 정보 저장
			session.setAttribute("loginUser", loginUser);
			
			// url 재요청 (메인페이지)
			return "redirect:/";
		} else {		// 로그인 실패
			// request영역에 에러 메시지 저장
			// => Model 객체 : requestScope
			model.addAttribute("errorMsg", "로그인에 실패했습니다.");
			
			// 에러페이지 응답 (포워딩)
			// "/WEB-INF/views/common/errorPage.jsp
			return "common/errorPage";
		}
	}
	
}
