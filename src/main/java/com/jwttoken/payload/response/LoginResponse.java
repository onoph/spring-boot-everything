package com.jwttoken.payload.response;

import com.jwttoken.model.User;

/**
 * Login informations
 * @author Xph
 *
 */
public class LoginResponse {

	private String token;
	private final String type = "Bearer";
	private AuthenticatedUserDTO user;
	
	public LoginResponse(String accessToken, AuthenticatedUserDTO user) {
		this.token = accessToken;
		this.user = user;
	}

	public String getTokenType() {
		return type;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public AuthenticatedUserDTO getUser() {
		return user;
	}

	public void setUser(AuthenticatedUserDTO user) {
		this.user = user;
	}

	
	
}
