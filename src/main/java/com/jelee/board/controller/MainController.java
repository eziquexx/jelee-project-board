package com.jelee.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jelee.board.model.Board;


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
	public String getBoardCommunity(@PathVariable("category") String category, Board board, Model model) {
		model.addAttribute("category", category);
		return "board/boardList";
	}
	
	// community board detail
	@GetMapping("/board/{category}/{id}")
	public String getBoardCommunityDetail(@PathVariable("category") String category, Board board, Model model) {
		model.addAttribute("category", category);
		return "board/boardDetail";
	}
}
