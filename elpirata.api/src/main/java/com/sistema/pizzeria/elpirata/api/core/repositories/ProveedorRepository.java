package com.sistema.pizzeria.elpirata.api.core.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.pizzeria.elpirata.api.core.entities.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {

	/**
	 * Find a Proveedor by its RUC.
	 *
	 * @param ruc the RUC of the Proveedor
	 * @return the Optional<Proveedor> with the given RUC, or null if not found
	 */
	Optional<Proveedor> findByProvRuc(String ruc);
	
	
	/**
	 * List all Proveedores by estadoId.
	 * @param estadoId
	 * @return List<Proveedor> with the given estadoId
	 */
	List<Proveedor> findByEstado_EstadoId(long estadoId);
	
	/**
	 * List all Proveedores by estadoId and provRazonSocial.
	 * 
	 * @param estadoId
	 * @param provRazonSocial
	 * @return List<Proveedor> with the given estadoId and provRazonSocial
	 */
	boolean existsByProvRazonSocial(String provRazonSocial);
	
	/**
	 * Filtrar por coincidencia parcial de razon social
	 * 
	 * @param provRazonSocial
	 * @return List<Proveedor> con coincidencia parcial de razon social
	 */
	
	List<Proveedor> findByProvRazonSocialContainsIgnoreCase(String provRazonSocial);
	
	/**
	 * Filtrar por coincidencia parcial de RUC
	 * 
	 * @param provRuc
	 * @return List<Proveedor> con coincidencia parcial de RUC
	 */
	List<Proveedor> findByProvRucContainsIgnoreCase(String provRuc);


}
