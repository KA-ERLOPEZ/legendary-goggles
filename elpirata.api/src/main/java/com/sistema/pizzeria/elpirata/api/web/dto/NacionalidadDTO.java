package com.sistema.pizzeria.elpirata.api.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;


public class NacionalidadDTO {

	private long nacId;
	
	@NotBlank(message = "El estado de la nacionalidad no puede estar vacío")
	@NotNull(message = "El estado de la nacionalidad no puede estar vacío")
	@Pattern(regexp = "true|false", message = "El estado de la nacionalidad debe ser verdadero o falso")
	private boolean nacEnabled;

	@NotBlank(message = "El nombre de la nacionalidad no puede estar vacío")
	@NotNull(message = "El nombre de la nacionalidad no puede estar vacío")
	@Pattern(regexp = "^[a-zA-Z\\s]+$", message = "El nombre de la nacionalidad solo puede contener letras y espacios")
	private String nacNombre;
	
	public NacionalidadDTO() {

	}
	
	public NacionalidadDTO(long nacId, boolean nacEnabled, String nacNombre) {
		this.nacId = nacId;
		this.nacEnabled = nacEnabled;
		this.nacNombre = nacNombre;
	}

	public long getNacId() {
		return nacId;
	}

	public void setNacId(long nacId) {
		this.nacId = nacId;
	}

	public boolean isNacEnabled() {
		return nacEnabled;
	}

	public void setNacEnabled(boolean nacEnabled) {
		this.nacEnabled = nacEnabled;
	}

	public String getNacNombre() {
		return nacNombre;
	}

	public void setNacNombre(String nacNombre) {
		this.nacNombre = nacNombre;
	}

}
