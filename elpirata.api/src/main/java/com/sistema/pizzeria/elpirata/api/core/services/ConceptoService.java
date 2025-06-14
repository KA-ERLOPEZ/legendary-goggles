package com.sistema.pizzeria.elpirata.api.core.services;

import java.util.List;

import com.sistema.pizzeria.elpirata.api.core.services.generics.GenericService;
import com.sistema.pizzeria.elpirata.api.web.dto.ConceptoDTO;

public interface ConceptoService extends GenericService<ConceptoDTO, Long> {

	/**
	 * Busca un concepto por su nombre.
	 *
	 * @param nombre Nombre del concepto a buscar.
	 * @return ConceptoDTO encontrado o null si no existe.
	 */
	ConceptoDTO getByNombre(String nombre);


	/**
	 * Busca todos los conceptos activos.
	 *
	 * @return Lista de ConceptoDTO activos.
	 */
	List<ConceptoDTO> getByActivoTrue();
	
	/**
	 * Busca todos los conceptos por dominio y estado.
	 *
	 * @param dominioId ID del dominio al que pertenecen los conceptos.
	 * @param activo    Estado de los conceptos (true para activos, false para
	 *                  inactivos).
	 * @return Lista de ConceptoDTO asociados al dominio.
	 */
	List<ConceptoDTO> getByDominioIdAndActivoTrue(long dominioId);
	
}
