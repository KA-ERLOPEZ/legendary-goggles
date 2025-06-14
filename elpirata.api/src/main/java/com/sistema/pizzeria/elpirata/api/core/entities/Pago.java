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
 * The persistent class for the pagos database table.
 * 
 */
@Entity
@Table(name="pagos")
@NamedQuery(name="Pago.findAll", query="SELECT p FROM Pago p")
public class Pago implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pag_id")
	private long pagId; 

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="pag_fecha", nullable = false)
	private Date pagFecha;

	@Column(name="pag_monto", nullable = false, precision = 10, scale = 2)
	private BigDecimal pagMonto;

	//uni-directional many-to-one association to TipoPago
	@ManyToOne
	@JoinColumn(name="tp_id", nullable = false, referencedColumnName = "tp_id")
	private TipoPago tipoPago;

	//uni-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="usu_id", nullable = false, referencedColumnName = "usu_id")
	private Usuario usuario;

	//uni-directional many-to-one association to VentaCabecera
	@ManyToOne
	@JoinColumn(name="venta_id", nullable = false, referencedColumnName = "venta_id")
	private VentaCabecera ventaCabecera;

	public Pago() {
	}

	public long getPagId() {
		return this.pagId;
	}

	public void setPagId(long pagId) {
		this.pagId = pagId;
	}

	public Date getPagFecha() {
		return this.pagFecha;
	}

	public void setPagFecha(Date pagFecha) {
		this.pagFecha = pagFecha;
	}

	public BigDecimal getPagMonto() {
		return this.pagMonto;
	}

	public void setPagMonto(BigDecimal pagMonto) {
		this.pagMonto = pagMonto;
	}

	public TipoPago getTipoPago() {
		return this.tipoPago;
	}

	public void setTipoPago(TipoPago tipoPago) {
		this.tipoPago = tipoPago;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public VentaCabecera getVentaCabecera() {
		return this.ventaCabecera;
	}

	public void setVentaCabecera(VentaCabecera ventaCabecera) {
		this.ventaCabecera = ventaCabecera;
	}

}