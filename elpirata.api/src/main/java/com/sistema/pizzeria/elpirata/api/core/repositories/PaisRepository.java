package com.sistema.pizzeria.elpirata.api.core.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.pizzeria.elpirata.api.core.entities.Pais;

public interface PaisRepository extends JpaRepository<Pais, Long> {

	/**
	 * Verifica si existe un país por su nombre.
	 * 
	 * @param paisNombre el nombre del país a verificar
	 * @return true si existe, false en caso contrario
	 */
	boolean existsByPaisNombre(String paisNombre);
	
	
	/**
	 * Listar todos los paises por estado
	 * @param estadoId
	 * @return lista de paises
	 */
	List<Pais> findByEstado_EstadoId(Long estadoId);

	/**
	 * Buscar un país por su nombre.
	 * 
	 * @param nombre el nombre del país a buscar
	 * @return un Optional que contiene el país si se encuentra, o vacío si no
	 */
	Optional<Pais> findByPaisNombre(String nombre);

}
