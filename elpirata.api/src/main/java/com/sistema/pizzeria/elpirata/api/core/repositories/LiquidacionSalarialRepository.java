package com.sistema.pizzeria.elpirata.api.core.repositories;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.pizzeria.elpirata.api.core.entities.LiquidacionSalarial;

public interface LiquidacionSalarialRepository extends JpaRepository<LiquidacionSalarial, Long> {
	
	/**
	 * Finds all LiquidacionSalarial records by contratoId with pagination.
	 *
	 * @param contratoId the ID of the contrato to filter by
	 * @param pageable   the pagination information
	 * @return a page of LiquidacionSalarial records
	 */
	Page<LiquidacionSalarial> findAllByContratoId(Long contratoId, Pageable pageable);
	
	/**
	 * Finds all LiquidacionSalarial records by contratoId and anio with pagination.
	 *
	 * @param contratoId the ID of the contrato to filter by
	 * @param anio       the year to filter by
	 * @param pageable   the pagination information
	 * @return a page of LiquidacionSalarial records
	 */
	Page<LiquidacionSalarial> findAllByContratoIdAndAnio(Long contratoId, Integer anio, Pageable pageable);
	
	/**
	 * Finds LiquidacionSalarial records by contratoId, anio, and mes with
	 * pagination.
	 *
	 * @param contratoId the ID of the contrato to filter by
	 * @param anio       the year to filter by
	 * @param mes        the month to filter by
	 * @param pageable   the pagination information
	 * @return a LiquidacionSalarial records
	 */
	Optional<LiquidacionSalarial> findByContratoIdAndAnioAndMes(Long contratoId, Integer anio, Integer mes);
	
	/**
	 * Finds all LiquidacionSalarial records by contratoId and mes with
	 * pagination.
	 * @param contratoId the ID of the contrato to filter by
	 * @param mes        the month to filter by
	 * 
	 * @return a page of LiquidacionSalarial records
	 */
	Page<LiquidacionSalarial> findAllByContratoIdAndMes(Long contratoId, Integer mes, Pageable pageable);
	
	/**
	 * Finds all LiquidacionSalarial records by anio with pagination.
	 *
	 * @param anio     the year to filter by
	 * @param pageable the pagination information
	 * @return a page of LiquidacionSalarial records
	 */
	Page<LiquidacionSalarial> findAllByAnio(Integer anio, Pageable pageable);
	
	/**
	 * Finds all LiquidacionSalarial records by anio and mes with pagination.
	 *
	 * @param anio     the year to filter by
	 * @param mes      the month to filter by
	 * @param pageable the pagination information
	 * @return a page of LiquidacionSalarial records
	 */
	Page<LiquidacionSalarial> findByAnioAndMes(Integer anio, Integer mes, Pageable pageable);
	
	/**
	 * Finds all LiquidacionSalarial records by fechaLiquidacion and contratoId with
	 * pagination.
	 *
	 * @param fechaInicio the start date to filter by
	 * @param fechaFin    the end date to filter by
	 * @param contratoId the ID of the contrato to filter by
	 * @param pageable    the pagination information
	 * @return a page of LiquidacionSalarial records
	 */
	Page<LiquidacionSalarial> findAllByContratoIdAndFechaLiquidacionBetween(LocalDate fechaInicio, LocalDate fechaFin, long contratoId, Pageable pageable);
	
	/**
	 * Finds all LiquidacionSalarial records by fechaLiquidacion with pagination.
	 *
	 * @param fechaInicio the start date to filter by
	 * @param fechaFin    the end date to filter by
	 * @param pageable    the pagination information
	 * @return a page of LiquidacionSalarial records
	 */
	Page<LiquidacionSalarial> findAllByFechaLiquidacionBetween(LocalDate fechaInicio, LocalDate fechaFin, Pageable pageable);
	
	
	/**
	 * Finds all LiquidacionSalarial records by anio and fechaLiquidacion between with pagination.
	 * 
	 * @param anio the year to filter by
	 * @param fechaInicio the start date to filter by
	 * @param fechaFin the end date to filter by
	 * @param pageable the pagination information
	 * 
	 * @return a page of LiquidacionSalarial records
	 */
	Page<LiquidacionSalarial> findAllByAnioAndFechaLiquidacionBetween(Integer anio, LocalDate fechaInicio, LocalDate fechaFin, Pageable pageable);

	/**
	 * Finds all LiquidacionSalarial records by mes and fechaLiquidacion between
	 * with pagination.
	 * 
	 * @param mes        the month to filter by
	 * @param fechaInicio  the start date to filter by
	 * @param fechaFin the end date to filter by
	 * @param pageable   the pagination information
	 * 
	 * @return a page of LiquidacionSalarial records
	 */
	Page<LiquidacionSalarial> findAllByMesAndFechaLiquidacionBetween(Integer mes, LocalDate fechaInicio,
			LocalDate fechaFin, Pageable pageable);
	

}
