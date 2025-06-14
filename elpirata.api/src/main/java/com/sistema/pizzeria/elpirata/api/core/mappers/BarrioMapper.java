package com.sistema.pizzeria.elpirata.api.core.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.sistema.pizzeria.elpirata.api.core.commons.GenericMapper;
import com.sistema.pizzeria.elpirata.api.core.entities.Barrio;
import com.sistema.pizzeria.elpirata.api.web.dto.BarrioDTO;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BarrioMapper extends GenericMapper<Barrio, BarrioDTO> {

	@Mapping(target = "ciudadId", source = "ciudad.ciuId")
	@Mapping(target = "ciudadNombre", source = "ciudad.ciuNombre")
	@Mapping(target = "estadoId", source = "estado.estadoId")
	@Mapping(target = "estadoNombre", source = "estado.estadoNombre")
	@Override
	BarrioDTO toDTO(Barrio entiy);
	
	@Mapping(target = "ciudad.ciuId", source = "ciudadId")
	@Mapping(target = "ciudad.ciuNombre", source = "ciudadNombre")
	@Mapping(target = "estado.estadoId", source = "estadoId")
	@Mapping(target = "estado.estadoNombre", source = "estadoNombre")
	@Override
	Barrio toEntity(BarrioDTO dto);
	
}
