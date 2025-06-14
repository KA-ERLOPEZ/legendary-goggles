package com.sistema.pizzeria.elpirata.api.core.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sistema.pizzeria.elpirata.api.core.commons.GenericMapper;
import com.sistema.pizzeria.elpirata.api.core.entities.Sabor;
import com.sistema.pizzeria.elpirata.api.web.dto.SaborDTO;

@Mapper(componentModel = "spring", uses = { EstadoMapper.class })
public interface SaborMapper extends GenericMapper<Sabor, SaborDTO> {

	@Mapping(target = "estadoId", source = "estado.estadoId")
	@Mapping(target = "estadoNombre", source = "estado.estadoNombre")
	@Override
	SaborDTO toDTO(Sabor entity);

	@Mapping(target = "estado.estadoId", source = "estadoId")
	@Override
	Sabor toEntity(SaborDTO dto);

}
