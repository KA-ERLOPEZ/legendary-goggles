package com.sistema.pizzeria.elpirata.api.core.entities;

import com.sistema.pizzeria.elpirata.api.Enums.TipoEmpresa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "personas_juridicas")
@PrimaryKeyJoinColumn(name = "perId")
public class PersonaJuridica extends Persona {
		
	
	private static final long serialVersionUID = 1L;

	@Column(name = "per_ruc", unique = true, nullable = false, length = 13)
	private String perRazonSocial;
	
	@Column(name = "per_pag_web", unique = true, nullable = false, length = 100)
	private String perPagWeb;
	
	@Column(name = "nombre_fantasia", length = 200)
    private String nombreFantasia;

    @Column(name = "representante_legal", length = 150)
    private String representanteLegal;

    @Column(name = "nro_registro", length = 100)
    private String nroRegistro;

    @Enumerated(value = jakarta.persistence.EnumType.STRING)
    @Column(name = "tipo_empresa", length = 50)
    private TipoEmpresa tipoEmpresa;

    @Column(name = "sector", length = 100)
    private String sector;


    @Column(name = "observaciones", length = 500)
    private String observaciones;
	
	public String getPerRazonSocial() {
		return perRazonSocial;
	}
	
	public void setPerRazonSocial(String perRazonSocial) {
		this.perRazonSocial = perRazonSocial;
	}
	
	public String getPerPagWeb() {
		return perPagWeb;
	}
	
	public void setPerPagWeb(String perPagWeb) {
		this.perPagWeb = perPagWeb;
	}

	public String getNombreFantasia() {
		return nombreFantasia;
	}

	public void setNombreFantasia(String nombreFantasia) {
		this.nombreFantasia = nombreFantasia;
	}

	public String getRepresentanteLegal() {
		return representanteLegal;
	}

	public void setRepresentanteLegal(String representanteLegal) {
		this.representanteLegal = representanteLegal;
	}

	public String getNroRegistro() {
		return nroRegistro;
	}

	public void setNroRegistro(String nroRegistro) {
		this.nroRegistro = nroRegistro;
	}

	public TipoEmpresa getTipoEmpresa() {
		return tipoEmpresa;
	}

	public void setTipoEmpresa(TipoEmpresa tipoEmpresa) {
		this.tipoEmpresa = tipoEmpresa;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	

	
}
