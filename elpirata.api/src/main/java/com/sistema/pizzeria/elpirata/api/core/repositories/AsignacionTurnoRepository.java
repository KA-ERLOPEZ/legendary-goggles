package com.sistema.pizzeria.elpirata.api.core.repositories;


import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.pizzeria.elpirata.api.core.entities.AsignacionTurno;
import com.sistema.pizzeria.elpirata.api.core.entities.Contrato;
import com.sistema.pizzeria.elpirata.api.core.entities.Turno;

public interface AsignacionTurnoRepository extends JpaRepository<AsignacionTurno, Long> {

    /**
     * Listar todas las asignaciones activas
     *
     * @param pageable
     * @return Page<AsignacionTurno>
     */
    Page<AsignacionTurno> findAllByActivoTrue(Pageable pageable);
    

    /**
     * Listar todas las asignaciones por contrato activas
     *
     * @param contrato
     * @param pageable
     * @return Page<AsignacionTurno>
     */
    Page<AsignacionTurno> findAllByContratoAndActivoTrue(Contrato contrato, Pageable pageable);

    /**
     * Listar todas las asignaciones por turno activas
     *
     * @param turno
     * @param pageable
     * @return Page<AsignacionTurno>
     */
    Page<AsignacionTurno> findAllByTurnoAndActivoTrue(Turno turno, Pageable pageable);

    /**
     * Validar si hay una asignación de turno a un contrato en una fecha determinada
     *
     * @param contrato
     * @param fechaInicio
     * @param nombre
     * @return boolean
     */
    boolean existsByIdAndContratoAndFechaInicioAndTurno_Nombre(long id, Contrato contrato, LocalDate fechaInicio, String nombre);
    
    /**
     *  Buscar si hay una asignaciòn de turno a un contrato en una fecha determinada
     *  
     * @param contrato
     * @param fechaInicio
     * @param nombre
     * @return Optional<AsignacionTurno>
     */
    Optional<AsignacionTurno> findByAndContratoAndFechaInicioAndTurno_Nombre( Contrato contrato, LocalDate fechaInicio, String nombre);
    
    
}

