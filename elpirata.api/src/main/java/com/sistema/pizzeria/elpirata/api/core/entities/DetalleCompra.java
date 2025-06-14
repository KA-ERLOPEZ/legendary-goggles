package com.sistema.pizzeria.elpirata.api.core.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;


/**
 * The persistent class for the detalle_compra database table.
 * 
 */
@Entity
@Table(name="detalle_compra")
@NamedQuery(name="DetalleCompra.findAll", query="SELECT d FROM DetalleCompra d")
public class DetalleCompra implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private DetalleCompraPK id;

	@Column(name="cd_cantidad", precision=10, scale=2, nullable=false)
	private BigDecimal cdCantidad;

	@Column(name="cd_cantidad_recivida", precision=10, scale=2, nullable=false)
	private BigDecimal cdCantidadRecivida;

	@Column(name="precio_unitario", precision=10, scale=2, nullable=false)
	private BigDecimal precioUnitario;

	//bi-directional many-to-one association to Compra
	@ManyToOne
	@JoinColumn(name="compra_id", insertable=false, updatable=false, nullable=false)
	private Compra compra;

	//uni-directional many-to-one association to Stock
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="depo_id", referencedColumnName="depo_id"),
		@JoinColumn(name="producto_id", referencedColumnName="prod_id")
		})
	private Stock stock;

	public DetalleCompra() {
	}

	public DetalleCompraPK getId() {
		return this.id;
	}

	public void setId(DetalleCompraPK id) {
		this.id = id;
	}

	public BigDecimal getCdCantidad() {
		return this.cdCantidad;
	}

	public void setCdCantidad(BigDecimal cdCantidad) {
		this.cdCantidad = cdCantidad;
	}

	public BigDecimal getCdCantidadRecivida() {
		return this.cdCantidadRecivida;
	}

	public void setCdCantidadRecivida(BigDecimal cdCantidadRecivida) {
		this.cdCantidadRecivida = cdCantidadRecivida;
	}

	public BigDecimal getPrecioUnitario() {
		return this.precioUnitario;
	}

	public void setPrecioUnitario(BigDecimal precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public Compra getCompra() {
		return this.compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public Stock getStock() {
		return this.stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

}