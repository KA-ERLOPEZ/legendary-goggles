package com.sistema.pizzeria.elpirata.api.core.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.pizzeria.elpirata.api.core.entities.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
	
	/**
	 * Verifica si existe una persona por su número de documento.
	 * @param perNroDocumento
	 * @return
	 */
	boolean existsByPerNroCedula(String perNroDocumento);

	/**
	 * Verifica si existe una persona por su email.
	 * @param perEmail
	 * @return Verdadero si existe, falso en caso contrario.
	 */
	boolean existsByPerEmail(String perEmail);

	/**
	 * Verifica si existe una persona por su teléfono.
	 * 
	 * @param perTelefono
	 * @return Verdadero si existe, falso en caso contrario.
	 */
	boolean existsByPerTelefono(String perTelefono);

	/**
	 * Verifica si existe una persona por su número de RUC.
	 * 
	 * @param perNroRuc
	 * @return Verdadero si existe, falso en caso contrario.
	 */	
	boolean existsByPerNroRuc(String perNroRuc);

	/**
	 * Filtra persona por coincidencia parcial del nombre.
	 * 
	 * @param perNombre
	 * @param pageable
	 * @return Lista de personas que coinciden con el nombre.
	 */
	Page<Persona> findByPerNombreContainingIgnoreCase(String perNombre, Pageable pageable);
	
	/**
	 * Busca una persona por su cédula.
	 * 
	 * @param perCedula
	 * @return  Optional<Persona>
	 */
	Optional<Persona> findByPerNroCedula(String perCedula);

}
