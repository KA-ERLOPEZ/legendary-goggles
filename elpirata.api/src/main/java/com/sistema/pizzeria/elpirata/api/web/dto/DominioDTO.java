package com.sistema.pizzeria.elpirata.api.web.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor @NoArgsConstructor
public class DominioDTO {
	private long dominioId;

	@NotNull(message = "El código del dominio no puede ser nulo.")
	@Size(max = 50, message = "El código del dominio no puede exceder 50 caracteres.")
	private String dominioCodigo;

	@NotNull(message = "El estado del dominio no puede ser nulo.")
	private boolean dominioEnabled;

	@NotNull(message = "El nombre del dominio no puede ser nulo.")
	@Size(max = 100, message = "El nombre del dominio no puede exceder 100 caracteres.")
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
