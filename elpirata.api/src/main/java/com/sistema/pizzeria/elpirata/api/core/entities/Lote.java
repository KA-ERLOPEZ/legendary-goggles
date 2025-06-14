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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


/**
 * The persistent class for the lote database table.
 * 
 */
@Entity
@NamedQuery(name="Lote.findAll", query="SELECT l FROM Lote l")
public class Lote implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="lote_id")
	private long loteId;

	@Column(name="cantidad_actual", precision=10, scale=2, nullable=false)
	private BigDecimal cantidadActual;

	@Column(name="cantidad_inicial", precision=10, scale=2, nullable=false)
	private BigDecimal cantidadInicial;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_fabricacion", nullable=false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date fechaFabricacion;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_vencimiento", nullable=false)
	private Date fechaVencimiento;

	@Column(name="lote_nro", length=20, nullable=false, unique=true)
	private String loteNro;

	//uni-directional many-to-one association to Deposito
	@ManyToOne
	@JoinColumn(name="depo_id", nullable=false)
	private Deposito deposito;

	//uni-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name="estado_id", nullable=false, referencedColumnName="estado_id")
	private Estado estado;

	//uni-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="prod_id", nullable=false, referencedColumnName="prod_id")
	private Producto producto;

	public Lote() {
	}

	public long getLoteId() {
		return this.loteId;
	}

	public void setLoteId(long loteId) {
		this.loteId = loteId;
	}

	public BigDecimal getCantidadActual() {
		return this.cantidadActual;
	}
	
	public void setCantidadActual(BigDecimal cantidadActual) {
		this.cantidadActual = cantidadActual;
	}
	
	public BigDecimal getCantidadInicial() {
		return this.cantidadInicial;
	}
	
	public void setCantidadInicial(BigDecimal cantidadInicial) {
		this.cantidadInicial = cantidadInicial;
	}

	public Date getFechaFabricacion() {
		return this.fechaFabricacion;
	}

	public void setFechaFabricacion(Date fechaFabricacion) {
		this.fechaFabricacion = fechaFabricacion;
	}

	public Date getFechaVencimiento() {
		return this.fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public String getLoteNro() {
		return this.loteNro;
	}

	public void setLoteNro(String loteNro) {
		this.loteNro = loteNro;
	}

	public Deposito getDeposito() {
		return this.deposito;
	}

	public void setDeposito(Deposito deposito) {
		this.deposito = deposito;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}