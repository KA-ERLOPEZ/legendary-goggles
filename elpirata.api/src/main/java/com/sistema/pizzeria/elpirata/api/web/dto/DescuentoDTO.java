package com.sistema.pizzeria.elpirata.api.web.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

public class DescuentoDTO {
	
	private long id;
	
	@NotNull(message = "El contrato no puede ser nulo")
	private ContratoDTO contrato;
	
	@NotNull(message = "La fecha no puede ser nula")
	@PastOrPresent(message = "La fecha debe ser pasada o presente")
	private LocalDate fecha;
	
	@NotNull(message = "El monto no puede ser nulo")
	@DecimalMin(value = "0.01", message = "El monto debe ser mayor que cero")
	@DecimalMax(value = "999999.99", message = "El monto debe ser menor que 1,000,000")
	private BigDecimal monto;
	
	@NotNull(message = "El concepto no puede ser nulo")
	private long conceptoId;
	
	private String conceptoNombre;
	
	private String estado;
	
	public DescuentoDTO() {
		// Constructor por defecto
	}
	
	public DescuentoDTO(long id, ContratoDTO contrato, LocalDate fecha, BigDecimal monto, long conceptoId, String conceptoNombre,
			String estado) {
		this.id = id;
		this.contrato = contrato;
		this.fecha = fecha;
		this.monto = monto;
		this.conceptoNombre = conceptoNombre;
		this.estado = estado;
		this.conceptoId = conceptoId;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ContratoDTO getContrato() {
		return contrato;
	}

	public void setContrato(ContratoDTO contrato) {
		this.contrato = contrato;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public String getConceptoNombre() {
		return conceptoNombre;
	}
	
	public long getConceptoId() {
		return conceptoId;
	}
	
	public void setConceptoId(long conceptoId) {
		this.conceptoId = conceptoId;
	}

	public void setConceptoNombre(String conceptoNombre) {
		this.conceptoNombre = conceptoNombre;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	

}
