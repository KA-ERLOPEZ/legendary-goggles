package com.sistema.pizzeria.elpirata.api.core.services;

import com.sistema.pizzeria.elpirata.api.core.services.generics.GenericService;
import com.sistema.pizzeria.elpirata.api.web.dto.DescuentoDTO;
import com.sistema.pizzeria.elpirata.api.web.dto.PageResponseDTO;

public interface DescuentoService extends GenericService<DescuentoDTO, Long> {
	
	/**
	 * Obtiene una lista paginada de descuentos por contrato.
	 *
	 * @param contratoId el ID del contrato
	 * @param page       el número de página
	 * @param size       el tamaño de la página
	 * @return una respuesta paginada con los descuentos
	 */
	PageResponseDTO<DescuentoDTO> getAllByContratoId(long contratoId, int page, int size);
	
	/**
	 * Obtiene una lista paginada de descuentos por concepto.
	 *
	 * @param conceptoId el ID del concepto
	 * @param page       el número de página
	 * @param size       el tamaño de la página
	 * @return una respuesta paginada con los descuentos
	 */
	PageResponseDTO<DescuentoDTO> getAllByConceptoId(long conceptoId, int page, int size);
	
	/**
	 * Obtiene una lista paginada de descuentos por concepto y estado.
	 *
	 * @param conceptoId el ID del concepto
	 * @param estado     el estado del descuento
	 * @param page       el número de página
	 * @param size       el tamaño de la página
	 * @return una respuesta paginada con los descuentos
	 */
	PageResponseDTO<DescuentoDTO> getAllByConceptoIdAndEstado(long conceptoId, String estado, int page, int size);
	
	/**
	 * Busca un descuento por contrato, concepto y fecha.
	 *
	 * @param contratoId el ID del contrato
	 * @param conceptoId el ID del concepto
	 * @param fecha      la fecha del descuento
	 * @return un DTO de descuento si se encuentra, o null si no se encuentra
	 */
	DescuentoDTO getByContratoIdAndConceptoIdAndFecha(long contratoId, long conceptoId, String fecha);
	
	/**
	 * Obtiene una lista paginada de descuentos entre dos fechas.
	 *
	 * @param startDate la fecha de inicio
	 * @param endDate   la fecha de fin
	 * @param page      el número de página
	 * @param size      el tamaño de la página
	 * @return una respuesta paginada con los descuentos
	 */
	PageResponseDTO<DescuentoDTO> getAllByFechaBetween(String startDate, String endDate, int page, int size);
	
	/**
	 * Obtiene una lista paginada de descuentos por estado.
	 *
	 * @param estado el estado del descuento
	 * @param page   el número de página
	 * @param size   el tamaño de la página
	 * @return una respuesta paginada con los descuentos
	 */
	PageResponseDTO<DescuentoDTO> getAllByEstado(String estado, int page, int size);
	
	/**
	 * Obtiene una lista paginada de descuentos por contrato y estado.
	 *
	 * @param contratoId el ID del contrato
	 * @param estado     el estado del descuento
	 * @param page       el número de página
	 * @param size       el tamaño de la página
	 * @return una respuesta paginada con los descuentos
	 */
	PageResponseDTO<DescuentoDTO> getAllByContratoIdAndEstado(long contratoId, String estado, int page, int size);
	
	
	
	
	
}
