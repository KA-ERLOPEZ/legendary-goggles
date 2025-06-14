package com.sistema.pizzeria.elpirata.api.core.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.pizzeria.elpirata.api.core.entities.TipoDocumento;

public interface TipoDocumentoRepository extends JpaRepository<TipoDocumento, Long> {

	/**
	 * Método para buscar un tipo de documento por su nombre
	 * 
	 * @param tipodocNombre Nombre del tipo de documento
	 * @return Optional TipoDocumento
	 */
	Optional<TipoDocumento> findByTipodocNombre(String tipodocNombre);

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
	 * @return List<TipoDocumento>
	 */
	List<TipoDocumento> findByEstadoEstadoId(int estadoId);
	
	/**
	 * Listar todos los tipos de documenbtos por coincidencia para el campo
	 * tipodocNombre
	 * 
	 * @param tipodocNombre Nombre del tipo de documento
	 * @return List<TipoDocumento>
	 */
	List<TipoDocumento> findByTipodocNombreContainingIgnoreCase(String tipodocNombre);
}
