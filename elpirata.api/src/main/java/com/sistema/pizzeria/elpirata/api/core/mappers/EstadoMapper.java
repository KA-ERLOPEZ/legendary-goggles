package com.sistema.pizzeria.elpirata.api.core.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.sistema.pizzeria.elpirata.api.core.commons.GenericMapper;
import com.sistema.pizzeria.elpirata.api.core.entities.Estado;
import com.sistema.pizzeria.elpirata.api.web.dto.EstadoDTO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EstadoMapper extends GenericMapper<Estado, EstadoDTO> {

	@Mapping(target = "dominioId", source = "dominio.dominioId")
	@Mapping(target = "dominioNombre", source = "dominio.dominioNombre")
	@Override
	EstadoDTO toDTO(Estado entiy);
	
	@Mapping(target = "dominio.dominioId", source = "dominioId")
	@Mapping(target = "dominio.dominioNombre", source = "dominioNombre")
	@Override
	Estado toEntity(EstadoDTO dto);
	
}