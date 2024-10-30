package com.jelee.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


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
	
	// user list
	@GetMapping("/users")
	public String userList() {
		return "user/userList";
	}

}
