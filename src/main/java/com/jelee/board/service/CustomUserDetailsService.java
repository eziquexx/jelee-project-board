package com.jelee.board.service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jelee.board.mapper.UserMapper;
import com.jelee.board.model.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final UserMapper userMapper;
	
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		// userId가 들어오는지 확인하기 위한 용도.
		// 디버깅 해보니까 안들어온다.. 왜..? ㅠㅠ
		log.info(userId);
		User user = userMapper.findByUserId(userId);
		if (user == null) {
			throw new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + userId);
		}
		
		return new org.springframework.security.core.userdetails.User(
				user.getUserId(),
				user.getUserPw(),
				user.isEnabled(),
				true, true, true,
				Collections.singletonList(new SimpleGrantedAuthority(user.getRole()))
		);
	}
	
}
