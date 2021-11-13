package com.cos.validation_test.web.dto;

import com.cos.validation_test.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JoinReqDto {

	private String username;
	private String password;
	private int chk;
	
	public User toEntity() {
		User user = User.builder()
				.username(username)
				.password(password)
				.chk(chk)
				.build();
		return user;
	}
}
