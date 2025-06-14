package com.sistema.pizzeria.elpirata.api.core.services;

import java.util.List;

import com.sistema.pizzeria.elpirata.api.core.services.generics.GenericService;
import com.sistema.pizzeria.elpirata.api.web.dto.TamanoDTO;

public interface TamanoService extends GenericService<TamanoDTO, Long> {

	/**
	 * Method to find a Tamano by its name.
	 * 
	 * @param tamNombre
	 * @return TamanoDTO
	 */
	TamanoDTO findByTamNombre(String tamNombre);

	/**
	 * Method to list all Tamano by a specific estado.
	 * @param estadoId
	 * @return List<TamanoDTO>
	 */
	List<TamanoDTO> findByEstado_estadoId(Integer estadoId);

	
}
