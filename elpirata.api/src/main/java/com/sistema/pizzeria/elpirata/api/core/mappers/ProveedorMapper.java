package com.sistema.pizzeria.elpirata.api.core.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sistema.pizzeria.elpirata.api.core.commons.GenericMapper;
import com.sistema.pizzeria.elpirata.api.core.entities.Proveedor;
import com.sistema.pizzeria.elpirata.api.web.dto.ProveedorDTO;

@Mapper(componentModel = "spring", uses = {EstadoMapper.class})
public interface ProveedorMapper extends GenericMapper<Proveedor, ProveedorDTO> {

	/**
	 * Convert a DTO to an entity.
	 *
	 * @param dto the DTO to convert
	 * @return the converted entity
	 */
	@Mapping(target = "estadoId", source = "estado.estadoId")
	@Mapping(target = "estadoNombre", source = "estado.estadoNombre")
	@Mapping(target = "barId", source = "barrio.barId")
	@Mapping(target = "barNombre", source = "barrio.barNombre")
	@Override
	ProveedorDTO toDTO(Proveedor entity);
	
	/**
	 * Convert an entity to a DTO.
	 *
	 * @param entity the entity to convert
	 * @return the converted DTO
	 */
	@InheritInverseConfiguration
	@Override
	Proveedor toEntity(ProveedorDTO dto);
}
