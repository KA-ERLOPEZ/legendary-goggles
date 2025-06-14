package com.sistema.pizzeria.elpirata.api.core.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sistema.pizzeria.elpirata.api.core.commons.GenericMapper;
import com.sistema.pizzeria.elpirata.api.core.entities.Tamano;
import com.sistema.pizzeria.elpirata.api.web.dto.TamanoDTO;

/**
 * Mapper for the entity Tamano and its DTO TamanoDTO.
 * 
 * @see Tamano
 */

@Mapper(componentModel = "spring", uses = { EstadoMapper.class })
public interface TamanoMapper extends GenericMapper<Tamano, TamanoDTO> {

	/**
	 * Convert the entity to DTO.
	 *
	 * @param entity the entity to convert
	 * @return the converted DTO
	 */
	@Mapping(target = "estadoId", source = "estado.estadoId")
	@Mapping(target = "estadoNombre", source = "estado.estadoNombre")
	@Override
	TamanoDTO toDTO(Tamano entity);
	
	/**
	 * Convert the DTO to entity.
	 *
	 * @param dto the DTO to convert
	 * @return the converted entity
	 */
	@Mapping(target = "estado.estadoId", source = "estadoId")
	@Override
	Tamano toEntity(TamanoDTO dto);
}
