package com.sistema.pizzeria.elpirata.api.web.dto;

public class AuthResponse {
	
	private String accessToken;
	private String refreshToken;
	
	

	public AuthResponse(String accessToken, String refreshToken) {
		super();
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}
	
	

	public AuthResponse() {
		super();
	}



	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

}
