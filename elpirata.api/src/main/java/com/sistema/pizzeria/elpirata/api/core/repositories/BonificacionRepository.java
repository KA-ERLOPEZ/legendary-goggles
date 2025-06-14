package com.sistema.pizzeria.elpirata.api.core.repositories;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.pizzeria.elpirata.api.core.entities.Bonificacion;

public interface BonificacionRepository extends JpaRepository<Bonificacion, Long> {

	/**
	 * Listr bonificaiones por contrato
	 * 
	 * @param id
	 * @return Lista de bonificaciones
	 */
	Page<Bonificacion> findByContratoId(long contratoId, Pageable pageable);
	
	/**
	 * Listr bonificaciones por contrato y nombre o apellido
	 * @param nombre
	 * @param apellido
	 * @param pageable
	 * @return Lista de bonificaciones
	 */
	Page<Bonificacion> findByContratoPersonaPerNombreIgnoreCaseContainingOrContratoPersonaPerApellidoIgnoreCaseContaining(
			String nombre, String apellido, Pageable pageable);
	
	/**
	 * Listar bonificaciones por contrato y estado
	 * 
	 * @param estado
	 * @param contratoId
	 * @param pageable
	 * @return Lista de bonificaciones
	 */
	Page<Bonificacion> findByContratoIdAndEstado(String estado, long contratoId, Pageable pageable);
	
	/**
	 * Listar bonificaciones por contrato y fecha
	 * 
	 * @param contratoId
	 * @param fecha
	 * @param pageable
	 * @return Lista de bonificaciones
	 */
	Page<Bonificacion> findByContratoIdAndFecha(long contratoId, LocalDate fecha, Pageable pageable);
	
	
	/**
	 * Listar bonificaciones por contrato y concepto y fecha
	 * 
	 * @param contratoId
	 * @param conceptoId
	 * @param fecha
	 * @return Lista de bonificaciones
	 */
	Optional<Bonificacion> findByContratoIdAndConceptoIdAndFecha(long contratoId, long conceptoId, LocalDate fecha);
	
	
	/**
	 * Verifica si existe una bonificacion por contrato y concepto y fecha
	 * 
	 * @param contratoId
	 * @param conceptoId
	 * @param fecha
	 * @return true si existe, false si no existe
	 */
	boolean existsByContratoIdAndConceptoIdAndFecha(long contratoId, long conceptoId, LocalDate fecha);
}
