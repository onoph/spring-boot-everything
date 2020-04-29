package com.jwttoken.payload.response;

import com.jwttoken.security.AuthenticatedUser;

public class AuthenticatedUserDTO {

	private Long userId;
	private String username;
	private String email;
	
	public static AuthenticatedUserDTO of(AuthenticatedUser authUser) {
		return new AuthenticatedUserDTO(
				authUser.getId(), authUser.getUsername(), authUser.getEmail());
	}
	
	private AuthenticatedUserDTO(Long userId, String username, String email) {
		super();
		this.userId = userId;
		this.username = username;
		this.email = email;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
