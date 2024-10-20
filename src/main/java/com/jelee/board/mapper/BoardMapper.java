package com.jelee.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.jelee.board.model.Board;

@Mapper
public interface BoardMapper {

	// board all list
	List<Board> getBoardListAllLimit20();
	
	// community
	Board getBoardById(@Param("category") String category, @Param("id") Integer id);
	List<Board> getBoardListLimit10(String category);
	
}
