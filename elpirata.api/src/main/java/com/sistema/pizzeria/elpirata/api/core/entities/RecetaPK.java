package com.sistema.pizzeria.elpirata.api.core.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 * The primary key class for the recetas database table.
 * 
 */
@Embeddable
public class RecetaPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="prod_id", insertable=false, updatable=false)
	private long prodId;

	@Column(name="ingrediente_id", insertable=false, updatable=false)
	private long ingredienteId;

	public RecetaPK() {
	}
	public long getProdId() {
		return this.prodId;
	}
	public void setProdId(long prodId) {
		this.prodId = prodId;
	}
	public long getIngredienteId() {
		return this.ingredienteId;
	}
	public void setIngredienteId(long ingredienteId) {
		this.ingredienteId = ingredienteId;
	}
	@Override
	public int hashCode() {
		return Objects.hash(ingredienteId, prodId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof RecetaPK))
			return false;
		RecetaPK other = (RecetaPK) obj;
		return ingredienteId == other.ingredienteId && prodId == other.prodId;
	}

	
}