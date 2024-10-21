package com.kh.lclinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.kh.lclinic.model.vo.User;


import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class APIController {
	
	@RequestMapping("/login/oauth/google")
	public String googleLoginCallback(@RequestParam(value="error", defaultValue="") String error
									, @RequestParam(value="code", defaultValue="") String code
									, @RequestParam(value="scope", defaultValue="") String scope
									, HttpSession session) {
		log.info("error : {}", error);
		log.info("code : {}", code);
		log.info("scope : {}", scope);
		
		if (code != null && !code.isEmpty()) {		
			User user = new User();
			user.setId("로그인한 구글 아이디");
			
			// 인증된 사용자의 정보를 추가로 조회하여 DB 저장
			// 로그인 처리
			
			session.setAttribute("user", user);
		}
		
		return "redirect:/";
	}


}
