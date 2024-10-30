package com.jelee.board.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jelee.board.mapper.UserMapper;
import com.jelee.board.model.Role;
import com.jelee.board.model.User;
import com.jelee.board.service.UserService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
	
	private final UserMapper userMapper;
	private final UserService userService;
	
	@PostMapping("/login")
	public void login(@ModelAttribute User user) {
		log.info(user.getUserId());
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
		
		boolean success = userService.registerUser(user);
		
		if (success) {
			response.sendRedirect("/user/signup/success");
		} else {
			String errorMessage = URLEncoder.encode("회원가입 실패. 아이디 중복. 다시 시도 해주세요.", StandardCharsets.UTF_8.toString());
			response.sendRedirect("/user/signup?error=" + errorMessage); // 인코딩된 메시지 전달
		}
	}
	
	@GetMapping("/list")
	public List<User> getUserList() {
		return userService.getUserList();
	}
	
	@GetMapping("/{userId}/roles")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> userRoles(@PathVariable("userId") Long userId) {
		List<Role> roles = userMapper.findRolesByUserId(userId);
		String user = userMapper.findById(userId).getUserId();
		List<Role> allRoles = userMapper.getAllRoles();
		
		Map<String, Object> response = new HashMap<>();
		response.put("allRoles", allRoles);
		response.put("roles", roles);
		response.put("user", user);
		response.put("userId", userId);
		
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/{userId}/role/add")
	public ResponseEntity<Map<String, Object>> insertUserRole(@PathVariable("userId") Long userId, 
			@RequestParam("roleId") Long roleId) {
		
//		userMapper.insertUserRole(userId, roleId);
		
		/*
		 * // 추가하고 난 다음의 최종 데이터 가져오기 List<Role> addedRole =
		 * userMapper.findRolesByUserId(userId);
		 * 
		 * // 모든 역할 가져옥 List<Role> allRoles = userMapper.getAllRoles();
		 * 
		 * Map<String, Object> response = new HashMap<>(); response.put("role",
		 * addedRole); response.put("allRoles", allRoles);
		 */
		
	    
	    userMapper.insertUserRole(userId, roleId);
	    
	    String redirectUrl = "/user/" + userId + "/roles";
	    System.out.println("Redirecting to: " + redirectUrl);
	    
	    return ResponseEntity.status(HttpStatus.FOUND)
	            .location(URI.create(redirectUrl))
	            .build();
	}
}
