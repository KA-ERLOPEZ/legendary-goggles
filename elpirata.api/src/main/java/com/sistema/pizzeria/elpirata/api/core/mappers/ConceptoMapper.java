package com.sistema.pizzeria.elpirata.api.core.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.sistema.pizzeria.elpirata.api.core.commons.GenericMapper;
import com.sistema.pizzeria.elpirata.api.core.entities.Concepto;
import com.sistema.pizzeria.elpirata.api.web.dto.ConceptoDTO;

/**
 * Mapper para la entidad Concepto
 * 
 * @author El Pirata
 *
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy =ReportingPolicy.IGNORE)
public interface ConceptoMapper extends GenericMapper<Concepto, ConceptoDTO> {

	/**
	 * Convierte un DTO a una entidad
	 * 
	 * @param dto
	 * @return Entidad
	 */
	
	@Override
	Concepto toEntity(ConceptoDTO dto);
	
	/**
	 * Convierte una entidad a un DTO
	 * 
	 * @param entity
	 * @return DTO
	 */
	
	@Override
	ConceptoDTO toDTO(Concepto entity);

}
