package com.jelee.board.model;

import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User {
	private Long id;
	private String userId;
	private String userPw;
	private String userName;
	private String userEmail;
	private Boolean enabled;
	private List<Role> roles;
}
