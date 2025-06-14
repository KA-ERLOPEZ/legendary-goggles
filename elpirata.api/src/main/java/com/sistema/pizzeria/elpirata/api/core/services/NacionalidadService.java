package com.sistema.pizzeria.elpirata.api.core.services;

import java.util.List;

import com.sistema.pizzeria.elpirata.api.core.services.generics.GenericService;
import com.sistema.pizzeria.elpirata.api.web.dto.NacionalidadDTO;

public interface NacionalidadService extends GenericService<NacionalidadDTO, Long> {

	/**
	 * Busca una nacionalidad por su nombre.
	 * 
	 * @param nacNombre Nombre de la nacionalidad a buscar.
	 * @return NacionalidadDTO objeto que representa la nacionalidad encontrada.
	 */
	NacionalidadDTO findByNacNombre(String nacNombre);

	/**
	 * Verifica si existe una nacionalidad por su nombre.
	 * 
	 * @param nacNombre Nombre de la nacionalidad a buscar.
	 * @return true si existe, false en caso contrario.
	 */
	boolean existsByNacNombre(String nacNombre);
	
	/**
	 * Listar todas las nacionalidades con el estado true.
	 * 
	 * @return Lista de NacionalidadDTO objetos que representan las nacionalidades encontradas.
	 *
	 */
	List<NacionalidadDTO> findByNacEnabledTrue();
	

}
