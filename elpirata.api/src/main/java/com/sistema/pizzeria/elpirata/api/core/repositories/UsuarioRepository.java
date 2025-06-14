package com.sistema.pizzeria.elpirata.api.core.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.pizzeria.elpirata.api.core.entities.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	/**
	 * Busca un usuario por su username.
	 * 
	 * @param usuUsername el username del usuario a buscar.
	 * @return un Optional que contiene el usuario encontrado, o vacío si no se
	 *         encontró.
	 */
	Optional<Usuario> findByUsuUsername(String usuUsername);

	/**
	 * Verifica si existe un usuario con el username dado.
	 * 
	 * @param usuUsername el username a verificar.
	 * @return true si existe un usuario con el username dado, false en caso
	 *         contrario.
	 */
	boolean existsByUsuUsername(String usuUsername);
	
	/**
	 * Verifica si existe un usuario con el email dado.
	 * 
	 * @param usuEmail el email a verificar.
	 * @return true si existe un usuario con el email dado, false en caso contrario.
	 */
	boolean existsByPersonaPerEmail(String usuEmail);
	
	/**
	 * Busca un usuario por su email.
	 * 
	 * @param usuEmail el email del usuario a buscar.
	 * @return un Optional que contiene el usuario encontrado, o vacío si no se
	 *         encontró.
	 */	
	Optional<Usuario> findByPersonaPerEmail(String usuEmail);
	
	
}
