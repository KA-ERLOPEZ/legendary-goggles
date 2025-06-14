package com.sistema.pizzeria.elpirata.api.core.commons;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.sistema.pizzeria.elpirata.api.core.entities.Usuario;
import com.sistema.pizzeria.elpirata.api.core.repositories.UsuarioRepository;

@Component("auditorAware")
public class AuditorAwareImpl implements AuditorAware<Usuario> {

	private final UsuarioRepository usuarioRepository;
	
	/**
	 * Constructor de la clase AuditorAwareImpl.
	 * 
	 * @param usuarioRepository el repositorio de usuarios
	 */
	public AuditorAwareImpl(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	/**
	 * Obtiene el auditor actual.
	 * 
	 * @return un Optional que contiene el usuario auditor actual
	 */
	@Override
	public Optional<Usuario> getCurrentAuditor() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (authentication == null || !authentication.isAuthenticated()) {
			return Optional.empty();
			
		}
		
		if (authentication.getPrincipal() instanceof UserDetails userDetails) {
			String username=  userDetails.getUsername();
			
			return usuarioRepository
					.findByUsuUsername(username)
					.map(usuario -> {
				usuario.setUsuPassword(null);
				return usuario;
			});
			
		}
		
		return Optional.empty();
	}

}
