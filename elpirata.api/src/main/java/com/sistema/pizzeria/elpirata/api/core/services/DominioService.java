package com.sistema.pizzeria.elpirata.api.core.services;

import com.sistema.pizzeria.elpirata.api.core.services.generics.GenericService;
import com.sistema.pizzeria.elpirata.api.web.dto.DominioDTO;

public interface DominioService extends GenericService<DominioDTO, Long> {

	/**
	 * Busca un Dominio por su código.
	 *
	 * @param codigo el código del Dominio a buscar
	 * @return el Dominio encontrado, o null si no se encontró
	 */
	DominioDTO findByCodigo(String codigo); // Método personalizad
}
