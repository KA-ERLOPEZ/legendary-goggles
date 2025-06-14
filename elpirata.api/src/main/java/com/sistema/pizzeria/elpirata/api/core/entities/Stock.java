package com.sistema.pizzeria.elpirata.api.core.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;


/**
 * The persistent class for the stock database table.
 * 
 */
@Entity
@NamedQuery(name="Stock.findAll", query="SELECT s FROM Stock s")
public class Stock implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private StockPK id;

	@Column(name="stk_cantidad_actual", nullable=false, precision=10, scale=2)
	private BigDecimal stkCantidadActual;

	@Column(name="stk_cantidad_max", nullable=false, precision=10, scale=2)
	private BigDecimal stkCantidadMax;

	@Column(name="stk_cantidad_min", nullable=false, precision=10, scale=2)
	private BigDecimal stkCantidadMin;

	//uni-directional many-to-one association to Deposito
	@ManyToOne
	@JoinColumn(name="depo_id", nullable=false, referencedColumnName="depo_id")
	private Deposito deposito;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="prod_id", nullable=false, referencedColumnName="prod_id")
	private Producto producto;

	public Stock() {
	}

	public StockPK getId() {
		return this.id;
	}

	public void setId(StockPK id) {
		this.id = id;
	}

	public BigDecimal getStkCantidadActual() {
        return this.stkCantidadActual;
            }
	
	public void setStkCantidadActual(BigDecimal stkCantidadActual) {
		this.stkCantidadActual = stkCantidadActual;
	}

	public BigDecimal getStkCantidadMax() {
		return this.stkCantidadMax;
	}
	
	public void setStkCantidadMax(BigDecimal stkCantidadMax) {
		this.stkCantidadMax = stkCantidadMax;
	}
	
	public BigDecimal getStkCantidadMin() {
		return this.stkCantidadMin;
	}
	
	public void setStkCantidadMin(BigDecimal stkCantidadMin) {
        this.stkCantidadMin = stkCantidadMin;
	}
	

	public Deposito getDeposito() {
		return this.deposito;
	}

	public void setDeposito(Deposito deposito) {
		this.deposito = deposito;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}