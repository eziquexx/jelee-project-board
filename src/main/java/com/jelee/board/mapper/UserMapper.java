package com.jelee.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jelee.board.model.User;

@Mapper
public interface UserMapper {
	void save(User user);
//	void userRegister(User user);
	User findByUserId(String userId);
//	User getUserPwByUserId(String userId); 
	List<User> getUserList();
}
