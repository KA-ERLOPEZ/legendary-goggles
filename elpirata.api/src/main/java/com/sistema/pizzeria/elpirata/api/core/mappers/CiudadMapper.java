package com.sistema.pizzeria.elpirata.api.core.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.sistema.pizzeria.elpirata.api.core.commons.GenericMapper;
import com.sistema.pizzeria.elpirata.api.core.entities.Ciudad;
import com.sistema.pizzeria.elpirata.api.web.dto.CiudadDTO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CiudadMapper extends GenericMapper<Ciudad, CiudadDTO> {
	
	@Mapping(target = "estadoId", source = "estado.estadoId")
	@Mapping(target = "estadoNombre", source = "estado.estadoNombre")
	@Mapping(target = "paisId", source = "pais.paisId")
	@Mapping(target = "paisNombre", source = "pais.paisNombre")
	@Override
	CiudadDTO toDTO(Ciudad entiy) ;
	
	
	@Mapping(target = "estado.estadoId", source = "estadoId")
	@Mapping(target = "estado.estadoNombre", source = "estadoNombre")
	@Mapping(target = "pais.paisId", source = "paisId")
	@Mapping(target = "pais.paisNombre", source = "paisNombre")
	@Override
	Ciudad toEntity(CiudadDTO dto);

}
