package com.jelee.board.controller;

import java.util.Map;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jelee.board.model.User;
import com.jelee.board.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	// Bcrypt.checkpw() 메서드를 통해 사용자가 입력한 pw와 DB에 저장된 PW 비교
	private boolean checkPw(String userPw, String memberPw) {
		return BCrypt.checkpw(userPw, memberPw);
	}
	
	// user - signup
	@PostMapping("/signup")
	public void userSignUp(@RequestBody User user) {
		System.out.println(user.toString());
		userService.userSignUp(user);
	}
	
	// user - login
	@PostMapping("/login")
	public ResponseEntity<?> userLogin(@RequestBody User user) {
		User member = userService.getUserPw(user.getUserId());
		if (member != null) {
			if (checkPw(user.getUserPw(), member.getUserPw())) {
				return ResponseEntity.ok().body(Map.of("message", "로그인 성공", "user", member));
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "비밀번호가 일치하지 않습니다."));
			}
		} else {
			// 사용자를 찾을 수 없습니다.
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "사용자를 찾을 수 없습니다."));
		}
	}
}
