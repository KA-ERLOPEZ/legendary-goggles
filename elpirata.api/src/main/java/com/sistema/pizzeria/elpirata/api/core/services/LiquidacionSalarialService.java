package com.sistema.pizzeria.elpirata.api.core.services;

import com.sistema.pizzeria.elpirata.api.core.services.generics.GenericService;
import com.sistema.pizzeria.elpirata.api.web.dto.LiquidacionSalarialDTO;
import com.sistema.pizzeria.elpirata.api.web.dto.PageResponseDTO;


public interface LiquidacionSalarialService extends GenericService<LiquidacionSalarialDTO, Long> {
	
	
	/**
	 * Finds all LiquidacionSalarial records paginated
	 * 
	 * @param page the page number to retrieve
	 * @param size the number of records per page
	 * @return a paginated list of LiquidacionSalarialDTO
	 */
	PageResponseDTO<LiquidacionSalarialDTO> getAll(int page, int size);
	
	/**
	 * Finds all LiquidacionSalarial records by contratoId with pagination.
	 *
	 * @param contratoId the ID of the contrato to filter by
	 * @param page       the page number to retrieve
	 * @param size       the number of records per page
	 * @return a paginated list of LiquidacionSalarialDTO
	 */
	PageResponseDTO<LiquidacionSalarialDTO> getAllByContratoId(Long contratoId, int page, int size);

	/**
	 * Finds all LiquidacionSalarial records by contratoId and anio with pagination.
	 *
	 * @param contratoId the ID of the contrato to filter by
	 * @param anio       the year to filter by
	 * @param page       the page number to retrieve
	 * @param size       the number of records per page
	 * @return a paginated list of LiquidacionSalarialDTO
	 */
	PageResponseDTO<LiquidacionSalarialDTO> getAllByContratoIdAndAnio(Long contratoId, Integer anio, int page, int size);
	
	/**
	 * Find  LiquidacionSalarial records by contratoId, anio, and mes with
	 * pagination.
	 *
	 * @param contratoId the ID of the contrato to filter by
	 * @param anio       the year to filter by
	 * @param mes        the month to filter by
	 * @return a  LiquidacionSalarialDTO
	 */
	LiquidacionSalarialDTO getByContratoIdAndAnioAndMes(Long contratoId, Integer anio, Integer mes);
	
	/**
	 * Finds all LiquidacionSalarial records by contratoId and mes with pagination.
	 *
	 * @param contratoId the ID of the contrato to filter by
	 * @param mes        the month to filter by
	 * @param page       the page number to retrieve
	 * @param size       the number of records per page
	 * @return a paginated list of LiquidacionSalarialDTO
	 */
	PageResponseDTO<LiquidacionSalarialDTO> getAllByContratoIdAndMes(Long contratoId, Integer mes, int page, int size);
	
	/**
	 * Finds all LiquidacionSalarial records by anio with pagination.
	 *
	 * @param anio the year to filter by
	 * @param page the page number to retrieve
	 * @param size the number of records per page
	 * @return a paginated list of LiquidacionSalarialDTO
	 */
	PageResponseDTO<LiquidacionSalarialDTO> getAllByAnio(Integer anio, int page, int size);
	
	/**
	 * Finds all LiquidacionSalarial records by anio and mes with pagination.
	 *
	 * @param anio the year to filter by
	 * @param mes  the month to filter by
	 * @param page the page number to retrieve
	 * @param size the number of records per page
	 * @return a paginated list of LiquidacionSalarialDTO
	 */
	PageResponseDTO<LiquidacionSalarialDTO> getByAnioAndMes(Integer anio, Integer mes, int page, int size);
	
	/**
	 * Finds all LiquidacionSalarial records by contratoId and between fechaLiquidacionInicio and fechaLiquidacionFin and contratoId with pagination.
	 * 
	 * @param contratoId the ID of the contrato to filter by
	 * @param fechaLiquidacionInicio the date to filter by
	 * @param fechaLiquidacionFin the date to filter by
	 * @param page the page number to retrieve
	 * @param size the number of records per page
	 * @return a paginated list of LiquidacionSalarialDTO
	 */
	PageResponseDTO<LiquidacionSalarialDTO> getAllByContratoIdAndFechaLiquidacionBetween(Long contratoId, String fechaLiquidacionInicio, String fechaLiquidacionFin, int page, int size);
	
	/**
	 * Finds all LiquidacionSalarial records by anio and between fechaLiquidacionInicio and fechaLiquidacionFin with pagination.
	 * 
	 * @param anio                   the year to filter by
	 * @param fechaLiquidacionInicio the date to filter by
	 * @param fechaLiquidacionFin    the date to filter by
	 * @param page                   the page number to retrieve
	 * @param size                   the number of records per page
	 * @return a paginated list of LiquidacionSalarialDTO
	 */
	PageResponseDTO<LiquidacionSalarialDTO> getAllByAnioAndFechaLiquidacionBetween(Integer anio, String fechaLiquidacionInicio, String fechaLiquidacionFin, int page, int size);
	
	/**
	 * Finds all LiquidacionSalarial records by mes and between
	 * fechaLiquidacionInicio and fechaLiquidacionFin with pagination.
	 * 
	 * @param mes                    the month to filter by
	 * @param fechaLiquidacionInicio the date to filter by
	 * @param fechaLiquidacionFin    the date to filter by
	 * @param page                   the page number to retrieve
	 * @param size                   the number of records per page
	 * @return a paginated list of LiquidacionSalarialDTO
	 */
	PageResponseDTO<LiquidacionSalarialDTO> getAllByMesAndFechaLiquidacionBetween(Integer mes, String fechaLiquidacionInicio, String fechaLiquidacionFin, int page, int size);
	
	/**
	 * Finds all LiquidacionSalarial records by between fechaLiquidacionInicio and
	 * fechaLiquidacionFin with pagination.
	 * 
	 * @param fechaLiquidacionInicio the date to filter by
	 * @param fechaLiquidacionFin    the date to filter by
	 * @param page                   the page number to retrieve
	 * @param size                   the number of records per page
	 * @return a paginated list of LiquidacionSalarialDTO
	 */
	PageResponseDTO<LiquidacionSalarialDTO> getAllByFechaLiquidacionBetween(String fechaLiquidacionInicio, String fechaLiquidacionFin, int page, int size);
}
