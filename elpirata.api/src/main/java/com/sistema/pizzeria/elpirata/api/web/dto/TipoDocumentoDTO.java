package com.sistema.pizzeria.elpirata.api.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class TipoDocumentoDTO {
	
	@Pattern(regexp = "^[0-9]+$", message = "El campo id debe ser un numero")
	private long tipodocId;
	
	@NotNull(message = "El campo descripcion es obligatorio")
	@NotBlank(message = "El campo descripcion no puede estar vacio")
	@Size(min = 5, max = 100, message = "El campo descripcion debe tener entre 5 y 100 caracteres")
	private String tipodocDescripcion;
	
	@NotNull(message = "El campo ley nro es obligatorio")
	@NotBlank(message = "El campo ley nro no puede estar vacio")
	@Size(min = 5, max = 100, message = "El campo ley nro debe tener entre 5 y 100 caracteres")
	private String tipodocLeyNro;
	
	@NotNull(message = "El campo nombre es obligatorio")
	@NotBlank(message = "El campo nombre no puede estar vacio")
	@Size(min = 5, max = 100, message = "El campo nombre debe tener entre 5 y 100 caracteres")
	private String tipodocNombre;
	
	@NotNull(message = "El campo estado es obligatorio")
	@NotBlank(message = "El campo estado no puede estar vacio")
	@Pattern(regexp = "^[0-9]+$", message = "El campo estado debe ser un numero")
	private int estadoId;
	private String estadoNombre;
	
	public TipoDocumentoDTO() {
		super();
	}

	public TipoDocumentoDTO(long tipodocId, String tipodocDescripcion, String tipodocLeyNro, String tipodocNombre,
			int estadoId, String estadoNombre) {
		super();
		this.tipodocId = tipodocId;
		this.tipodocDescripcion = tipodocDescripcion;
		this.tipodocLeyNro = tipodocLeyNro;
		this.tipodocNombre = tipodocNombre;
		this.estadoId = estadoId;
		this.estadoNombre = estadoNombre;
	}
	
	public long getTipodocId() {
		return tipodocId;
	}

	public void setTipodocId(long tipodocId) {
		this.tipodocId = tipodocId;
	}

	public String getTipodocDescripcion() {
		return tipodocDescripcion;
	}

	public void setTipodocDescripcion(String tipodocDescripcion) {
		this.tipodocDescripcion = tipodocDescripcion;
	}

	public String getTipodocLeyNro() {
		return tipodocLeyNro;
	}

	public void setTipodocLeyNro(String tipodocLeyNro) {
		this.tipodocLeyNro = tipodocLeyNro;
	}

	public String getTipodocNombre() {
		return tipodocNombre;
	}

	public void setTipodocNombre(String tipodocNombre) {
		this.tipodocNombre = tipodocNombre;
	}

	public int getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(int estadoId) {
		this.estadoId = estadoId;
	}

	public String getEstadoNombre() {
		return estadoNombre;
	}

	public void setEstadoNombre(String estadoNombre) {
		this.estadoNombre = estadoNombre;
	}

}
