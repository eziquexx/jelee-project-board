package com.jelee.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jelee.board.mapper.BoardMapper;
import com.jelee.board.model.Board;

@Service
public class BoardService {
	@Autowired
	private BoardMapper boardMapper;
	
	// board list all
	public List<Board> getBoardListAllLimit20() {
		return boardMapper.getBoardListAllLimit20();
	}
	
	// community detail
	public Board getBoardDetail(String category, Integer id) {
		return boardMapper.getBoardById(category, id);
	}
	
	// board list
	public List<Board> getBoardListLimit10(String category) {
		return boardMapper.getBoardListLimit10(category);
	}
	
	
}
