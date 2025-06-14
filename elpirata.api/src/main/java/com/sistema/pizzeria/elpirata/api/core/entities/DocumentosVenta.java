package com.sistema.pizzeria.elpirata.api.core.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


/**
 * The persistent class for the documentos_ventas database table.
 * 
 */
@Entity
@Table(name="documentos_ventas")
@NamedQuery(name="DocumentosVenta.findAll", query="SELECT d FROM DocumentosVenta d")
public class DocumentosVenta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="documento_id")
	private long documentoId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="documento_fec_emision", nullable=false)
	private Date documentoFecEmision;

	@Column(name="documento_nro", nullable=false, length=50)
	private String documentoNro;
	
	@Column(name="venta_tipo", nullable=false)
	private String ventaTipo;

	@Column(name="venta_total", precision=10, scale=2, nullable=false)
	private BigDecimal ventaTotal;

	@Column(name="total_iva10", precision=10, scale=2, nullable=false)
	private BigDecimal totalIva10;

	@Column(name="total_iva5", precision=10, scale=2, nullable=false)
	private BigDecimal totalIva5;
	
	@Column(name="total_exentas", precision=10, scale=2, nullable=false)
	private BigDecimal totalExentas;

	//uni-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name="estado_id", nullable=false)
	private Estado estado;

	//bi-directional many-to-one association to Timbrado
	@ManyToOne
	@JoinColumn(name="timbrado_id", nullable=false)
	private Timbrado timbrado;

	//bi-directional many-to-one association to TipoDocumento
	@ManyToOne
	@JoinColumn(name="tipodoc_id", nullable=false)
	private TipoDocumento tipoDocumento;

	//uni-directional many-to-one association to VentaCabecera
	@ManyToOne
	@JoinColumn(name="venta_id", nullable=false)
	private VentaCabecera ventaCabecera;

	public DocumentosVenta() {
	}

	public long getDocumentoId() {
		return this.documentoId;
	}

	public void setDocumentoId(long documentoId) {
		this.documentoId = documentoId;
	}

	public Date getDocumentoFecEmision() {
		return this.documentoFecEmision;
	}

	public void setDocumentoFecEmision(Date documentoFecEmision) {
		this.documentoFecEmision = documentoFecEmision;
	}

	public String getDocumentoNro() {
		return this.documentoNro;
	}

	public void setDocumentoNro(String documentoNro) {
		this.documentoNro = documentoNro;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Timbrado getTimbrado() {
		return this.timbrado;
	}

	public void setTimbrado(Timbrado timbrado) {
		this.timbrado = timbrado;
	}

	public TipoDocumento getTipoDocumento() {
		return this.tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public VentaCabecera getVentaCabecera() {
		return this.ventaCabecera;
	}

	public void setVentaCabecera(VentaCabecera ventaCabecera) {
		this.ventaCabecera = ventaCabecera;
	}

}