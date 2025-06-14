package com.sistema.pizzeria.elpirata.api.core.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 * The primary key class for the detalle_compra database table.
 * 
 */
@Embeddable
public class DetalleCompraPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="compra_id", insertable=false, updatable=false)
	private long compraId;

	@Column(name="producto_id", insertable=false, updatable=false)
	private long productoId;

	public DetalleCompraPK() {
	}
	public long getCompraId() {
		return this.compraId;
	}
	public void setCompraId(long compraId) {
		this.compraId = compraId;
	}
	public long getProductoId() {
		return this.productoId;
	}
	public void setProductoId(int productoId) {
		this.productoId = productoId;
	}
	@Override
	public int hashCode() {
		return Objects.hash(compraId, productoId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof DetalleCompraPK))
			return false;
		DetalleCompraPK other = (DetalleCompraPK) obj;
		return compraId == other.compraId && productoId == other.productoId;
	}

	
}