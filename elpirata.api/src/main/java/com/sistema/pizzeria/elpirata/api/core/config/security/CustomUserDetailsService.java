package com.sistema.pizzeria.elpirata.api.core.config.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sistema.pizzeria.elpirata.api.core.entities.Estado;
import com.sistema.pizzeria.elpirata.api.core.entities.Role;
import com.sistema.pizzeria.elpirata.api.core.entities.Usuario;
import com.sistema.pizzeria.elpirata.api.core.repositories.UsuarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Busca al usuario por su nombre de usuario
        Usuario user = this.repository
                .findByUsuUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con username: " + username));
        
        // Valida que los datos importantes no sean nulos
        if (user.getPersona() == null) {
            throw new IllegalStateException("El usuario no tiene información personal asignada.");
        }
        if (user.getEstado() == null) {
            throw new IllegalStateException("El usuario no tiene un estado asignado.");
        }
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            throw new IllegalStateException("El usuario no tiene roles asignados.");
        }

        // Retorna el objeto UserDetails con los datos necesarios
        return new CustomUserDetails(
                user.getUsuUsername(),
                user.getUsuPassword(),
                user.getPersona().getPerNombre() + " " + user.getPersona().getPerApellido(),
                isEnabled(user.getEstado()),
                authorities(user.getRoles())
        );
    }

    /**
     * Convierte los roles de un usuario en autoridades compatibles con Spring Security.
     *
     * @param roles conjunto de roles del usuario
     * @return colección de GrantedAuthority
     */
    private Collection<? extends GrantedAuthority> authorities(Set<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleDescription()))
                .collect(Collectors.toList());
    }

    /**
     * Verifica si el usuario está habilitado según su estado.
     *
     * @param estado objeto Estado del usuario
     * @return true si el estado es "activo", false en caso contrario
     */
    private boolean isEnabled(Estado estado) {
        return "activo".equalsIgnoreCase(estado.getEstadoNombre());
    }
}

