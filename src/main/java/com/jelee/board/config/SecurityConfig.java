package com.jelee.board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		log.info("security config...");
		
		http 
			.formLogin(formLogin -> formLogin
				.loginPage("/user/login") // 로그인 페이지 url
				.loginProcessingUrl("/login") // 로그인 처리 url
	            .usernameParameter("userId")   // 사용자 ID 이름 변경
	            .passwordParameter("userPw")   // 비밀번호 이름 변경
				.defaultSuccessUrl("/", true) // 로그인 성공시 이동 경로. true를 안 적어줬더니 로그인 페이지에 계속 머무는 현상이 발생.
											  // 그래서 true를 추가해줬다.
				.permitAll()
			)
			.authorizeHttpRequests(authorize -> authorize
					// css, js, api 작동을 안해서 클라이언트가 접근할 수 있게 추가해주기.
				.requestMatchers("/css/**", "/js/**", "/api/**").permitAll()
				.requestMatchers("/","/user/signup").permitAll()
				.anyRequest().authenticated()
			);
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
