package com.jelee.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jelee.board.mapper.UserMapper;
import com.jelee.board.model.User;

@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;
	
	public void userRegister(User user) {
		userMapper.userRegister(user);
	};
	
}
