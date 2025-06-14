package com.sistema.pizzeria.elpirata.api.core.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.sistema.pizzeria.elpirata.api.core.commons.GenericMapper;
import com.sistema.pizzeria.elpirata.api.core.entities.HoraExtra;
import com.sistema.pizzeria.elpirata.api.web.dto.HoraExtraDTO;

@Mapper(componentModel = "spring", uses = {
		ContratoMapper.class }, unmappedSourcePolicy = ReportingPolicy.IGNORE, 
				unmappedTargetPolicy = ReportingPolicy.IGNORE, 
				nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
public interface HoraExtraMapper extends GenericMapper<HoraExtra, HoraExtraDTO> {

	/**
	 * Convierte un DTO a una entidad
	 * 
	 * @param dto el DTO a convertir
	 * @return la entidad convertida
	 */

	@Override
	HoraExtra toEntity(HoraExtraDTO dto);

	/**
	 * Convierte una entidad a un DTO
	 * 
	 * @param entity la entidad a convertir
	 * @return el DTO convertido
	 */
	@Override
	HoraExtraDTO toDTO(HoraExtra entity);
}
