package com.sistema.pizzeria.elpirata.api.core.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.pizzeria.elpirata.api.core.entities.Marca;

public interface MarcaRepository extends JpaRepository<Marca, Long> {

	/**
	 * Find by marNombre
	 * @param marNombre
	 * @return Optional<Marca>
	 */
	Optional<Marca> findByMarNombre(String marNombre);
	
	/**
	 * List by estadoId
	 * @param estadoId
	 * @return List<Marca>
	 */
	List<Marca> findByEstado_EstadoId(Integer estadoId);
	
	/**
	 * Is marNombre exists
	 * @param marNombre
	 * @return boolean
	 */
	boolean existsByMarNombre(String marNombre);
}
