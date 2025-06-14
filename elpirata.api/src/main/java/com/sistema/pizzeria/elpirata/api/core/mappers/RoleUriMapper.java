package com.sistema.pizzeria.elpirata.api.core.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.sistema.pizzeria.elpirata.api.core.commons.GenericMapper;
import com.sistema.pizzeria.elpirata.api.core.entities.RoleUri;
import com.sistema.pizzeria.elpirata.api.web.dto.RoleUriDTO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleUriMapper extends GenericMapper<RoleUri, RoleUriDTO> {

	@Mapping(source = "role", target = "role")
	@Mapping(source = "uri", target = "uri")
	@Override
	RoleUriDTO toDTO(RoleUri entiy);

	@Mapping(source = "role", target = "role")
	@Mapping(source = "uri", target = "uri")
	@Override
	RoleUri toEntity(RoleUriDTO dto);
}
