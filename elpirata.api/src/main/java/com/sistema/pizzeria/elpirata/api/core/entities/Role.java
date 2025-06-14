package com.sistema.pizzeria.elpirata.api.core.entities;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * The persistent class for the roles database table.
 * 
 */
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "roles")
@NamedQuery(name = "Role.findAll", query = "SELECT r FROM Role r")
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private long roleId;

    @Column(name = "created_at", updatable = false)
    @PastOrPresent(message = "La fecha de creación debe ser pasada o presente")
    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());

    @Column(name = "role_description", nullable = false, length = 60)
    @NotBlank(message = "La descripción del rol no puede estar vacía")
    @Size(max = 60, message = "La descripción del rol no puede superar los 60 caracteres")
    private String roleDescription;

    @Column(name = "role_enabled", nullable = false, columnDefinition = "TINYINT(1) CHECK(role_enabled IN(0, 1)) DEFAULT 1")
    private boolean roleEnabled;

    @Column(name = "updated_at")
    @PastOrPresent(message = "La fecha de actualización debe ser pasada o presente")
    private Timestamp updatedAt;

    public Role() {
    }
    
    public Role(String roleDescription) {
    	        this.roleDescription = roleDescription;
    }

    public long getRoleId() {
        return this.roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getRoleDescription() {
        return this.roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public boolean isRoleEnabled() {
        return this.roleEnabled;
    }

    public void setRoleEnabled(boolean roleEnabled) {
        this.roleEnabled = roleEnabled;
    }

    public Timestamp getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
