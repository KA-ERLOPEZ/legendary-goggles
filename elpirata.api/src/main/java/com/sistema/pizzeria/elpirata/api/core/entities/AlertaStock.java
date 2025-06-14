package com.sistema.pizzeria.elpirata.api.core.entities;

import java.io.Serializable;
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
 * The persistent class for the alertas_stock database table.
 * 
 */
@Entity
@Table(name="alertas_stock")
@NamedQuery(name="AlertaStock.findAll", query="SELECT a FROM AlertaStock a")
public class AlertaStock implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="alerta_id")
	private long alertaId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="alerta_fecha", nullable = false)
	private Date alertaFecha;

	@Column(name="alerta_tipo", nullable = false, length=50)
	private String alertaTipo;

	//uni-directional many-to-one association to Stock
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="depo_id", referencedColumnName="depo_id"),
		@JoinColumn(name="prod_id", referencedColumnName="prod_id")
		})
	private Stock stock;

	//uni-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="usu_id", referencedColumnName="usu_id", nullable = false)
	private Usuario usuario;

	public AlertaStock() {
	}

	public long getAlertaId() {
		return this.alertaId;
	}

	public void setAlertaId(long alertaId) {
		this.alertaId = alertaId;
	}

	public Date getAlertaFecha() {
		return this.alertaFecha;
	}

	public void setAlertaFecha(Date alertaFecha) {
		this.alertaFecha = alertaFecha;
	}

	public String getAlertaTipo() {
		return this.alertaTipo;
	}

	public void setAlertaTipo(String alertaTipo) {
		this.alertaTipo = alertaTipo;
	}

	public Stock getStock() {
		return this.stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}