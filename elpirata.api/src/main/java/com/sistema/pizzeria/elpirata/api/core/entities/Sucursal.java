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
 * The persistent class for the sucursales database table.
 * 
 */
@Entity
@Table(name="sucursales")
@NamedQuery(name="Sucursal.findAll", query="SELECT s FROM Sucursal s")
public class Sucursal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="suc_id")
	private long sucId;
	
	@Column(name="suc_codigo", nullable=false, unique=true)
	private Integer sucCodigo;
	
	@Column(name="suc_ruc", length=20, nullable=false, unique=true)
	private String sucRuc;

	@Column(name="suc_direccion", length=100, nullable=false)
	private String sucDireccion;
	

	@Column(name="suc_email", length=150, nullable=false, unique=true)
	private String sucEmail;

	@Column(name="suc_nombre", length=50, nullable=false)
	private String sucNombre;

	@Column(name="suc_telefono", length=15, nullable=false)
	private String sucTelefono;

	//uni-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name="estado_id")
	private Estado estado;

	public Sucursal() {
	}

	public long getSucId() {
		return this.sucId;
	}

	public void setSucId(long sucId) {
		this.sucId = sucId;
	}

	public String getSucDireccion() {
		return this.sucDireccion;
	}

	public void setSucDireccion(String sucDireccion) {
		this.sucDireccion = sucDireccion;
	}

	public String getSucEmail() {
		return this.sucEmail;
	}

	public void setSucEmail(String sucEmail) {
		this.sucEmail = sucEmail;
	}

	public String getSucNombre() {
		return this.sucNombre;
	}

	public void setSucNombre(String sucNombre) {
		this.sucNombre = sucNombre;
	}

	public String getSucTelefono() {
		return this.sucTelefono;
	}

	public void setSucTelefono(String sucTelefono) {
		this.sucTelefono = sucTelefono;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public Integer getSucCodigo() {
		return sucCodigo;
	}

	public void setSucCodigo(Integer sucCodigo) {
		this.sucCodigo = sucCodigo;
	}
	
	public String getSucRuc() {
		return sucRuc;
	}

	public void setSucRuc(String sucRuc) {
		this.sucRuc = sucRuc;
	}
	

}