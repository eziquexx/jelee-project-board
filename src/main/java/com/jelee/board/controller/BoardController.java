package com.jelee.board.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jelee.board.model.Board;
import com.jelee.board.service.BoardService;

@RestController
@RequestMapping("/api/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	// board all
	@GetMapping
	public List<Board> getBoardListAllLimit20() {
		return boardService.getBoardListAllLimit20();
	}
	
	// community detail
	@GetMapping("/{category}/{id}")
	public Board getCommunityDetail(@PathVariable("category") String category,
			@PathVariable("id") Integer id) {
		return boardService.getBoardDetail(category, id);
	}
	
	// board list
	@GetMapping("/{category}")
	public List<Board> getBoardListLimit10(@PathVariable("category") String category) {
		return boardService.getBoardListLimit10(category);
	}

}
