package com.sistema.pizzeria.elpirata.api.core.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.sistema.pizzeria.elpirata.api.Enums.VentaTipo;

import jakarta.persistence.CascadeType;
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
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


/**
 * The persistent class for the venta_cabecera database table.
 * 
 */
@Entity
@Table(name="venta_cabecera")
@NamedQuery(name="VentaCabecera.findAll", query="SELECT v FROM VentaCabecera v")
public class VentaCabecera implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="venta_id")
	private long ventaId;	
	
	@Temporal(TemporalType.DATE)
	@Column(name="venta_fecha", nullable=false)
	private Date ventaFecha;
	
	//uni-directional many-to-one association to Persona
	@ManyToOne( optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name="cliente_id", nullable=false, referencedColumnName="per_id")
	private Persona cliente;
	
	@Column(name="venta_numero_factura", length=20, nullable=false)
	private String ventaNumeroFactura;
	
	@Column(name="numero_comprobante", length=20, nullable=false)
	private Integer numeroComprobante;
	
	
	@ManyToOne( optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name="timbrado_id", nullable=false, referencedColumnName="timbrado_id")
	private Timbrado timbrado;
	
	@Enumerated(EnumType.STRING)
	@Column(name="venta_tipo", nullable=false)
	private VentaTipo ventaTipo;
	
	@Column(name="cantidad_cuotas", nullable=false)
	private Integer cantidadCuotas;
	
	@Column(name="venta_total", precision=10, scale=2, nullable=false)
	private BigDecimal ventaTotal;
	
	@Column(name="venta_total_iva_5", precision=10, scale=2, nullable=false)
	private BigDecimal ventaTotalIva5;
	
	@Column(name="venta_total_iva_10", precision=10, scale=2, nullable=false)
	private BigDecimal ventaTotalIva10;
	
	@Column(name="total_exentas", precision=10, scale=2, nullable=false)
	private BigDecimal totalExentas;
	
	@Column(name="monto_iva_5", precision=10, scale=2, nullable=false)
	private BigDecimal montoIva5;
	
	@Column(name="monto_iva_10", precision=10, scale=2, nullable=false)
	private BigDecimal montoIva10;
	
	@Column(name="total_iva", precision=10, scale=2, nullable=false)
	private BigDecimal totalIva;
	
	@Column(name="total_descuentos", precision=10, scale=2, nullable=false)
	private BigDecimal totalDescuentos;
	
	//uni-directional many-to-one association to CajaAperturaCierre
	@ManyToOne
	@JoinColumn(name="caja_id", nullable=false, referencedColumnName="caja_id")
	private Caja caja;

	//uni-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name="estado_id", nullable=false, referencedColumnName="estado_id")
	private Estado estado;

	//uni-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="usu_id", nullable=false, referencedColumnName="usu_id")
	private Usuario usuario;

	//bi-directional many-to-one association to VentaDetalle
	@OneToMany(mappedBy="ventaCabecera", orphanRemoval=true, cascade= {CascadeType.PERSIST, CascadeType.MERGE}, fetch=FetchType.EAGER)
	private List<VentaDetalle> ventaDetalles;
	
	//bi-directional many-to-one association to CuentasPorCobrar
	@OneToMany(mappedBy="ventaCabecera", cascade= {CascadeType.MERGE, CascadeType.PERSIST}, fetch=FetchType.LAZY, orphanRemoval=true)
	private List<CuentasPorCobrar> cuentasPorCobrar;

	public VentaCabecera() {
	}

	public long getVentaId() {
		return this.ventaId;
	}

	public void setVentaId(long ventaId) {
		this.ventaId = ventaId;
	}

	public Persona getCliente() {
		return this.cliente;
	}
	
	public void setCliente(Persona cliente) {
		this.cliente = cliente;
	}

	public BigDecimal getTotalExentas() {
		return this.totalExentas;
	}

	public void setTotalExentas(BigDecimal totalExentas) {
		this.totalExentas = totalExentas;
	}

	public Date getVentaFecha() {
		return this.ventaFecha;
	}

	public void setVentaFecha(Date ventaFecha) {
		this.ventaFecha = ventaFecha;
	}



	public BigDecimal getVentaTotal() {
		return this.ventaTotal;
	}

	public void setVentaTotal(BigDecimal ventaTotal) {
		this.ventaTotal = ventaTotal;
	}

	public BigDecimal getVentaTotalIva10() {
		return this.ventaTotalIva10;
	}

	public void setVentaTotalIva10(BigDecimal ventaTotalIva10) {
		this.ventaTotalIva10 = ventaTotalIva10;
	}

	public BigDecimal getVentaTotalIva5() {
		return this.ventaTotalIva5;
	}

	public void setVentaTotalIva5(BigDecimal ventaTotalIva5) {
		this.ventaTotalIva5 = ventaTotalIva5;
	}

	public Timbrado getTimbrado() {
		return this.timbrado;
	}
	
	public void setTimbrado(Timbrado timbrado) {
		this.timbrado = timbrado;
	}
	
	public VentaTipo getVentaTipo() {
		return this.ventaTipo;
	}
	
	public void setVentaTipo(VentaTipo ventaTipo) {
		this.ventaTipo = ventaTipo;
	}
	
	public Caja getCaja() {
		return this.caja;
	}
	
	public void setCaja(Caja caja) {
		this.caja = caja;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public BigDecimal getMontoIva5() {
		return this.montoIva5;
	}
	
	public void setMontoIva5(BigDecimal montoIva5) {
		this.montoIva5 = montoIva5;
	}
	
	public BigDecimal getMontoIva10() {
		return this.montoIva10;
	}
	
	public void setMontoIva10(BigDecimal montoIva10) {
		this.montoIva10 = montoIva10;
	}
	
	public BigDecimal getTotalIva() {
		return this.totalIva;
	}
	
	public void setTotalIva(BigDecimal totalIva) {
		this.totalIva = totalIva;
	}
	
	public BigDecimal getTotalDescuentos() {
		return this.totalDescuentos;
	}
	
	public void setTotalDescuentos(BigDecimal totalDescuentos) {
		this.totalDescuentos = totalDescuentos;
	}
	
	public String getVentaNumeroFactura() {
		return this.ventaNumeroFactura;
	}
	
	public void setVentaNumeroFactura(String ventaNumeroFactura) {
		this.ventaNumeroFactura = ventaNumeroFactura;
	}
	
	public Integer getNumeroComprobante() {
		return this.numeroComprobante;
	}
	
	public void setNumeroComprobante(Integer numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
	}
	
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Integer getCantidadCuotas() {
		return this.cantidadCuotas;
	}
	
	public void setCantidadCuotas(Integer cantidadCuotas) {
		this.cantidadCuotas = cantidadCuotas;
	}
	


	public List<VentaDetalle> getVentaDetalles() {
		return this.ventaDetalles;
	}

	public void setVentaDetalles(List<VentaDetalle> ventaDetalles) {
		this.ventaDetalles = ventaDetalles;
	}

	public VentaDetalle addVentaDetalle(VentaDetalle ventaDetalle) {
		getVentaDetalles().add(ventaDetalle);
		ventaDetalle.setVentaCabecera(this);

		return ventaDetalle;
	}

	public VentaDetalle removeVentaDetalle(VentaDetalle ventaDetalle) {
		getVentaDetalles().remove(ventaDetalle);
		ventaDetalle.setVentaCabecera(null);

		return ventaDetalle;
	}
	

}