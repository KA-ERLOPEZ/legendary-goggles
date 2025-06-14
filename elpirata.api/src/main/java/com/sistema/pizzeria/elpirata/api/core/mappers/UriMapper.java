package com.sistema.pizzeria.elpirata.api.core.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sistema.pizzeria.elpirata.api.core.commons.GenericMapper;
import com.sistema.pizzeria.elpirata.api.core.entities.Uri;
import com.sistema.pizzeria.elpirata.api.web.dto.UriDTO;

@Mapper(componentModel = "spring", uses = {RoleUriMapper.class})
public interface UriMapper extends GenericMapper<Uri, UriDTO> {

	/**
	 * Convert a DTO to an entity.
	 *
	 * @param dto the DTO to convert
	 * @return the converted entity
	 */
	@Mapping(target = "uriId", source = "uriId")
	@Mapping(target = "uriDescription", source = "uriDescription")
	@Mapping(target = "estadoId", source = "estado.estadoId")
	@Mapping(target = "estadoNombre", source = "estado.estadoNombre")
	@Override
	UriDTO toDTO(Uri entity);

	/**
	 * Convert an entity to a DTO.
	 *
	 * @param entity the entity to convert
	 * @return the converted DTO
	 */
	@InheritInverseConfiguration
	@Override
	Uri toEntity(UriDTO dto);
}
