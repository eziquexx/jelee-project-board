package com.jelee.board.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jelee.board.mapper.UserMapper;
import com.jelee.board.model.User;

@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;
	
	public void userSignUp(User user) {
		System.out.println(user);
		user.setUserPw(BCrypt.hashpw(user.getUserPw(), BCrypt.gensalt()));
		userMapper.userRegister(user);
	};
	
	public User getUserPw(String userId) {
		return userMapper.getUserPwByUserId(userId);
	}
	
}
