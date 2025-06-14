package com.sistema.pizzeria.elpirata.api.core.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


/**
 * The persistent class for the timbrado database table.
 * 
 */
@Entity
@NamedQuery(name="Timbrado.findAll", query="SELECT t FROM Timbrado t")
public class Timbrado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="timbrado_id")
	private long timbradoId;

	@Temporal(TemporalType.DATE)
	@Column(name="timbrado_fecha_fin", nullable=false)
	private LocalDate timbradoFechaFin;

	@Temporal(TemporalType.DATE)
	@Column(name="timbrado_fecha_inic", nullable=false)
	private LocalDate timbradoFechaInic;

	@Column(name="timbrado_nro", length=50, nullable=false, unique=true)
	private String timbradoNro;
	
	@Column(name="timbrado_rango_inicial", nullable=false)
	private Integer rangoInicial;
	
	@Column(name="timbrado_rango_final", nullable=false)
	private Integer rangoFinal;

	@Temporal(TemporalType.DATE)
	@Column(name="timbrado_fecha_autorizacion", nullable=true)
	private LocalDate fechaAutorizacion;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="timbrado_fecha_registro", nullable=false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime fechaRegistro = LocalDateTime.now();
	
	//uni-directional many-to-one association to Sucursal
	@ManyToOne(optional=false)
	@JoinColumn(name="suc_id", nullable=false)
	private Sucursal sucursal;
	
	//uni-directional many-to-one association to Caja
	@ManyToOne(optional=false)
	@JoinColumn(name="caja_id", nullable=false)
	private Caja caja;
	
	//uni-directional many-to-one association to TipoDocumento
	@ManyToOne(optional=false)
	@JoinColumn(name="tipodoc_id", nullable=false)
	private TipoDocumento tipoDocumento;
	
	//bi-directional many-to-one association to DocumentosVenta
	@OneToMany(mappedBy="timbrado", orphanRemoval=true, cascade= {CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.EAGER)
	private List<DocumentosVenta> documentosVentas;

	//uni-directional many-to-one association to Estado
	@ManyToOne(optional=false)
	@JoinColumn(name="estado_id")
	private Estado estado;

	public Timbrado() {
	}

	public long getTimbradoId() {
		return this.timbradoId;
	}

	public void setTimbradoId(long timbradoId) {
		this.timbradoId = timbradoId;
	}

	public LocalDate getTimbradoFechaFin() {
		return this.timbradoFechaFin;
	}

	public void setTimbradoFechaFin(LocalDate timbradoFechaFin) {
		this.timbradoFechaFin = timbradoFechaFin;
	}

	public LocalDate getTimbradoFechaInic() {
		return this.timbradoFechaInic;
	}

	public void setTimbradoFechaInic(LocalDate timbradoFechaInic) {
		this.timbradoFechaInic = timbradoFechaInic;
	}

	public String getTimbradoNro() {
		return this.timbradoNro;
	}

	public void setTimbradoNro(String timbradoNro) {
		this.timbradoNro = timbradoNro;
	}

	public List<DocumentosVenta> getDocumentosVentas() {
		return this.documentosVentas;
	}

	public void setDocumentosVentas(List<DocumentosVenta> documentosVentas) {
		this.documentosVentas = documentosVentas;
	}

	public DocumentosVenta addDocumentosVenta(DocumentosVenta documentosVenta) {
		getDocumentosVentas().add(documentosVenta);
		documentosVenta.setTimbrado(this);

		return documentosVenta;
	}

	public DocumentosVenta removeDocumentosVenta(DocumentosVenta documentosVenta) {
		getDocumentosVentas().remove(documentosVenta);
		documentosVenta.setTimbrado(null);

		return documentosVenta;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public Sucursal getSucursal() {
		return this.sucursal;
	}		
	
	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}
	
	public Caja getCaja() {
		return this.caja;
	}
	
	public void setCaja(Caja caja) {
		this.caja = caja;
	}
	
	public TipoDocumento getTipoDocumento() {
		return this.tipoDocumento;
	}
	
	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	
	public Integer getRangoInicial() {
		return rangoInicial;
	}
	
	public void setRangoInicial(Integer rangoInicial) {
		this.rangoInicial = rangoInicial;
	}
	
	public Integer getRangoFinal() {
		return rangoFinal;
	}
	
	public void setRangoFinal(Integer rangoFinal) {
		this.rangoFinal = rangoFinal;
	}
	
	public LocalDate getFechaAutorizacion() {
		return fechaAutorizacion;
	}

	public void setFechaAutorizacion(LocalDate fechaAutorizacion) {
		this.fechaAutorizacion = fechaAutorizacion;
	}
	
	public LocalDateTime getFechaRegistro() {
		return fechaRegistro;
	}
	
	public void setFechaRegistro(LocalDateTime fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	
	

}