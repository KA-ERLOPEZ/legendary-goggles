package com.sistema.pizzeria.elpirata.api.core.entities;

import java.io.Serializable;
import java.util.List;

/**
 * The persistent class for the uris database table.
 * 
 */
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "uris")
@NamedQuery(name = "Uri.findAll", query = "SELECT u FROM Uri u")
public class Uri implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uri_id")
    private long uriId;

    @Column(name = "uri_description", nullable = false, length = 255)
    private String uriDescription;

    @Column(name = "uri_path", nullable = false, unique = true)
    private String uriPath;

    @OneToMany(mappedBy = "uri", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<RoleUri> roleUris;
    
    @ManyToOne
    @JoinColumn(name = "estado_id", nullable = false, referencedColumnName = "estado_id")
    private Estado estado;

    public Uri() {
    }

	public long getUriId() {
		return uriId;
	}

	public void setUriId(long uriId) {
		this.uriId = uriId;
	}

	public String getUriDescription() {
		return uriDescription;
	}

	public void setUriDescription(String uriDescription) {
		this.uriDescription = uriDescription;
	}

	public String getUriPath() {
		return uriPath;
	}

	public void setUriPath(String uriPath) {
		this.uriPath = uriPath;
	}

	public List<RoleUri> getRoleUris() {
		return roleUris;
	}

	public void setRoleUris(List<RoleUri> roleUris) {
		this.roleUris = roleUris;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

    // Getters y setters omitidos por brevedad (mantén los que ya tienes)
    
    
}
