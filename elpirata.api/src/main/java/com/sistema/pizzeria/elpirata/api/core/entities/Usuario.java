package com.sistema.pizzeria.elpirata.api.core.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
/**
 * The persistent class for the usuarios database table.
 * 
 */
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "usuarios")
public class Usuario  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usu_id")
    private long usuId;

    @NotNull(message = "El estado ID no puede ser nulo")
    @ManyToOne
    @JoinColumn(name = "estado_id", referencedColumnName = "estado_id", nullable = false)
    private Estado estado;

    @NotNull(message = "El perfil ID no puede ser nulo")
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "per_id", referencedColumnName = "per_id", nullable = false)
    private Persona persona;

    @Column(name = "usu_failed_attempts")
    private int usuFailedAttempts;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "usu_fec_actualizacion")
    private Date usuFecActualizacion;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "usu_fec_creacion", nullable = false)
    private Date usuFecCreacion;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "usu_last_failed_login")
    private Date usuLastFailedLogin;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "usu_last_login", nullable = false)
    private Date usuLastLogin;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "usu_lockout_expiration")
    private Date usuLockoutExpiration;

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Size(min = 8, max = 150, message = "La contraseña debe tener entre 8 y 150 caracteres")
    @Column(name = "usu_password", nullable = false, length = 150)
    private String usuPassword;

    @Column(name = "usu_reset_token")
    private String usuResetToken;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "usu_reset_token_expiration")
    private Date usuResetTokenExpiration;

    @Column(name = "usu_two_factor_enabled", nullable = false, columnDefinition = "TINYINT(1)  CHECK(usu_two_factor_enabled IN(0, 1)) DEFAULT 0")
    private boolean usuTwoFactorEnabled;

    @Column(name = "usu_two_factor_secret", length = 255)
    private String usuTwoFactorSecret;

    @NotBlank(message = "El nombre de usuario no puede estar vacío")
    @Size(min = 3, max = 20, message = "El nombre de usuario debe tener entre 3 y 20 caracteres")
    @Column(name = "usu_username", unique = true, nullable = false)
    private String usuUsername;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
    @JoinTable(
            name = "usuario_rol",
            joinColumns = @JoinColumn(name = "usu_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    public Usuario() {}

	public long getUsuId() {
		return usuId;
	}

	public void setUsuId(long usuId) {
		this.usuId = usuId;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public int getUsuFailedAttempts() {
		return usuFailedAttempts;
	}

	public void setUsuFailedAttempts(int usuFailedAttempts) {
		this.usuFailedAttempts = usuFailedAttempts;
	}

	public Date getUsuFecActualizacion() {
		return usuFecActualizacion;
	}

	public void setUsuFecActualizacion(Date usuFecActualizacion) {
		this.usuFecActualizacion = usuFecActualizacion;
	}

	public Date getUsuFecCreacion() {
		return usuFecCreacion;
	}

	public void setUsuFecCreacion(Date usuFecCreacion) {
		this.usuFecCreacion = usuFecCreacion;
	}

	public Date getUsuLastFailedLogin() {
		return usuLastFailedLogin;
	}

	public void setUsuLastFailedLogin(Date usuLastFailedLogin) {
		this.usuLastFailedLogin = usuLastFailedLogin;
	}

	public Date getUsuLastLogin() {
		return usuLastLogin;
	}

	public void setUsuLastLogin(Date usuLastLogin) {
		this.usuLastLogin = usuLastLogin;
	}

	public Date getUsuLockoutExpiration() {
		return usuLockoutExpiration;
	}

	public void setUsuLockoutExpiration(Date usuLockoutExpiration) {
		this.usuLockoutExpiration = usuLockoutExpiration;
	}

	public String getUsuPassword() {
		return usuPassword;
	}

	public void setUsuPassword(String usuPassword) {
		this.usuPassword = usuPassword;
	}

	public String getUsuResetToken() {
		return usuResetToken;
	}

	public void setUsuResetToken(String usuResetToken) {
		this.usuResetToken = usuResetToken;
	}

	public Date getUsuResetTokenExpiration() {
		return usuResetTokenExpiration;
	}

	public void setUsuResetTokenExpiration(Date usuResetTokenExpiration) {
		this.usuResetTokenExpiration = usuResetTokenExpiration;
	}

	public boolean isUsuTwoFactorEnabled() {
		return usuTwoFactorEnabled;
	}

	public void setUsuTwoFactorEnabled(boolean usuTwoFactorEnabled) {
		this.usuTwoFactorEnabled = usuTwoFactorEnabled;
	}

	public String getUsuTwoFactorSecret() {
		return usuTwoFactorSecret;
	}

	public void setUsuTwoFactorSecret(String usuTwoFactorSecret) {
		this.usuTwoFactorSecret = usuTwoFactorSecret;
	}

	public String getUsuUsername() {
		return usuUsername;
	}

	public void setUsuUsername(String usuUsername) {
		this.usuUsername = usuUsername;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
    
    
}
