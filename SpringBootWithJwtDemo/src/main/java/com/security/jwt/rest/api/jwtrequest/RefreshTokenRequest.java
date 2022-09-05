package com.security.jwt.rest.api.jwtrequest;

public class RefreshTokenRequest {
	
	private String refreshToken;

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
}
