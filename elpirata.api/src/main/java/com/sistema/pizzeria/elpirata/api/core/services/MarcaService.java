package com.sistema.pizzeria.elpirata.api.core.services;

import java.util.List;

import com.sistema.pizzeria.elpirata.api.core.services.generics.GenericService;
import com.sistema.pizzeria.elpirata.api.web.dto.MarcaDTO;

public interface MarcaService extends GenericService<MarcaDTO, Long> {

	/**
	 * Busca una marca por su nombre.
	 * 
	 * @param marNombre Nombre de la marca a buscar.
	 * @return MarcaDTO objeto que representa la marca encontrada.
	 */
	MarcaDTO findByMarNombre(String marNombre);

	/**
	 * Verifica si existe una marca por su nombre.
	 * 
	 * @param marNombre Nombre de la marca a buscar.
	 * @return true si existe, false en caso contrario.
	 */
	boolean existsByMarNombre(String marNombre);

	/**
	 * Busca una lista de marcas por su estado.
	 * 
	 * @param estadoId ID del estado de la marca.
	 * @return Lista de MarcaDTO objetos que representan las marcas encontradas.
	 */
	List<MarcaDTO> findByEstadoId(Integer estadoId);
}
