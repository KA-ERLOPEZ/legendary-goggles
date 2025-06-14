package com.sistema.pizzeria.elpirata.api.core.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.sistema.pizzeria.elpirata.api.core.commons.GenericMapper;
import com.sistema.pizzeria.elpirata.api.core.entities.Marca;
import com.sistema.pizzeria.elpirata.api.web.dto.MarcaDTO;

/**
 * Mapper interface for converting between Marca entities and MarcaDTOs. This
 * interface extends the GenericMapper interface.
 * 
 * @see GenericMapper
 */
@Mapper(componentModel = "spring", uses = { EstadoMapper.class }, unmappedTargetPolicy = ReportingPolicy.IGNORE, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MarcaMapper extends GenericMapper<Marca, MarcaDTO> {

	/**
	 * Convert a Marca entity to a MarcaDTO.
	 *
	 * @param marca the Marca entity to convert
	 * @return the converted MarcaDTO
	 */
	@Mapping(target = "estadoId", source = "estado.estadoId")
	@Mapping(target = "estadoNombre", source = "estado.estadoNombre")
	@Override
	MarcaDTO toDTO(Marca marca);

	/**
	 * Convert a MarcaDTO to a Marca entity.
	 *
	 * @param marcaDTO the MarcaDTO to convert
	 * @return the converted Marca entity
	 */
	@Mapping(target = "estado.estadoId", source = "estadoId")
	@Override
	Marca toEntity(MarcaDTO marcaDTO);
}
