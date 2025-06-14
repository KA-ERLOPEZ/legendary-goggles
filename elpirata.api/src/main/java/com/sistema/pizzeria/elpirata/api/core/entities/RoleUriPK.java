package com.sistema.pizzeria.elpirata.api.core.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 * The primary key class for the role_uri database table.
 * 
 */
@Embeddable
public class RoleUriPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="role_id", insertable=false, updatable=false)
	private long roleId;

	@Column(name="uri_id", insertable=false, updatable=false)
	private long uriId;
	
	@Column(name="access_type")
	private String accessType;

	public RoleUriPK() {
	}
	
	public RoleUriPK(long roleId, long uriId, String accessType) {
		super();
		this.roleId = roleId;
		this.uriId = uriId;
		this.accessType = accessType;
	}
	
	public long getRoleId() {
		return this.roleId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	public long getUriId() {
		return this.uriId;
	}
	public void setUriId(int uriId) {
		this.uriId = uriId;
	}
	public String getAccessType() {
		return this.accessType;
	}
	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accessType, roleId, uriId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof RoleUriPK))
			return false;
		RoleUriPK other = (RoleUriPK) obj;
		return Objects.equals(accessType, other.accessType) && roleId == other.roleId && uriId == other.uriId;
	}

	
}