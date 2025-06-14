package com.sistema.pizzeria.elpirata.api.core.services;

import java.util.List;

import com.sistema.pizzeria.elpirata.api.core.services.generics.GenericService;
import com.sistema.pizzeria.elpirata.api.web.dto.PaisDTO;

public interface PaisService extends GenericService<PaisDTO, Long> {
	/**
	 * Busca un país por su nombre.
	 * 
	 * @param nombre Nombre del país.
	 * @return País encontrado.
	 */
	PaisDTO findByNombre(String nombre);

	/**
	 * Lista todos los paises por estado.
	 * @param estadoId
	 * @return Lista de paises.
	 */
	List<PaisDTO> getByEstadoId(long estadoId);
	
	/**
	 * Verifica si existe un país por su nombre.
	 * 
	 * @param nombre Nombre del país.
	 * @return true si existe, false en caso contrario.
	 */
	boolean existsByNombre(String nombre);

}
