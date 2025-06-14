package com.sistema.pizzeria.elpirata.api.core.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.sistema.pizzeria.elpirata.api.core.commons.GenericMapper;
import com.sistema.pizzeria.elpirata.api.core.entities.UsuarioLogin;
import com.sistema.pizzeria.elpirata.api.web.dto.UsuarioLoginDTO;

@Mapper(componentModel = "spring")
public interface UsuarioLoginMapper extends GenericMapper<UsuarioLogin, UsuarioLoginDTO> {

	UsuarioLoginMapper INSTANCE = Mappers.getMapper(UsuarioLoginMapper.class);


    @Mapping(source = "usuario.usuId", target = "usuarioId")
    @Mapping(source = "usuario.usuUsername", target = "usuarioUsername")
	@Override
	UsuarioLoginDTO toDTO(UsuarioLogin entiy);
    
    @Mapping(source = "usuarioId", target = "usuario.usuId")
	@Override
	UsuarioLogin toEntity(UsuarioLoginDTO dto);
}
