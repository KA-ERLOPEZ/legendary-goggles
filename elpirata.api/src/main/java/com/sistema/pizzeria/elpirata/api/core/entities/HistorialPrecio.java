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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


/**
 * The persistent class for the historial_precios database table.
 * 
 */
@Entity
@Table(name="historial_precios")
@NamedQuery(name="HistorialPrecio.findAll", query="SELECT h FROM HistorialPrecio h")
public class HistorialPrecio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="hist_precio_id")
	private long histPrecioId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_cambio", nullable=false)
	private Date fechaCambio;

	@Column(name="nuevo_precio", nullable=false, precision=10, scale=2 )
	private BigDecimal nuevoPrecio;

	@Column(name="precio_anterior", nullable=false, precision=10, scale=2 )
	private BigDecimal precioAnterior;
	
	@Column(name="historial_estado", nullable=false)
	private boolean historialEstado;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="prod_id", nullable=false)
	private Producto producto;

	public HistorialPrecio() {
	}

	public long getHistPrecioId() {
		return this.histPrecioId;
	}

	public void setHistPrecioId(int histPrecioId) {
		this.histPrecioId = histPrecioId;
	}

	public Date getFechaCambio() {
		return this.fechaCambio;
	}

	public void setFechaCambio(Date fechaCambio) {
		this.fechaCambio = fechaCambio;
	}

	public BigDecimal getNuevoPrecio() {
		return this.nuevoPrecio;
	}

	public void setNuevoPrecio(BigDecimal nuevoPrecio) {
		this.nuevoPrecio = nuevoPrecio;
	}

	public BigDecimal getPrecioAnterior() {
		return this.precioAnterior;
	}

	public void setPrecioAnterior(BigDecimal precioAnterior) {
		this.precioAnterior = precioAnterior;
	}
	

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	public boolean isHistorialEstado() {
		return historialEstado;
	}	

	public void setHistorialEstado(boolean historialEstado) {
		this.historialEstado = historialEstado;
	}

}