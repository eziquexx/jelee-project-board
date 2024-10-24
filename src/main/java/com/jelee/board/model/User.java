package com.jelee.board.model;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class User {
	private Long id;
	@NonNull
	private String userId;
	@NonNull
	private String userPw;
	@NonNull
	private String userName;
	@NonNull
	private String userEmail;
    private boolean enabled;
    private String role;
}
