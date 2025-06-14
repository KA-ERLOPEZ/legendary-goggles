package com.sistema.pizzeria.elpirata.api.core.repositories;

import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.sistema.pizzeria.elpirata.api.core.entities.Turno;

import io.lettuce.core.dynamic.annotation.Param;
import jakarta.transaction.Transactional;

public interface TurnoRepository extends JpaRepository<Turno, Long> {

	/**
	 * Verifica si existe un turno con el nombre especificado.
	 * 
	 * @param nombre el nombre del turno
	 * @return true si existe, false en caso contrario
	 */
	boolean existsByNombre(String nombre);
	
	/**
	 * Verifica si existe un turno con la hora de inicio y fin especificadas.
	 * 
	 * @param horaInicio la hora de inicio del turno
	 * @param horaFin    la hora de fin del turno
	 * @param nombre     el nombre del turno
	 * @return true si existe, false en caso contrario
	 */
	boolean existsByHoraInicioAndHoraFinAndNombre(LocalTime horaInicio, LocalTime horaFin, String nombre);
	
	/**
	 * Lista todos los turnos activos.
	 * 
	 * @return una lista de objetos Turno
	 */
	List<Turno> findAllByActivoTrue();
	
	/**
	 * Actualiza el estado de un turno.
	 * 
	 * @param id     el ID del turno
	 * @param activo el nuevo estado del turno
	 * @return el número de filas afectadas
	 */
	@Modifying
	@Transactional
	@Query("UPDATE Turno t SET t.activo = :activo WHERE t.id = :id")
	int updateActivoById(@Param("id") Long id, @Param("activo") boolean activo);

}
