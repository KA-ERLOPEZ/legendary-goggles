package com.sistema.pizzeria.elpirata.api.core.services;

import java.util.List;

import com.sistema.pizzeria.elpirata.api.core.services.generics.GenericService;
import com.sistema.pizzeria.elpirata.api.web.dto.SaborDTO;

public interface SaborService extends GenericService<SaborDTO, Long> {

	/**
	 * Method to find a Sabor by its name.
	 *
	 * @param nombre the name of the Sabor
	 * @return the Sabor entity if found, null otherwise
	 */
	SaborDTO findByNombre(String nombre);
	
	/**
	 * Method to list all Sabores by a specific estado.
	 * 
	 * @param estadoId the estado of the Sabores
	 * @return a list of Sabores with the specified estado
	 * 
	 */
	List<SaborDTO> findAllByEstado(Integer estadoId);
}
