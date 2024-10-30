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
		
		// http 인증, 인가 설정
		http
			.formLogin(formLogin -> formLogin
					.loginPage("/user/login")
					.usernameParameter("userId")  // userId를 username으로 설정
	                .passwordParameter("userPw")  // password는 userPw로 설정
					.defaultSuccessUrl("/", true)
					.permitAll()
					)
			.authorizeHttpRequests(authorize -> authorize
					.requestMatchers("/", "/home", 
							"/user/login", "/user/signup", "/user/signup/success",
							"/css/**", "/js/**", 
							"/api/**", "/board/**").permitAll()
					.requestMatchers("/user/list", "/user/*/roles", "/user/*/role/**").hasRole("ADMIN")
					.anyRequest()
					.authenticated()
					);
		
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
