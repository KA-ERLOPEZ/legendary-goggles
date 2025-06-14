package com.sistema.pizzeria.elpirata.api.core.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


/**
 * The persistent class for the movimientos_stock database table.
 * 
 */
@Entity
@Table(name="movimientos_stock")
@NamedQuery(name="MovimientoStock.findAll", query="SELECT m FROM MovimientoStock m")
public class MovimientoStock implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="mov_id")
	private long movId;

	@Column(name="mov_cantidad", precision=10, scale=2, nullable=false)
	private BigDecimal movCantidad;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="mov_fecha", nullable=false)
	private Date movFecha;

	@Column(name="mov_tipo", nullable = false, length = 6)
	private String movTipo;

	//uni-directional many-to-one association to Stock
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="depo_id", referencedColumnName="depo_id"),
		@JoinColumn(name="prod_id", referencedColumnName="prod_id")
		})
	private Stock stock;

	public MovimientoStock() {
	}

	public long getMovId() {
		return this.movId;
	}

	public void setMovId(int movId) {
		this.movId = movId;
	}

	public BigDecimal getMovCantidad() {
		return this.movCantidad;
	}

	public void setMovCantidad(BigDecimal movCantidad) {
		this.movCantidad = movCantidad;
	}

	public Date getMovFecha() {
		return this.movFecha;
	}

	public void setMovFecha(Date movFecha) {
		this.movFecha = movFecha;
	}

	public String getMovTipo() {
		return this.movTipo;
	}

	public void setMovTipo(String movTipo) {
		this.movTipo = movTipo;
	}

	public Stock getStock() {
		return this.stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

}