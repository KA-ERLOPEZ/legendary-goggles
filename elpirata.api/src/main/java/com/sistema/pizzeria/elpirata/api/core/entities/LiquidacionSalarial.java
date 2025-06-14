package com.sistema.pizzeria.elpirata.api.core.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.sistema.pizzeria.elpirata.api.core.commons.Auditable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "liquidacion_salarial", uniqueConstraints = {
		@jakarta.persistence.UniqueConstraint(name = "uq_liquidacion_salarial", columnNames = { "cont_id", "liq_anio",
				"liq_mes" }) })
public class LiquidacionSalarial extends Auditable implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
	@Column(name = "liq_id")
	private long id;
	
	@Column(name = "liq_anio", nullable = false)
	private int anio;
	
	@Column(name = "liq_mes", nullable = false)
	private int mes;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "liq_fecha_liquidacion", nullable = false)
	private LocalDate fechaLiquidacion;
	
	@Column(name = "liq_total_bruto", nullable = false, precision = 10, scale = 2)
	private BigDecimal totalBruto;
	
	@Column(name = "liq_total_descuentos", nullable = false, precision = 10, scale = 2)
	private BigDecimal totalDescuentos;
	
	@Column(name = "liq_total_neto", nullable = false, precision = 10, scale = 2)
	private BigDecimal totalNeto;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "cont_id", nullable = false, referencedColumnName = "cont_id")
	private Contrato contrato;
	
	@OneToMany(mappedBy = "liquidacionSalarial", fetch = FetchType.EAGER, orphanRemoval = true, cascade = {
			CascadeType.PERSIST, CascadeType.MERGE 
	})
	private List<DetalleLiquidacionSalarial> detalleLiquidacionSalarial;

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

	public BigDecimal getTotalBruto() {
		return totalBruto;
	}
	
	public void setTotalBruto(BigDecimal totalBruto) {
		this.totalBruto = totalBruto;
	}

	public BigDecimal getTotalDescuentos() {
		return totalDescuentos;
	}

	public void setTotalDescuentos(BigDecimal totalDescuentos) {
		this.totalDescuentos = totalDescuentos;
	}

	public BigDecimal getTotalNeto() {
		return totalNeto;
	}

	public void setTotalNeto(BigDecimal totalNeto) {
		this.totalNeto = totalNeto;
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public List<DetalleLiquidacionSalarial> getDetalleLiquidacionSalarial() {
		return detalleLiquidacionSalarial;
	}

	public void setDetalleLiquidacionSalarial(List<DetalleLiquidacionSalarial> detalleLiquidacionSalarial) {
		this.detalleLiquidacionSalarial = detalleLiquidacionSalarial;
	}
	
	public LocalDate getFechaLiquidacion() {
        return fechaLiquidacion;
    }
	
	public void setFechaLiquidacion(LocalDate fechaLiquidacion) {
		this.fechaLiquidacion = fechaLiquidacion;
	}
	
	
	public DetalleLiquidacionSalarial addDetalleLiquidacionSalarial(DetalleLiquidacionSalarial detalle) {
		detalle.setLiquidacionSalarial(this);
		this.detalleLiquidacionSalarial.add(detalle);
		return detalle;
	}
	
	public DetalleLiquidacionSalarial removeDetalleLiquidacionSalarial(DetalleLiquidacionSalarial detalle) {
		detalle.setLiquidacionSalarial(null);
		this.detalleLiquidacionSalarial.remove(detalle);
		return detalle;
	}
	
}
