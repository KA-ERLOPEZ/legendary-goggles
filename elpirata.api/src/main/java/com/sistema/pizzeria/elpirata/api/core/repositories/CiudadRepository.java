package com.sistema.pizzeria.elpirata.api.core.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.pizzeria.elpirata.api.core.entities.Ciudad;

public interface CiudadRepository extends JpaRepository<Ciudad, Long> {

	/**
	 * Verifica si existe una ciudad por su nombre
	 * 
	 * @param ciuNombre nombre de la ciudad
	 * @return true si existe, false si no existe
	 */
	boolean existsByCiuNombre(String ciuNombre);
	
	/**
	 * Busca todas las ciudades por el id del pais
	 * 
	 * @param paisId id del pais
	 * @return lista de ciudades
	 */
	List<Ciudad> findAllByPaisPaisId(Long paisId);
	
	/**
	 * Busca todas las ciudades por el id del estado
	 * 
	 * @param estadoId id del estado
	 * @return lista de ciudades
	 */
	List<Ciudad> findAllByEstadoEstadoId(Long estadoId);

}
