package com.sistema.pizzeria.elpirata.api.core.entities;


import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.NoArgsConstructor;


/**
 * The persistent class for the dominio database table.
 * 
 */


@NoArgsConstructor
@Entity
@NamedQuery(name = "Dominio.findAll", query = "SELECT d FROM Dominio d")
public class Dominio implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dominio_id")
    private long dominioId;

    @NotNull(message = "El código del dominio no puede ser nulo.")
    @Size(max = 50, message = "El código del dominio no puede exceder 50 caracteres.")
    @Column(name = "dominio_codigo", nullable = false, unique = true, length = 50)
    private String dominioCodigo;

    @NotNull(message = "El estado del dominio no puede ser nulo.")
    @Column(name = "dominio_enabled", nullable = false)
    private boolean dominioEnabled;

    @NotNull(message = "El nombre del dominio no puede ser nulo.")
    @Size(max = 100, message = "El nombre del dominio no puede exceder 100 caracteres.")
    @Column(name = "dominio_nombre", nullable = false, length = 100)
    private String dominioNombre;

	public long getDominioId() {
		return dominioId;
	}

	public void setDominioId(long dominioId) {
		this.dominioId = dominioId;
	}

	public String getDominioCodigo() {
		return dominioCodigo;
	}

	public void setDominioCodigo(String dominioCodigo) {
		this.dominioCodigo = dominioCodigo;
	}

	public boolean isDominioEnabled() {
		return dominioEnabled;
	}

	public void setDominioEnabled(boolean dominioEnabled) {
		this.dominioEnabled = dominioEnabled;
	}

	public String getDominioNombre() {
		return dominioNombre;
	}

	public void setDominioNombre(String dominioNombre) {
		this.dominioNombre = dominioNombre;
	}

    
}
