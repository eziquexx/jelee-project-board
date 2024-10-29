package com.jelee.board.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.jelee.board.model.User;

@Mapper
public interface UserMapper {
	void save (User user);
	User findByUserId(String userId);
}
