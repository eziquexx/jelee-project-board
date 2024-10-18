package com.jelee.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class MainController {

	// home
	@GetMapping
	public String getHome() {
		return "index";
	}
	
	// community bulletin board
	@GetMapping("/board/community")
	public String getBoardCommunity() {
		return "board/community";
	}
}
