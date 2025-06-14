package com.sistema.pizzeria.elpirata.api.web.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CargoDTO {
	
	private long cargoId;
	
	@NotNull(message = "El nombre del cargo es obligatorio")
	@Size(min = 1, max = 50, message = "El nombre del cargo debe tener entre 1 y 50 caracteres")
	private String cargoNombre;
	
	private boolean cargoEnabled = true;
	
	public CargoDTO() {
		super();
	}
	
	public CargoDTO(long cargoId, String cargoNombre, boolean cargoEnabled) {
		super();
		this.cargoId = cargoId;
		this.cargoNombre = cargoNombre;
		this.cargoEnabled = cargoEnabled;
	}
	
	public long getCargoId() {
		return cargoId;
	}
	
	public void setCargoId(long cargoId) {
		this.cargoId = cargoId;
	}
	
	public String getCargoNombre() {
		return cargoNombre;
	}
	
	public void setCargoNombre(String cargoNombre) {
		this.cargoNombre = cargoNombre;
	}
	
	public boolean isCargoEnabled() {
		return cargoEnabled;
	}
	
	public void setCargoEnabled(boolean cargoEnabled) {
		this.cargoEnabled = cargoEnabled;
	}
	
}
