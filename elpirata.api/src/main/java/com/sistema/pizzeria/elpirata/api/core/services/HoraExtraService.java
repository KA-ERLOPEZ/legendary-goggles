package com.sistema.pizzeria.elpirata.api.core.services;

import java.time.LocalDate;

import com.sistema.pizzeria.elpirata.api.core.services.generics.GenericService;
import com.sistema.pizzeria.elpirata.api.web.dto.HoraExtraDTO;
import com.sistema.pizzeria.elpirata.api.web.dto.PageResponseDTO;

public interface HoraExtraService extends GenericService<HoraExtraDTO, Long> {
	
	/**
	 * Busca todas las horas extras de un contrato
	 * 
	 * @param contratoId id del contrato
	 * 
	 * @param pageable   paginación
	 * 
	 * @return lista de horas extras
	 */
	public PageResponseDTO<HoraExtraDTO> getByContratoId(long contratoId, int page, int size);

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
	public PageResponseDTO<HoraExtraDTO> getByContratoIdAndEstado(long contratoId, String estado, int page, int size);
	
	/**
	 * Busca todas las horas extras de un empleado
	 * 
	 * @param page       número de página
	 * 
	 * @param size       tamaño de la página
	 * 
	 * @param empleadoId id del empleado
	 * 
	 * @return lista de horas extras
	 */
	public PageResponseDTO<HoraExtraDTO> findAllByEmpleadoId(int page, int size, Long empleadoId);
	
	/**
	 * Busca una hora extra por contrato y fecha
	 * 
	 * @param contratoId id del contrato
	 * 
	 * @param fecha      fecha de la hora extra
	 * 
	 * @return true si existe, false en caso contrario
	 */
	public HoraExtraDTO getByContratoIdAndFecha(long contratoId, LocalDate fecha);

	
	/**
	 * Busca todas las horas extras
	 * 
	 * @param page número de página
	 * 
	 * @param size tamaño de la página
	 * 
	 * @return lista de horas extras
	 */
	public PageResponseDTO<HoraExtraDTO> getAll(int page, int size);

}
