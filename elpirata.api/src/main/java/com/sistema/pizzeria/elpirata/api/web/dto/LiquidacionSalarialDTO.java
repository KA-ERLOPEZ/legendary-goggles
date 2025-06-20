package com.sistema.pizzeria.elpirata.api.web.dto;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;

public class LiquidacionSalarialDTO {
	
	private long id;
	
	@NotNull(message = "El año es obligatorio")
	@Pattern(regexp = "^[0-9]{4}$", message = "El año debe ser un número de 4 dígitos")
	@Min(value= 1900, message = "El año debe ser mayor o igual a 1900")
	@Max(value= 2100, message = "El año debe ser menor o igual a 2100")
	private int anio;
	
	@NotNull(message = "El mes es obligatorio")
	@Min(value = 1, message = "El mes debe ser un número entre 1 y 12")
	@Max(value = 12, message = "El mes debe ser un número entre 1 y 12")
	private int mes;
	
	@NotNull(message = "La fecha de liquidación es obligatoria")
	@PastOrPresent(message = "La fecha de liquidación debe ser una fecha pasada o presente")
	@Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "La fecha de liquidación debe tener el formato YYYY-MM-DD")
	private LocalDate fechaLiquidacion;
	
	@NotNull(message = "El total bruto es obligatorio")
	@Min(value = 0, message = "El total bruto no puede ser negativo")
	@Max(value = (long) Double.MAX_VALUE, message = "El total bruto debe ser un número válido")
	@Pattern(regexp = "^\\d+(\\.\\d{1,2})?$", message = "El total bruto debe ser un número con hasta 2 decimales")
	private double totalBruto;
	
	@NotNull(message = "El total de descuentos es obligatorio")
	@Min(value = 0, message = "El total de descuentos no puede ser negativo")
	@Max(value = (long) Double.MAX_VALUE, message = "El total de descuentos debe ser un número válido")
	@Pattern(regexp = "^\\d+(\\.\\d{1,2})?$", message = "El total de descuentos debe ser un número con hasta 2 decimales")
	private double totalDescuentos;
	
	@NotNull(message = "El total neto es obligatorio")
	@Min(value = 0, message = "El total neto no puede ser negativo")
	@Max(value = (long) Double.MAX_VALUE, message = "El total neto debe ser un número válido")
	@Pattern(regexp = "^\\d+(\\.\\d{1,2})?$", message = "El total neto debe ser un número con hasta 2 decimales")
	private double totalNeto;
	
	@NotNull(message = "El ID del contrato es obligatorio")
	private long contratoId;
	
	private String nombreEmpleado;
	
	private String apellidoEmpleado;
	
	private String ciEmpleado;
	
	private List<DetalleLiquidacionSalarialDTO> detalleLiquidacionSalarial;
	
	public LiquidacionSalarialDTO() {
		// Constructor por defecto
	}
	
	/**
	 * Constructor con todos los parámetros.
	 * 
	 * @param id               el ID de la liquidación salarial
	 * @param anio             el año de la liquidación
	 * @param mes              el mes de la liquidación
	 * @param fechaLiquidacion la fecha de liquidación
	 * @param totalBruto       el total bruto de la liquidación
	 * @param totalDescuentos  el total de descuentos aplicados
	 * @param totalNeto        el total neto a pagar
	 * @param contratoId       el ID del contrato asociado
	 * @param nombreEmpleado   el nombre del empleado
	 * @param apellidoEmpleado el apellido del empleado
	 * @param ciEmpleado       la cédula de identidad del empleado
	 */
	public LiquidacionSalarialDTO(long id, int anio, int mes, LocalDate fechaLiquidacion, double totalBruto,
			double totalDescuentos, double totalNeto, long contratoId, String nombreEmpleado, String apellidoEmpleado,
			String ciEmpleado) {
		this.id = id;
		this.anio = anio;
		this.mes = mes;
		this.fechaLiquidacion = fechaLiquidacion;
		this.totalBruto = totalBruto;
		this.totalDescuentos = totalDescuentos;
		this.totalNeto = totalNeto;
		this.contratoId = contratoId;
		this.nombreEmpleado = nombreEmpleado;
		this.apellidoEmpleado = apellidoEmpleado;
		this.ciEmpleado = ciEmpleado;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public LocalDate getFechaLiquidacion() {
		return fechaLiquidacion;
	}

	public void setFechaLiquidacion(LocalDate fechaLiquidacion) {
		this.fechaLiquidacion = fechaLiquidacion;
	}

	public double getTotalBruto() {
		return totalBruto;
	}

	public void setTotalBruto(double totalBruto) {
		this.totalBruto = totalBruto;
	}

	public double getTotalDescuentos() {
		return totalDescuentos;
	}

	public void setTotalDescuentos(double totalDescuentos) {
		this.totalDescuentos = totalDescuentos;
	}

	public double getTotalNeto() {
		return totalNeto;
	}

	public void setTotalNeto(double totalNeto) {
		this.totalNeto = totalNeto;
	}

	public long getContratoId() {
		return contratoId;
	}

	public void setContratoId(long contratoId) {
		this.contratoId = contratoId;
	}

	public String getNombreEmpleado() {
		return nombreEmpleado;
	}

	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}

	public String getApellidoEmpleado() {
		return apellidoEmpleado;
	}

	public void setApellidoEmpleado(String apellidoEmpleado) {
		this.apellidoEmpleado = apellidoEmpleado;
	}

	public String getCiEmpleado() {
		return ciEmpleado;
	}

	public void setCiEmpleado(String ciEmpleado) {
		this.ciEmpleado = ciEmpleado;
	}
	
	public List<DetalleLiquidacionSalarialDTO> getDetalleLiquidacionSalarial() {
		return detalleLiquidacionSalarial;
	}
	
	public void setDetalleLiquidacionSalarial(List<DetalleLiquidacionSalarialDTO> detalleLiquidacionSalarial) {
		this.detalleLiquidacionSalarial = detalleLiquidacionSalarial;
	}
	

}
