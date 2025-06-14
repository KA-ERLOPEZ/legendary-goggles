package com.sistema.pizzeria.elpirata.api.core.config.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
	private String nameAndLastName;
	private boolean enabled;
	private Collection<? extends GrantedAuthority> authorities;

	// Constructor personalizado
	public CustomUserDetails(String username, String password, String nameAndLastName, boolean enabled,
			Collection<? extends GrantedAuthority> authorities) {
		this.username = username;
		this.password = password;
		this.nameAndLastName = nameAndLastName;
		this.enabled = enabled;
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public String getNameAndLastName() {
		return nameAndLastName;
	}

	// Indica si la cuenta ha expirado (aquí asumimos que siempre es válida)
	@Override
	public boolean isAccountNonExpired() {
		return enabled;
	}

	// Indica si la cuenta está bloqueada (aquí asumimos que siempre está
	// desbloqueada)
	@Override
	public boolean isAccountNonLocked() {
		return enabled;
	}

	// Indica si las credenciales han expirado (aquí asumimos que siempre son
	// válidas)
	@Override
	public boolean isCredentialsNonExpired() {
		return enabled;
	}

	// Indica si el usuario está habilitado
	@Override
	public boolean isEnabled() {
		return enabled;
	}
}
