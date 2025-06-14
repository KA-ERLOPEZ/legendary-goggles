package com.sistema.pizzeria.elpirata.api.web.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import com.sistema.pizzeria.elpirata.api.core.validations.anotations.RangoHorarioValido;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;

@RangoHorarioValido
public class HoraExtraDTO {
	
	private long id;
	
	@NotNull(message = "El contrato no puede ser nulo")
	private ContratoDTO contrato;
	
	@NotNull(message = "La fecha no puede ser nula")
	@PastOrPresent(message = "La fecha debe ser en el pasado o presente")
	private LocalDate fecha;
	
	@NotNull(message = "La cantidad de horas no puede ser nula")
	@FutureOrPresent(message = "La cantidad de horas debe ser en el futuro o presente")
	private LocalTime horaInicio;
	
	@NotNull(message = "La cantidad de horas no puede ser nula")
	@FutureOrPresent(message = "La cantidad de horas debe ser en el futuro o presente")
	private LocalTime horaFin;
	
	@NotNull(message = "El porcentaje de pago no puede ser nulo")
	@Pattern(regexp = "^\\d+(\\.\\d{1,2})?$", message = "El porcentaje de pago debe ser un número válido con hasta 2 decimales")
	private BigDecimal procentajePago;
	
	@NotNull(message = "El estado no puede ser nulo")
	@Pattern(regexp = "^(PENDIENTE |APROBADO |RECHAZADO |LIQUIDADO)$", message = "El estado debe ser 'PENDIENTE', 'APROBADO', 'RECHAZADO' o 'LIQUIDADO'")
	private String estado;
	
	@NotNull(message = "La observación no puede ser nula")
    @Pattern(regexp = "^.{0,255}$", message = "La observación debe tener un máximo de 255 caracteres")
	private String observacion;
	
	public HoraExtraDTO() {
		super();
	}
	
	public HoraExtraDTO(long id, ContratoDTO contrato, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin, BigDecimal procentajePago,
			String estado, String observacion) {
		super();
		this.id = id;
		this.contrato = contrato;
		this.fecha = fecha;
		this.procentajePago = procentajePago;
		this.estado = estado;
		this.observacion = observacion;
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

	public LocalTime getHoraInicio() {
		return horaInicio;
	}
	
	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}
	
	public LocalTime getHoraFin() {
		return horaFin;
	}
	
	public void setHoraFin(LocalTime horaFin) {
		this.horaFin = horaFin;
	}

	public BigDecimal getProcentajePago() {
		return procentajePago;
	}

	public void setProcentajePago(BigDecimal procentajePago) {
		this.procentajePago = procentajePago;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
}
