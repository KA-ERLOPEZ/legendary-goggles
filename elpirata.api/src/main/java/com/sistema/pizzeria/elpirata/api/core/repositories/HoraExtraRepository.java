package com.sistema.pizzeria.elpirata.api.core.repositories;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.pizzeria.elpirata.api.core.entities.HoraExtra;


public interface HoraExtraRepository extends JpaRepository<HoraExtra, Long> {

	/*
	 * * Busca todas las horas extras de un contrato
	 * 
	 * @param contratoId id del contrato
	 * 
	 * @param pageable paginación
	 * 
	 * @return lista de horas extras
	 */
	Page<HoraExtra> findByContratoId(long contratoId, Pageable pageable);
	
	/**
	 * Busca todas las horas extras de un contrato con el estado especificado
	 * 
	 * @param contratoId id del contrato
	 * 
	 * @param estado     estado de la hora extra
	 * 
	 * @param pageable   paginación
	 * 
	 * @return lista de horas extras
	 */
	Page<HoraExtra> findByContratoIdAndEstado(long contratoId, String estado, Pageable pageable);
	
	/**
	 * Verifica si existe una hora extra con el contrato y la fecha especificada
	 * 
	 * @param contratoId id del contrato
	 * 
	 * @param fecha      fecha de la hora extra
	 * 
	 * @return true si existe, false en caso contrario
	 */
	boolean existsByContratoIdAndFecha(long contratoId, LocalDate fecha);
	
	/**
	 * Busca una hora extra por contrato y fecha
	 * 
	 * @param contratoId id del contrato
	 * 
	 * @param fecha      fecha de la hora extra
	 * 
	 * @return la hora extra encontrada
	 */
	Optional<HoraExtra> findByContratoIdAndFecha(long contratoId, LocalDate fecha);
	
	/**
	 * Busca todas las horas extras de un empleado
	 * 
	 * @param empleadoId id del empleado
	 * 
	 * @param pageable   paginación
	 * 
	 * @return lista de horas extras
	 */
	Page<HoraExtra> findByContratoPersonaPerId(long empleadoId, Pageable pageable);
}
