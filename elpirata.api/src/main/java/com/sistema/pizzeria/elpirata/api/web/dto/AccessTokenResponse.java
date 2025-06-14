package com.sistema.pizzeria.elpirata.api.web.dto;

public class AccessTokenResponse {

	private String accessToken;
	

	public AccessTokenResponse(String accessToken) {
		super();
		this.accessToken = accessToken;
	}

	public AccessTokenResponse() {
		super();
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	
}
