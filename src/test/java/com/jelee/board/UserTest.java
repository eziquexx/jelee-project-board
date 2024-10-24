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
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserMapper userMapper;
	
	@Test
	@DisplayName("사용자 추가 테스트")
	void addUser() {
		// 테스트용 사용자 생성
		User user = new User();
		user.setUserId("test1");
		user.setUserPw(passwordEncoder.encode("1234"));
		user.setUserName("지은");
		user.setUserEmail("hello@gmail.com");
		user.setEnabled(true);
		user.setRole("ROLL_USER");
		
		log.info("password: {}", user.getUserPw());
		
		// userMapper를 통해 데이터베이스에 사용자 추가
		userMapper.save(user);
	}
}
