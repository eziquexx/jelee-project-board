package com.jelee.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jelee.board.mapper.UserMapper;
import com.jelee.board.model.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserMapper userMapper;
	
	@PostMapping("/login")
	public void login(@RequestParam("userId") String userId, @RequestParam("userPw") String userPw) {
		log.info(userId);
		User member = userMapper.findByUserId(userId);
		
		if (userPw.equals(member.getUserPw())) {
			log.info("회원");
		} else {
			log.info("비회원");
		}
	}
}
