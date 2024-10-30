package com.jelee.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jelee.board.mapper.UserMapper;
import com.jelee.board.model.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final PasswordEncoder passwordEncoder;
	private final UserMapper userMapper;
	
	public boolean registerUser(User user) {
		// 중복 체크
		int check = userMapper.checkUserId(user.getUserId());
		boolean exists = (check > 0);
		
		if (exists) {
			return false;
		}
		
		// 사용자 등록
		User newUser = new User();
		newUser.setUserId(user.getUserId());
		newUser.setUserPw(passwordEncoder.encode(user.getUserPw()));
		newUser.setUserName(user.getUserName());
		newUser.setUserEmail(user.getUserEmail());
		newUser.setEnabled(true);
		
		userMapper.save(newUser);
		// 권한 부여
		userMapper.insertUserRole(newUser.getId(), 1L);
		
		return true;
	}
	
	public List<User> getUserList() {
		return userMapper.getUserList();
	}
}
