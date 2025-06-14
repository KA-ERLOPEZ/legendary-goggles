package com.sistema.pizzeria.elpirata.api.core.entities;

import java.io.Serializable;

/**
 * The persistent class for the nacionalidad database table.
 * 
 */
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "nacionalidades")
public class Nacionalidad implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nac_id")
    private long nacId;

    @Column(name = "nac_enabled", nullable = false, columnDefinition = "TINYINT(1) CHECK(nac_enabled IN(0, 1)) default 1")
    private boolean nacEnabled;

    @NotBlank(message = "El nombre de la nacionalidad no puede estar vacío")
    @Column(name = "nac_nombre", nullable = false, unique = true, length = 150)
    private String nacNombre;

    public Nacionalidad() {}

	public long getNacId() {
		return nacId;
	}

	public void setNacId(long nacId) {
		this.nacId = nacId;
	}

	public boolean isNacEnabled() {
		return nacEnabled;
	}

	public void setNacEnabled(boolean nacEnabled) {
		this.nacEnabled = nacEnabled;
	}

	public String getNacNombre() {
		return nacNombre;
	}

	public void setNacNombre(String nacNombre) {
		this.nacNombre = nacNombre;
	}
    
    
}
