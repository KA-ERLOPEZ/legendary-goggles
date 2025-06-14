package com.sistema.pizzeria.elpirata.api.core.services;

import java.util.List;

import com.sistema.pizzeria.elpirata.api.core.services.generics.GenericService;
import com.sistema.pizzeria.elpirata.api.web.dto.ProveedorDTO;

public interface ProveedorService extends GenericService<ProveedorDTO, Long> {
	
	/**
	 * Find a Proveedor by its RUC.
	 *
	 * @param ruc the RUC of the Proveedor
	 * @return the Optional<ProveedorDTO> with the given RUC, or null if not found
	 */
	ProveedorDTO findByProvRuc(String ruc);

	/**
	 * List all Proveedores by estadoId.
	 * 
	 * @param estadoId
	 * @return List<ProveedorDTO> with the given estadoId
	 */
	List<ProveedorDTO> findByEstado_EstadoId(long estadoId);

	/**
	 * List all Proveedores by estadoId and provRazonSocial.
	 * 
	 * @param estadoId
	 * @param provRazonSocial
	 * @return List<ProveedorDTO> with the given estadoId and provRazonSocial
	 */
	boolean existsByProvRazonSocial(String provRazonSocial);

	/**
	 * Filtrar por coincidencia parcial de razon social
	 * 
	 * @param provRazonSocial
	 * @return List<ProveedorDTO> con coincidencia parcial de razon social
	 */
	List<ProveedorDTO> findByProvRazonSocialContainsIgnoreCase(String provRazonSocial);
	
	/**
	 * Filtrar por coincidencia parcial de RUC
	 * 
	 * @param provRuc
	 * @return List<ProveedorDTO> con coincidencia parcial de RUC
	 */
	List<ProveedorDTO> findByProvRucContainsIgnoreCase(String provRuc);

}
