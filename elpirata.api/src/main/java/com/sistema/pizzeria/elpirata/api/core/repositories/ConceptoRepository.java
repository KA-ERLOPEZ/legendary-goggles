package com.sistema.pizzeria.elpirata.api.core.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.pizzeria.elpirata.api.core.entities.Concepto;

public interface ConceptoRepository extends JpaRepository<Concepto, Long> {
	
	/**
	 * Busca un concepto por su nombre.
	 * 
	 * @param nombre Nombre del concepto a buscar.
	 * @return Concepto encontrado o null si no existe.
	 */
	Optional<Concepto> findByNombre(String nombre);

	/**
	 * Verifica si un concepto con el nombre dado existe.
	 * 
	 * @param nombre Nombre del concepto a verificar.
	 * @return true si existe, false en caso contrario.
	 */
	boolean existsByNombre(String nombre);
	
	/**
	 * Busca todos los conceptos activos.
	 * 
	 * @return Lista de conceptos activos.
	 */
	List<Concepto> findByActivoTrue(); 
	
	/**
	 * Busca todos los conceptos por dominio y estado.
	 * 
	 * @param dominioId ID del dominio al que pertenecen los conceptos.
	 * @param activo Estado de los conceptos (true para activos, false para inactivos).
	 * @return Lista de conceptos asociados al dominio.
	 */
	List<Concepto> findByDominioDominioIdAndActivoTrue(long dominioId);

}
