 package com.sistema.pizzeria.elpirata.api.core.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sistema.pizzeria.elpirata.api.core.commons.GenericMapper;
import com.sistema.pizzeria.elpirata.api.core.entities.Persona;
import com.sistema.pizzeria.elpirata.api.web.dto.PersonaDTO;

@Mapper(componentModel = "spring", uses = {NacionalidadMapper.class})
public interface PersonaMapper extends GenericMapper<Persona, PersonaDTO> {
	
	@Override
	@Mapping(target = "barId", source = "barrio.barId")
	@Mapping(target = "barNombre", source = "barrio.barNombre")
	PersonaDTO toDTO(Persona entity);
	
	@Override
	@Mapping(target = "barrio.barId", source = "dto.barId")
	@Mapping(target = "barrio.barNombre", source = "dto.barNombre")
	Persona toEntity(PersonaDTO dto);

}
