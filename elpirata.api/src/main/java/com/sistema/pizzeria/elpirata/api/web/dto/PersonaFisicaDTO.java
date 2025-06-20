package com.sistema.pizzeria.elpirata.api.web.dto;

import java.util.Date;

public class PersonaFisicaDTO {

    private Long perId;

    private String perNombre;

    private String perApellido;

    private String perNroCedula;

    private String perEmail;

    private String perTelefono;

    private String perDireccion;

    private boolean perEnabled;

    private String perNroRuc;

    private Date perFecNac;

    private Long barId;
    
    private String barNombre;  

    private Long nacId;
    
    private String nacNombre;

    public PersonaFisicaDTO() {
    }

    public PersonaFisicaDTO(Long perId, String perNombre, String perApellido, String perNroCedula,
                             String perEmail, String perTelefono, String perDireccion, boolean perEnabled,
                             String perNroRuc, Date perFecNac, Long barId, Long nacId, String nacNombre) {
        this.perId = perId;
        this.perNombre = perNombre;
        this.perApellido = perApellido;
        this.perNroCedula = perNroCedula;
        this.perEmail = perEmail;
        this.perTelefono = perTelefono;
        this.perDireccion = perDireccion;
        this.perEnabled = perEnabled;
        this.perNroRuc = perNroRuc;
        this.perFecNac = perFecNac;
        this.barId = barId;
        this.barId = barId;
        this.nacId = nacId;
        this.nacNombre = nacNombre;
    }

    // 👉 Getters y Setters

    public Long getPerId() {
        return perId;
    }

    public void setPerId(Long perId) {
        this.perId = perId;
    }

    public String getPerNombre() {
        return perNombre;
    }

    public void setPerNombre(String perNombre) {
        this.perNombre = perNombre;
    }

    public String getPerApellido() {
        return perApellido;
    }

    public void setPerApellido(String perApellido) {
        this.perApellido = perApellido;
    }

    public String getPerNroCedula() {
        return perNroCedula;
    }

    public void setPerNroCedula(String perNroCedula) {
        this.perNroCedula = perNroCedula;
    }

    public String getPerEmail() {
        return perEmail;
    }

    public void setPerEmail(String perEmail) {
        this.perEmail = perEmail;
    }

    public String getPerTelefono() {
        return perTelefono;
    }

    public void setPerTelefono(String perTelefono) {
        this.perTelefono = perTelefono;
    }

    public String getPerDireccion() {
        return perDireccion;
    }

    public void setPerDireccion(String perDireccion) {
        this.perDireccion = perDireccion;
    }

    public boolean isPerEnabled() {
        return perEnabled;
    }

    public void setPerEnabled(boolean perEnabled) {
        this.perEnabled = perEnabled;
    }

    public String getPerNroRuc() {
        return perNroRuc;
    }

    public void setPerNroRuc(String perNroRuc) {
        this.perNroRuc = perNroRuc;
    }

    public Date getPerFecNac() {
        return perFecNac;
    }

    public void setPerFecNac(Date perFecNac) {
        this.perFecNac = perFecNac;
    }

    public Long getBarId() {
        return barId;
    }
    

    public void setBarId(Long barId) {
        this.barId = barId;
    }

    
    public String getBarNombre() {
		return barNombre;
	}

	public void setBarNombre(String barNombre) {
		this.barNombre = barNombre;
	}

	public Long getNacId() {
        return nacId;
    }
    

    public void setNacId(Long nacId) {
        this.nacId = nacId;
    }

	public String getNacNombre() {
		return nacNombre;
	}

	public void setNacNombre(String nacNombre) {
		this.nacNombre = nacNombre;
	}
    
    
    
}

