package com.sistema.pizzeria.elpirata.api.web.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class DetalleLiquidacionSalarialDTO {

	private long id;
	
	@NotNull(message = "El ID de liquidación salarial no puede ser nulo")
	private int LiquidacionSalarialId;
	
	@NotNull(message = "El concepto no puede ser nulo")
	private long conceptoId;
	
	@NotNull(message = "El concepto no puede ser nulo")
	private String conceptoNombre; 
	
	@NotNull(message = "El monto no debe estar vacio")
	@Pattern(regexp = "^[0-9]+(\\.[0-9]{1,2})?$", message = "El monto debe ser un número válido con hasta dos decimales")
	private BigDecimal monto;
	
	@NotNull(message = "El tipo no puede ser nulo")
	private String tipo;
	
	private String observacion;
	
	private boolean imponible;
	
	private Integer orden;
	
	private long horasExtrasId;
	
	
	private long bonificacionId;
	
	private long descuentoId;

	public DetalleLiquidacionSalarialDTO() {
	}
	
	public DetalleLiquidacionSalarialDTO(long id, int liquidacionSalarialId, String conceptoNombre, BigDecimal monto, String tipo,
			String observacion, boolean imponible, Integer orden, long horasExtrasId, long bonificacionId,
			long descuentoId) {
		this.id = id;
		this.LiquidacionSalarialId = liquidacionSalarialId;
		this.conceptoNombre = conceptoNombre;
		this.monto = monto;
		this.tipo = tipo;
		this.observacion = observacion;
		this.imponible = imponible;
		this.orden = orden;
		this.horasExtrasId = horasExtrasId;
		this.bonificacionId = bonificacionId;
		this.descuentoId = descuentoId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getConceptoNombre() {
		return conceptoNombre;
	}

	public void setConceptoNombre(String conceptoNombre) {
		this.conceptoNombre = conceptoNombre;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public boolean isImponible() {
		return imponible;
	}

	public void setImponible(boolean imponible) {
		this.imponible = imponible;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}
	
	public long getHorasExtrasId() {
		return horasExtrasId;
	}
	
	public void setHorasExtrasId(long horasExtrasId) {
		this.horasExtrasId = horasExtrasId;
	}
	
	public long getBonificacionId() {
		return bonificacionId;
	}
	
	public void setBonificacionId(long bonificacionId) {
		this.bonificacionId = bonificacionId;
	}
	
	public long getDescuentoId() {
		return descuentoId;
	}
	
	public void setDescuentoId(long descuentoId) {
		this.descuentoId = descuentoId;
	}
	
	public int getLiquidacionSalarialId() {
		return LiquidacionSalarialId;
	}
	
	public void setLiquidacionSalarialId(int liquidacionSalarialId) {
		LiquidacionSalarialId = liquidacionSalarialId;
	}
	
	public long getConceptoId() {
		return conceptoId;
	}
	
	public void setConceptoId(long conceptoId) {
		this.conceptoId = conceptoId;
	}
	
	
	
}
