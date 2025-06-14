package com.sistema.pizzeria.elpirata.api.core.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "detalle_liquidacion_salarial", uniqueConstraints = 
        {
		@UniqueConstraint(columnNames = {"liq_id", "bon_id"}, name = "uk_detalle_liquidacion_salarial_1"),
		@UniqueConstraint(columnNames = {"liq_id", "des_id"}, name = "uk_detalle_liquidacion_salarial_2"),
		@UniqueConstraint(columnNames = { "liq_id", "hor_id" }, name = "uk_detalle_liquidacion_salarial_3") })
public class DetalleLiquidacionSalarial implements Serializable {

	

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "det_id")
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "liq_id", nullable = false, referencedColumnName = "liq_id")
    private LiquidacionSalarial liquidacionSalarial;
    
    @Column(name = "det_concepto", nullable = false, length = 50)
    private Concepto concepto;

    @Column(name = "det_monto", nullable = false, precision = 10, scale = 2)
    private BigDecimal monto;

    @Enumerated(EnumType.STRING)
    @Column(name = "det_tipo", nullable = false, length = 30)
    private TipoConcepto tipo;

    @Column(name = "det_observacion", length = 255)
    private String observacion;

    @Column(name = "det_imponible", nullable = false)
    private boolean imponible;

    @Column(name = "det_orden")
    private Integer orden;

    // Relaciones opcionales para trazabilidad
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hor_id", referencedColumnName = "hor_id")
    private HoraExtra horaExtra;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "des_id", referencedColumnName = "des_id")
    private Descuento descuento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bon_id", referencedColumnName = "bon_id")
    private Bonificacion bonificacion;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public LiquidacionSalarial getLiquidacionSalarial() {
		return liquidacionSalarial;
	}
	
	public void setLiquidacionSalarial(LiquidacionSalarial liquidacionSalarial) {
		this.liquidacionSalarial = liquidacionSalarial;
	}
	
	public Concepto getConcepto() {
		return concepto;
	}
	
	public void setConcepto(Concepto concepto) {
		this.concepto = concepto;
	}
	
	public BigDecimal getMonto() {
		return monto;
	}
	
	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}
	
	public TipoConcepto getTipo() {
		return tipo;
	}
	
	public void setTipo(TipoConcepto tipo) {
		this.tipo = tipo;
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

	public Bonificacion getBonificacion() {
		return bonificacion;
	}

	public void setBonificacion(Bonificacion bonificacion) {
		this.bonificacion = bonificacion;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getObservacion() {
		return observacion;
	}
	
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
	public HoraExtra getHoraExtra() {
		return horaExtra;
	}
	
	public void setHoraExtra(HoraExtra horaExtra) {
		this.horaExtra = horaExtra;
	}
	
	public Descuento getDescuento() {
		return descuento;
	}
	
	public void setDescuento(Descuento descuento) {
		this.descuento = descuento;
	}
	
	public Bonificacion getAsignacion() {
		return bonificacion;
	}
	
	public void setAsignacion(Bonificacion bonificacion) {
		this.bonificacion = bonificacion;
	}
	
	
	
	
}
