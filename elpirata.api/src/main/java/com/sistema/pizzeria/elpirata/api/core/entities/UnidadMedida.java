package com.sistema.pizzeria.elpirata.api.core.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;


/**
 * The persistent class for the unidad_medida database table.
 * 
 */
@Entity
@Table(name="unidad_medida")
@NamedQuery(name="UnidadMedida.findAll", query="SELECT u FROM UnidadMedida u")
public class UnidadMedida implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="um_id")
	private long umId;

	@Column(name="um_abreviatura", length=5, nullable=false)
	private String umAbreviatura;

	@Column(name="um_enabled", nullable=false)
	@JoinColumn(name="um_estado", nullable=false, referencedColumnName="estado_id")
	private Estado umEstado;

	@Column(name="um_nombre", length=50, nullable=false)
	private String umNombre;

	public UnidadMedida() {
	}

	public long getUmId() {
		return this.umId;
	}

	public void setUmId(long umId) {
		this.umId = umId;
	}

	public String getUmAbreviatura() {
		return this.umAbreviatura;
	}

	public void setUmAbreviatura(String umAbreviatura) {
		this.umAbreviatura = umAbreviatura;
	}

	public Estado getUmEstado() {
		return this.umEstado;
	}
	
	public void setUmEstado(Estado umEstado) {
		this.umEstado = umEstado;
	}

	public String getUmNombre() {
		return this.umNombre;
	}

	public void setUmNombre(String umNombre) {
		this.umNombre = umNombre;
	}

}