package com.sistema.pizzeria.elpirata.api.web.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;

public class BonificacionDTO {
	
	private long id;
	
	@NotNull(message = "El contrato es requerido")
	private long contratoId;
	
	private String nombre;
	
	private String apellido;
	
	private String nroCedula;
	
	@NotNull(message = "La fecha es requerida")
	@PastOrPresent(message = "La fecha tiene que ser anterio o igual a la fecha actual")
	private LocalDate fecha;
	
	@NotNull(message = "El monto es requerido")
	@DecimalMin(value = "0.0", message = "El precio tiene que ser mayor a 0")
	private BigDecimal monto;
	
	@NotNull(message = "El concepto es requerido")
	private long conceptoId;
	
	private String concepto;
	
	@Pattern(regexp = "^(?i)(pendiente|aprobado|rechazado|liquidado)$",
			 message = "El estado debe ser uno de: PENDIENTE, APROBADO, RECHAZADO o LIQUIDADO")
	private String estado;
	
	public BonificacionDTO() {
		
	}
	
	

	public BonificacionDTO(long id, long contratoId, String nombre, String apellido, String nroCedula, LocalDate fecha,
			BigDecimal monto, String concepto, String estado) {
		super();
		this.id = id;
		this.contratoId = contratoId;
		this.nombre = nombre;
		this.apellido = apellido;
		this.nroCedula = nroCedula;
		this.fecha = fecha;
		this.monto = monto;
		this.concepto = concepto;
		this.estado= estado;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getContratoId() {
		return contratoId;
	}

	public void setContratoId(long contratoId) {
		this.contratoId = contratoId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNroCedula() {
		return nroCedula;
	}

	public void setNroCedula(String nroCedula) {
		this.nroCedula = nroCedula;
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
	
	public long getConceptoId() {
		return conceptoId;
	}
	
	public void setConceptoId(long conceptoId) {
		this.conceptoId = conceptoId;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	

}
