package com.sistema.pizzeria.elpirata.api.core.services;

import java.util.List;

import com.sistema.pizzeria.elpirata.api.core.services.generics.GenericService;
import com.sistema.pizzeria.elpirata.api.web.dto.DominioDTO;
import com.sistema.pizzeria.elpirata.api.web.dto.EstadoDTO;

public interface EstadoService extends GenericService<EstadoDTO, Long> {

	/**
	 * Busca una lista de estados por el dominio proporcionado.
	 *
	 * @param dominio El dominio por el cual se desea filtrar los estados.
	 * @return Una lista de objetos EstadoDTO que coinciden con el dominio
	 *         proporcionado.
	 */
	List<EstadoDTO> buscarEstadoPorDominio(DominioDTO dominio);
}
