package com.sistema.pizzeria.elpirata.api.core.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;


/**
 * The persistent class for the denominaciones database table.
 * 
 */
@Entity
@Table(name="denominaciones")
@NamedQuery(name="Denominacion.findAll", query="SELECT d FROM Denominacion d")
public class Denominacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="deno_id")
	private long denoId;

	@Column(name="deno_codigo", unique=true, nullable=false, length=10)
	private String denoCodigo;

	@Column(name="deno_nombre", nullable=false, length=50)
	private String denoNombre;
	
	@ManyToOne
	@JoinColumn(name="estado_id", nullable=false, referencedColumnName="estado_id")
	private Estado estado;

	public Denominacion() {
	}

	public long getDenoId() {
		return this.denoId;
	}

	public void setDenoId(long denoId) {
		this.denoId = denoId;
	}

	public String getDenoCodigo() {
		return this.denoCodigo;
	}

	public void setDenoCodigo(String denoCodigo) {
		this.denoCodigo = denoCodigo;
	}

	public String getDenoNombre() {
		return this.denoNombre;
	}

	public void setDenoNombre(String denoNombre) {
		this.denoNombre = denoNombre;
	}

}