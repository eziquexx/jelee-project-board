package com.jelee.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jelee.board.model.User;


@Controller
@RequestMapping("/")
public class MainController {

	// home
	@GetMapping
	public String getHome() {
		return "index";
	}
	
	// community board list
	@GetMapping("/board/{category}")
	public String getBoardCommunity(@PathVariable("category") String category, Model model) {
		model.addAttribute("category", category);
		return "board/boardList";
	}
	
	// community board detail
	@GetMapping("/board/{category}/{id}")
	public String getBoardCommunityDetail(@PathVariable("category") String category, Model model) {
		model.addAttribute("category", category);
		return "board/boardDetail";
	}
	
	// user - login
	@GetMapping("/user/login")
	public String loginPage() {
		return "user/userLogin";
	}
	
	// user - register, add
	@GetMapping("/user/signup")
	public String signUpPage() {
		return "user/userSignup";
	}
	// user - register, add
	@GetMapping("/user/signup/success")
	public String signupSucessPage() {
		return "user/successSignup";
	}
	
	// user - list, admin
	@GetMapping("/user/list")
	public String userListPage() {
		return "user/userList";
	}
	
	// user - detail, admin
	@GetMapping("/user/{userId}/roles")
	public String userRoles(@PathVariable("userId") Long userId, Model model) {
		User user = new User();
		user.setId(userId);
		
		model.addAttribute("userId", userId);
		return "user/userRoles";
	}

}
