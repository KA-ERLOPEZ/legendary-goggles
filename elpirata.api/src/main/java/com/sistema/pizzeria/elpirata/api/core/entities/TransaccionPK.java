package com.sistema.pizzeria.elpirata.api.core.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 * The primary key class for the transacciones database table.
 * 
 */
@Embeddable
public class TransaccionPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="cac_id", insertable=false, updatable=false)
	private long cacId;

	@Column(name="tran_tipo_id", insertable=false, updatable=false)
	private long tranTipoId;

	public TransaccionPK() {
	}
	public long getCacId() {
		return this.cacId;
	}
	public void setCacId(long cacId) {
		this.cacId = cacId;
	}
	public long getTranTipoId() {
		return this.tranTipoId;
	}
	public void setTranTipoId(long tranTipoId) {
		this.tranTipoId = tranTipoId;
	}
	@Override
	public int hashCode() {
		return Objects.hash(cacId, tranTipoId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof TransaccionPK))
			return false;
		TransaccionPK other = (TransaccionPK) obj;
		return cacId == other.cacId && tranTipoId == other.tranTipoId;
	}

	
}