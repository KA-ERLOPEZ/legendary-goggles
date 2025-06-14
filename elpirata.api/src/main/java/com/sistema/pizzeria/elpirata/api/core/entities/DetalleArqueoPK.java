package com.sistema.pizzeria.elpirata.api.core.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 * The primary key class for the detalle_arqueo database table.
 * 
 */
@Embeddable
public class DetalleArqueoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="arqueo_id", insertable=false, updatable=false)
	private long arqueoId;

	@Column(name="deno_id", insertable=false, updatable=false)
	private long denoId;

	public DetalleArqueoPK() {
	}
	public long getArqueoId() {
		return this.arqueoId;
	}
	public void setArqueoId(long arqueoId) {
		this.arqueoId = arqueoId;
	}
	public long getDenoId() {
		return this.denoId;
	}
	public void setDenoId(long denoId) {
		this.denoId = denoId;
	}
	@Override
	public int hashCode() {
		return Objects.hash(arqueoId, denoId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DetalleArqueoPK other = (DetalleArqueoPK) obj;
		return arqueoId == other.arqueoId && denoId == other.denoId;
	}

	
}