package com.sistema.pizzeria.elpirata.api.core.entities;

import java.io.Serializable;

import com.sistema.pizzeria.elpirata.api.core.commons.Auditable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "conceptos")
public class Concepto extends Auditable implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "con_id")
	private long id;
	
	@Column(name = "con_nombre", length = 50, nullable = false, unique = true)
	private String nombre;
	
	@Column(name = "con_descripcion", length = 200, nullable = true)
	private String descripcion;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "dominio_id", nullable = false, referencedColumnName = "dominio_id")
	private Dominio dominio;
	
	@Column(name = "con_activo", nullable = false)
	private boolean activo = true;
	
	public Concepto() {
		
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public Dominio getDominio() {
		return dominio;
	}
	
	public void setDominio(Dominio dominio) {
		this.dominio = dominio;
	}

}
