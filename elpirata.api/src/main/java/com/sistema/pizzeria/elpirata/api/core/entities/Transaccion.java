package com.sistema.pizzeria.elpirata.api.core.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;


/**
 * The persistent class for the transacciones database table.
 * 
 */
@Entity
@Table(name="transacciones")
@NamedQuery(name="Transaccion.findAll", query="SELECT t FROM Transaccion t")
public class Transaccion implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TransaccionPK id;

	@Column(name="tran_descripcion", length=100, nullable=false)
	private String tranDescripcion;

	@ManyToOne
	@JoinColumn(name="tran_estado", nullable=false, referencedColumnName="estado_id")
	private Estado tranEstado;

	@Column(name="tran_monto", nullable=false, precision=10, scale=2)
	private BigDecimal tranMonto;

	//bi-directional many-to-one association to CajaAperturaCierre
	@ManyToOne
	@JoinColumn(name="cac_id", nullable=false, referencedColumnName="cac_id")
	private CajaAperturaCierre cajaAperturaCierre;

	//uni-directional many-to-one association to TipoTransaccion
	@ManyToOne
	@JoinColumn(name="tran_tipo_id")
	private TipoTransaccion tipoTransaccione;

	public Transaccion() {
	}

	public TransaccionPK getId() {
		return this.id;
	}

	public void setId(TransaccionPK id) {
		this.id = id;
	}

	public String getTranDescripcion() {
		return this.tranDescripcion;
	}

	public void setTranDescripcion(String tranDescripcion) {
		this.tranDescripcion = tranDescripcion;
	}

	
	public Estado getTranEstado() {
		return this.tranEstado;
	}
	
	public void setTranEstado(Estado tranEstado) {
		this.tranEstado = tranEstado;
	}

	public BigDecimal getTranMonto() {
		return this.tranMonto;
	}
	
	public void setTranMonto(BigDecimal tranMonto) {
		this.tranMonto = tranMonto;
	}
	

	public CajaAperturaCierre getCajaAperturaCierre() {
		return this.cajaAperturaCierre;
	}

	public void setCajaAperturaCierre(CajaAperturaCierre cajaAperturaCierre) {
		this.cajaAperturaCierre = cajaAperturaCierre;
	}

	public TipoTransaccion getTipoTransaccione() {
		return this.tipoTransaccione;
	}

	public void setTipoTransaccione(TipoTransaccion tipoTransaccione) {
		this.tipoTransaccione = tipoTransaccione;
	}

}