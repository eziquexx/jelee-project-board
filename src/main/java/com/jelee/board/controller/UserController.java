package com.jelee.board.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jelee.board.mapper.UserMapper;
import com.jelee.board.model.User;
import com.jelee.board.service.UserService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
	
	private final UserMapper userMapper;
	private final UserService userService;
	
	@PostMapping("/login")
	public void login(@ModelAttribute User user) {
		log.info(user.toString());
		User member = userMapper.findByUserId(user.getUserId());
		
		if (user.getUserPw().equals(member.getUserPw())) {
			log.info("회원");
		} else {
			log.info("비회원");
		}
	}
	
	@PostMapping("/signup")
	public void signup(@ModelAttribute User user, HttpServletResponse response) throws IOException {
		log.info(user.getUserId());
		
		boolean sucess = userService.registerUser(user);
		
		if (sucess) {
			response.sendRedirect("/user/signup/success");
		} else {
			String errorMessage = URLEncoder.encode("회원가입 실패. 아이디 중복. 다시 시도 해주세요.", StandardCharsets.UTF_8.toString());
			response.sendRedirect("/user/signup?error=" + errorMessage); // 인코딩된 메시지 전달
		}
		
	}
}
