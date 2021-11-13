package com.cos.validation_test.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.validation_test.domain.user.User;
import com.cos.validation_test.domain.user.UserRepository;
import com.cos.validation_test.util.Script;
import com.cos.validation_test.web.dto.CMRespDto;
import com.cos.validation_test.web.dto.JoinReqDto;
import com.cos.validation_test.web.dto.LoginReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class TestController {

	private final UserRepository userRepository;
	private final HttpSession session;


	// 인덱스 페이지로 이동
	@GetMapping("/")
	public String index() {
		return "index";
	}

	// 관리자 페이지로 이동
	@GetMapping("/admin/detailForm")
	public @ResponseBody String adminDetailForm() {
		
		// 공통 로직 시작 ---------------------------------------------
		User principal = (User) session.getAttribute("principal");
		
		// 유효성검사
		// 인증 체크 (공통로직)
		if(principal == null ) { // 로그인 안됨
			return Script.href("/user/loginForm", "관리자만 접근 가능합니다.");
		}
		
		if(principal.getChk() != 2) { // 관리자만 접근 가능
			return Script.href("/user/detail", "관리자만 접근 가능합니다. " + principal.getChk());
		}
		
		// 공통 로직 끝 ---------------------------------------------
		
		return Script.href("/admin/detail", "관리자로 로그인 성공!.");
	}
	
	// 어드민 디테일 페이지로 이동
	@GetMapping("/admin/detail")
	public String detail() {
		return "/admin/detail";
	}

	// 로그인 완료 페이지로 이동
	@GetMapping("/user/detail")
	public String userDetail() {
		return "/user/detail";
	}
	
	// 로그인
	@PostMapping("/login")
	public String login(LoginReqDto dto) {

	
		User userEntity = userRepository.mLogin(dto.getUsername(), dto.getPassword());
	
		if (userEntity == null) { // username, password 잘못 기입

			return "redirect:/user/loginForm";

		} else {
			// 세션 날라가는 조건 : 1. session.invalidate(), 2. 브라우저를 닫으면 날라감
			session.setAttribute("principal", userEntity); // 세션에 담은 이유는 인증하기 위해서
		}

		return "redirect:/user/detail";
	}

	// 로그아웃
	@GetMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/user/loginForm";
	}
	
	// 로그인 페이지로 이동
	@GetMapping("/user/loginForm")
	public String loginForm() {
		
		User principal = (User) session.getAttribute("principal");
		
		// 유효성검사
		// 인증 체크 (공통로직)
		if(principal != null ) { 
			return "redirect:/user/detail";
		}
		
		return "user/loginForm";
	}

	// 회원가입 페이지로 이동
	@GetMapping("/user/joinForm")
	public String joinForm() {
		return "/user/joinForm";
	}
	
	// 회원가입
	@PostMapping("/user/join")
	public String join(JoinReqDto dto) {
		// System.out.println(dto.getUsername());
		
		dto.setChk(1);
		
		// DB에 저장
		userRepository.save(dto.toEntity());
		return "redirect:/user/joinForm";
	}
	
	// 어드민으로 변경
	@PutMapping("/changeAdmin")
	public @ResponseBody CMRespDto<String> changeAdmin(){
		
		User principal = (User) session.getAttribute("principal");
		
		if(principal == null) {
			return new CMRespDto<>(-1, "실패", "로그인이 필요합니다.");
		}
		
		userRepository.mChangeAdmin(principal.getUsername());
		principal.setChk(2);
		
		return new CMRespDto<>(1, "성공", null);
	}
	
	// 게스트로 변경
	@PutMapping("/changeGuest")
	public @ResponseBody CMRespDto<String> changeGuest(){
		
		User principal = (User) session.getAttribute("principal");
		
		if(principal == null) {
			return new CMRespDto<>(-1, "실패", "로그인이 필요합니다.");
		}
		
		userRepository.mChangeGuest(principal.getUsername());
		principal.setChk(1);
		
		return new CMRespDto<>(1, "성공", null);
	}
}
