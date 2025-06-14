package com.sistema.pizzeria.elpirata.api.core.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.sistema.pizzeria.elpirata.api.core.commons.GenericMapper;
import com.sistema.pizzeria.elpirata.api.core.entities.UsuarioSesion;
import com.sistema.pizzeria.elpirata.api.web.dto.UsuarioSesionDTO;

@Mapper(componentModel = "spring", uses = {UsuarioMapper.class}, nullValuePropertyMappingStrategy  = NullValuePropertyMappingStrategy.IGNORE)
public interface UsuarioSesionMapper extends GenericMapper<UsuarioSesion, UsuarioSesionDTO> {

}
