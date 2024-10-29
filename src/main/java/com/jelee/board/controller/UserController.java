package com.jelee.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	public void login(@ModelAttribute User user) {
		log.info(user.toString());
		User member = userMapper.findByUserId(user.getUserId());
		
		if (user.getUserPw().equals(member.getUserPw())) {
			log.info("회원");
		} else {
			log.info("비회원");
		}
	}
}
