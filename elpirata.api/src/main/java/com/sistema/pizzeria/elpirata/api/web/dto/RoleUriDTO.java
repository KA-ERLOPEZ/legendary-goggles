package com.sistema.pizzeria.elpirata.api.web.dto;

import jakarta.validation.constraints.NotNull;

public class RoleUriDTO {

    @NotNull
    private RoleDTO role;

    @NotNull
    private UriDTO uri;

    @NotNull
    private String httpMethod;

	public RoleUriDTO(@NotNull RoleDTO role, @NotNull UriDTO uri, @NotNull String httpMethod) {
		super();
		this.role = role;
		this.uri = uri;
		this.httpMethod = httpMethod;
	}

	public RoleUriDTO() {
		
	}

	public RoleDTO getRole() {
		return role;
	}

	public void setRole(RoleDTO role) {
		this.role = role;
	}

	public UriDTO getUri() {
		return uri;
	}

	public void setUri(UriDTO uri) {
		this.uri = uri;
	}

	public String getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}

	

   
}


