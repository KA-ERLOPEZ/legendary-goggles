package com.sistema.pizzeria.elpirata.api.core.services;

import java.util.List;

import com.sistema.pizzeria.elpirata.api.core.services.generics.GenericService;
import com.sistema.pizzeria.elpirata.api.web.dto.TipoDocumentoDTO;

public interface TipoDocumentoService extends GenericService<TipoDocumentoDTO, Long> {
	
	/**
	 * Método para buscar un tipo de documento por su nombre
	 * 
	 * @param tipodocNombre Nombre del tipo de documento
	 * @return TipoDocumentoDTO
	 */
	TipoDocumentoDTO findByTipodocNombre(String tipodocNombre);

	/**
	 * Método para validar si existe un tipo de documento por su nombre
	 * 
	 * @param tipodocNombre Nombre del tipo de documento
	 * @return boolean
	 */
	boolean existsByTipodocNombre(String tipodocNombre);

	/**
	 * Mètodo para listar todos los tipos de documentos por estado
	 * 
	 * @param estadoId Id del estado
	 * @return List<TipoDocumentoDTO>
	 */
	List<TipoDocumentoDTO> findByEstadoId(int estadoId);
	
	/**
	 * Listar por coincidencia parcial por nombre(ignorando minusculas y mayusculas)
	 * 
	 * @param tipoDocId
	 * @return List<TipoDocumentoDTO>
	 */
	List<TipoDocumentoDTO> getByTipoDocNombreContainingIgnoreCase(String tipoDocNombre);

}
