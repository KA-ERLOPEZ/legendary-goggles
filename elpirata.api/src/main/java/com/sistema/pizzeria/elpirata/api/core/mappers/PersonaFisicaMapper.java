package com.sistema.pizzeria.elpirata.api.core.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.sistema.pizzeria.elpirata.api.core.commons.GenericMapper;
import com.sistema.pizzeria.elpirata.api.core.entities.PersonaFisica;
import com.sistema.pizzeria.elpirata.api.web.dto.PersonaFisicaDTO;

@Mapper(componentModel = "spring", 
uses = { BarrioMapper.class, NacionalidadMapper.class }, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PersonaFisicaMapper extends GenericMapper<PersonaFisica, PersonaFisicaDTO> {

	/**
	 * Convierte un DTO de PersonaFisica a una entidad de PersonaFisica.
	 *
	 * @param personaFisicaDTO el DTO a convertir
	 * @return la entidad convertida
	 */
	@Mapping(source = "barId", target = "barrio.barId")
	@Mapping(source = "nacId", target = "nacionalidad.nacId")
	@Mapping(source = "nacNombre", target = "nacionalidad.nacNombre")
	@Mapping(source = "barNombre", target = "barrio.barNombre")
	@Override
	PersonaFisica toEntity(PersonaFisicaDTO personaFisicaDTO);

	/**
	 * Convierte una entidad de PersonaFisica a un DTO de PersonaFisica.
	 *
	 * @param personaFisica la entidad a convertir
	 * @return el DTO convertido
	 */
	@InheritInverseConfiguration
	@Override
	PersonaFisicaDTO toDTO(PersonaFisica personaFisica);
}
