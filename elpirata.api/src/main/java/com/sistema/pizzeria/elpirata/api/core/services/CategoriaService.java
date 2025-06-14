package com.sistema.pizzeria.elpirata.api.core.services;

import java.util.List;

import com.sistema.pizzeria.elpirata.api.core.services.generics.GenericService;
import com.sistema.pizzeria.elpirata.api.web.dto.CategoriaDTO;

public interface CategoriaService extends GenericService<CategoriaDTO, Long> {

	/**
	 * Busca una categoria por su nombre
	 * 
	 * @param nombre
	 * @return CategoriaDTO
	 */
	CategoriaDTO findByCatNombre(String nombre);
	
	/**
	 * Busca una categoria por su estado
	 * 
	 * @param id
	 * @return CategoriaDTO
	 */
	List<CategoriaDTO> findByEstado_EstadoId(long id);
}
