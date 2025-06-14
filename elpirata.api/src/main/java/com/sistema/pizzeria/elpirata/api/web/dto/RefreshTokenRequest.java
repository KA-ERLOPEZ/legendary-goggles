package com.sistema.pizzeria.elpirata.api.web.dto;

public class RefreshTokenRequest {

	private String refreshToken;
	
	

	public RefreshTokenRequest(String refreshToken) {
		super();
		this.refreshToken = refreshToken;
	}
	
	

	public RefreshTokenRequest() {
		super();
	}



	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	
	
}
