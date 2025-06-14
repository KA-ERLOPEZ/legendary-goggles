package com.sistema.pizzeria.elpirata.api.core.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.pizzeria.elpirata.api.core.entities.Nacionalidad;

public interface NacionalidadRepository extends JpaRepository<Nacionalidad, Long> {

	/**
	 * Verifica si existe una nacionalidad por su nombre.
	 *
	 * @param nacNombre el nombre de la nacionalidad
	 * @return true si existe, false en caso contrario
	 */
	boolean existsByNacNombre(String nacNombre);

	/**
	 * Busca una nacionalidad por su nombre.
	 *
	 * @param nacNombre el nombre de la nacionalidad
	 * @return un Optional que contiene la nacionalidad si existe, o vacío si no
	 */
	Optional<Nacionalidad> findByNacNombre(String nacNombre);

	/**
	 * Lista todas las nacionalidades habilitadas.
	 * 
	 * @return una lista de objetos NacionalidadDTO
	 */
	List<Nacionalidad> findByNacEnabledTrue();

}
