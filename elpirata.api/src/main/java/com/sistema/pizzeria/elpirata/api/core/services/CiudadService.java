package com.sistema.pizzeria.elpirata.api.core.services;

import java.util.List;

import com.sistema.pizzeria.elpirata.api.core.services.generics.GenericService;
import com.sistema.pizzeria.elpirata.api.web.dto.CiudadDTO;

public interface CiudadService extends GenericService<CiudadDTO, Long> {

	/**
	 * Lista todas las ciudades por el id del pais
	 * @param paisId
	 * @return Lista de ciudades
	 */
	List<CiudadDTO> findAllByPaisId(Long paisId);
	
	/**
	 * Lista todas las ciudades por el id del estado
	 * 
	 * @param estadoId
	 * @return Lista de ciudades
	 */
	List<CiudadDTO> findAllByEstadoId(Long estadoId);
}
