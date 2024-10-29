package com.jelee.board;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.jelee.board.mapper.UserMapper;
import com.jelee.board.model.User;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class UserTest {
	
	PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserMapper userMapper;
	
	@Test
	@DisplayName("사용자 추가 테스트")
	void addUser() {
		User user = new User();
		user.setUserId("hong");
		user.setUserPw("1234");
		user.setUserName("홍씨");
		user.setUserEmail("hong@gmail.com");
		user.setEnabled(true);
		user.setRole("ROLE_USER");
		
		log.info("암호화 하지 않음", user.getUserPw());
		
		userMapper.save(user);
	}
	

}
