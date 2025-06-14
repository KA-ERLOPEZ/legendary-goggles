package com.sistema.pizzeria.elpirata.api.core.entities;

import java.io.Serializable;

import com.sistema.pizzeria.elpirata.api.core.commons.Auditable;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

/**
 * The persistent class for the role_uri database table.
 * 
 */
@Entity
@Table(name = "role_uri")
@NamedQuery(name = "RoleUri.findAll", query = "SELECT r FROM RoleUri r")
public class RoleUri extends Auditable implements Serializable  {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RoleUriPK id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "role_id", nullable = false, referencedColumnName = "role_id")
	private Role role;

	@ManyToOne(optional = false)
	@JoinColumn(name = "uri_id", nullable = false, referencedColumnName = "uri_id")
	private Uri uri;
	
	@Column(name = "rl_http_method", nullable = false, length = 255 )
	private String httpMethod;

	public RoleUri() {
	}

	

	public RoleUriPK getId() {
		return id;
	}



	public void setId(RoleUriPK id) {
		this.id = id;
	}



	public Role getRole() {
		return role;
	}



	public void setRole(Role role) {
		this.role = role;
	}


	public Uri getUri() {
		return uri;
	}

	public void setUri(Uri uri) {
		this.uri = uri;
	}



	public String getHttpMethod() {
		return httpMethod;
	}



	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}
	
	


}
