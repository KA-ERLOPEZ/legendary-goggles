package com.sistema.pizzeria.elpirata.api.core.commons;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.sistema.pizzeria.elpirata.api.core.entities.Usuario;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;


@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable {

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_creacion_id", referencedColumnName = "usu_id")
    private Usuario usuarioCreacion;
    
    @LastModifiedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_modificacion_id", referencedColumnName = "usu_id")
    private Usuario usuarioModificacion;

	public Auditable(LocalDateTime createdAt, LocalDateTime updatedAt, Usuario usuarioCreacion, Usuario usuarioModificacion) {
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.usuarioCreacion = usuarioCreacion;
		this.usuarioModificacion = usuarioModificacion;
	}	
	

	public Auditable() {
	}



	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}


	public Usuario getUsuarioCreacion() {
		return usuarioCreacion;
	}


	public void setUsuarioCreacion(Usuario usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}


	public Usuario getUsuarioModificacion() {
		return usuarioModificacion;
	}


	public void setUsuarioModificacion(Usuario usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}
	
	
}
