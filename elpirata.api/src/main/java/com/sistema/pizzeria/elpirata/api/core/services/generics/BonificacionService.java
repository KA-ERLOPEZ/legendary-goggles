package com.sistema.pizzeria.elpirata.api.core.services.generics;

import com.sistema.pizzeria.elpirata.api.web.dto.BonificacionDTO;
import com.sistema.pizzeria.elpirata.api.web.dto.PageResponseDTO;

public interface BonificacionService extends GenericService<BonificacionDTO, Long> {

	/**
	 * Listar bonificaciones por contrato
	 * 
	 * @param id
	 * @param page
	 * @param size
	 * @return Lista de bonificaciones
	 */
	PageResponseDTO<BonificacionDTO> findByContratoId(int page, int size, long id);

	/**
	 * Listar bonificaciones por contrato y nombre o apellido
	 * 
	 * @param nombre
	 * @param apellido
	 * @param page
	 * @param size
	 * @return Lista de bonificaciones
	 */
	PageResponseDTO<BonificacionDTO> findByNombreOrApellido(int page, int size, String nombre, String apellido);

	/**
	 * Listar bonificaciones por contrato y estado
	 * 
	 * @param estado
	 * @param contratoId
	 * @param page
	 * @param size
	 * 
	 * @return Lista de bonificaciones
	 */
	PageResponseDTO<BonificacionDTO> findByEstadoAndContratoId(int page, int size, String estado, long contratoId);

	/**
	 * Listar bonificaciones por contrato y concepto
	 * 
	 * @param conceptoId
	 * @param contratoId
	 * @param page
	 * @param size
	 * 
	 * @return Lista de bonificaciones
	 */
	BonificacionDTO getByContratoIdAndConceptoIdAndFecha(long contratoId, long conceptoId, String fecha);

	/**
	 * Listar bonificaciones por contrato y fecha
	 * 
	 * @param contratoId
	 * @param fecha
	 * @return Lista de bonificaciones
	 */
	PageResponseDTO<BonificacionDTO> findByContratoIdAndFecha(long contratoId, String fecha, int page, int size);

}
