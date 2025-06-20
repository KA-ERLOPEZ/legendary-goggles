package com.sistema.pizzeria.elpirata.api.core.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;


/**
 * The persistent class for the tipo_documento database table.
 * 
 */
@Entity
@Table(name="tipo_documento")
@NamedQuery(name="TipoDocumento.findAll", query="SELECT t FROM TipoDocumento t")
public class TipoDocumento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="tipodoc_id")
	private long tipodocId;
	@Column(name="tipodoc_descripcion", nullable = false, length=100)
	private String tipodocDescripcion;

	@Column(name="tipodoc_ley_nro", length=50, nullable = false)
	private String tipodocLeyNro;

	@Column(name="tipodoc_nombre", nullable = false, length=50)
	private String tipodocNombre;


	//uni-directional many-to-one association to Estado
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="estado_id", nullable = false, referencedColumnName = "estado_id")
	private Estado estado;

	public TipoDocumento() {
	}

	public long getTipodocId() {
		return this.tipodocId;
	}

	public void setTipodocId(long tipodocId) {
		this.tipodocId = tipodocId;
	}

	public String getTipodocDescripcion() {
		return this.tipodocDescripcion;
	}

	public void setTipodocDescripcion(String tipodocDescripcion) {
		this.tipodocDescripcion = tipodocDescripcion;
	}

	public String getTipodocLeyNro() {
		return this.tipodocLeyNro;
	}

	public void setTipodocLeyNro(String tipodocLeyNro) {
		this.tipodocLeyNro = tipodocLeyNro;
	}

	public String getTipodocNombre() {
		return this.tipodocNombre;
	}

	public void setTipodocNombre(String tipodocNombre) {
		this.tipodocNombre = tipodocNombre;
	}


	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}