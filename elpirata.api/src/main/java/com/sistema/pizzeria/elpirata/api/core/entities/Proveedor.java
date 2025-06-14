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
 * The persistent class for the proveedores database table.
 * 
 */
@Entity
@Table(name="proveedores")
@NamedQuery(name="Proveedor.findAll", query="SELECT p FROM Proveedor p")
public class Proveedor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="prov_id")
	private long provId;

	@Column(name="prov_direccion", length=100, nullable=false)
	private String provDireccion;

	@Column(name="prov_email", length=100, nullable=false, unique=true)
	private String provEmail;

	@Column(name="prov_pag_web", length=100, nullable=false, unique=true)
	private String provPagWeb;

	@Column(name="prov_razon_social", length=100, nullable=false)
	private String provRazonSocial;

	@Column(name="prov_ruc", length=13, nullable=false, unique=true)
	private String provRuc;

	@Column(name="prov_telefono", length=15, nullable=false)
	private String provTelefono;
	
	//uni-directional many-to-one association to Barrio
	@ManyToOne
	@JoinColumn(name="barrio_id", referencedColumnName = "bar_id", nullable = false)
	private Barrio barrio;

	//uni-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name="estado_id", referencedColumnName = "estado_id", nullable = false)
	private Estado estado;

	public Proveedor() {
	}

	public long getProvId() {
		return this.provId;
	}

	public void setProvId(long provId) {
		this.provId = provId;
	}

	public String getProvDireccion() {
		return this.provDireccion;
	}

	public void setProvDireccion(String provDireccion) {
		this.provDireccion = provDireccion;
	}

	public String getProvEmail() {
		return this.provEmail;
	}

	public void setProvEmail(String provEmail) {
		this.provEmail = provEmail;
	}

	public String getProvPagWeb() {
		return this.provPagWeb;
	}

	public void setProvPagWeb(String provPagWeb) {
		this.provPagWeb = provPagWeb;
	}

	public String getProvRazonSocial() {
		return this.provRazonSocial;
	}

	public void setProvRazonSocial(String provRazonSocial) {
		this.provRazonSocial = provRazonSocial;
	}

	public String getProvRuc() {
		return this.provRuc;
	}

	public void setProvRuc(String provRuc) {
		this.provRuc = provRuc;
	}

	public String getProvTelefono() {
		return this.provTelefono;
	}

	public void setProvTelefono(String provTelefono) {
		this.provTelefono = provTelefono;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public Barrio getBarrio() {
		return barrio;
	}

	public void setBarrio(Barrio barrio) {
		this.barrio = barrio;
	}

}