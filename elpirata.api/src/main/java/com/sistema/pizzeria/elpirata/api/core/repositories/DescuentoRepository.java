package com.sistema.pizzeria.elpirata.api.core.repositories;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.pizzeria.elpirata.api.core.entities.Descuento;

public interface DescuentoRepository extends JpaRepository<Descuento, Long> {
	
	/**
	 * Lista todos los descuentos asociados a un contrato.
	 * @param contratoId
	 * @param pageable
	 * @return
	 */
	Page<Descuento> findAllByContratoId(long contratoId, Pageable pageable);
	
	/**
	 * Lista todos los descuentos asociados a un concepto.
	 * 
	 * @param conceptoId
	 * @param pageable
	 * @return
	 */
	Page<Descuento> findAllByConceptoId(long conceptoId, Pageable pageable);
	
	/**
	 * Lista todos los descuentos asociados a un concepto y con estado activo.
	 * 
	 * @param conceptoId
	 * @param pageable
	 * @return
	 */
	Page<Descuento> findAllByConceptoIdAndEstado(long conceptoId, String estado, Pageable pageable);
	
	/**
	 * Busca un descuento por contrato, concepto y fecha.
	 * 
	 * @param contratoId
	 * @param conceptoId
	 * @param fecha
	 * @return
	 */
	Optional<Descuento> findByContratoIdAndConceptoIdAndFecha(long contratoId, long conceptoId, LocalDate fecha);
	
	
	/**
	 * Lista todos los descuentos entre dos fechas.
	 * 
	 * @param startDate
	 * @param endDate
	 * @param pageable
	 * @return
	 */
	Page<Descuento> findAllByFechaBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);
	
	
	/**
	 * Lista todos los descuentos por estado.
	 * 
	 * @param estado
	 * @param pageable
	 * @return
	 */
	Page<Descuento> findAllByEstado(String estado, Pageable pageable);
	
	/**
	 * Lista todos los descuentos asociados a un contrato y con estado activo.
	 * 
	 * @param contratoId
	 * @param estado
	 * @param pageable
	 * @return
	 */
	Page<Descuento> findAllByContratoIdAndEstado(long contratoId, String estado, Pageable pageable);
	
}
