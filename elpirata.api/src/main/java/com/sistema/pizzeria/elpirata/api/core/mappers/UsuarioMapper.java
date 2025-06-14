package com.sistema.pizzeria.elpirata.api.core.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.sistema.pizzeria.elpirata.api.core.commons.GenericMapper;
import com.sistema.pizzeria.elpirata.api.core.entities.Usuario;
import com.sistema.pizzeria.elpirata.api.web.dto.UsuarioAdminDTO;
import com.sistema.pizzeria.elpirata.api.web.dto.UsuarioClienteDTO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = { RoleMapper.class, PersonaMapper.class })
public interface UsuarioMapper extends GenericMapper<Usuario, UsuarioAdminDTO> {

	// Mapea Usuario -> UsuarioClienteDTO
	@Mapping(source = "estado.estadoId", target = "estadoId") // Relación con Estado
	UsuarioClienteDTO toUsuarioClienteDTO(Usuario usuario);

	// Mapea UsuarioClienteDTO -> Usuario
	@Mapping(source = "estadoId", target = "estado.estadoId") // Relación con Estado
	Usuario toUsuario(UsuarioClienteDTO dto);
}