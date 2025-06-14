package com.sistema.pizzeria.elpirata.api.core.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.sistema.pizzeria.elpirata.api.core.commons.GenericMapper;
import com.sistema.pizzeria.elpirata.api.core.entities.TipoDocumento;
import com.sistema.pizzeria.elpirata.api.web.dto.TipoDocumentoDTO;

@Mapper(componentModel = "spring", uses = { EstadoMapper.class }, 
unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TipoDocumentoMapper extends GenericMapper<TipoDocumento, TipoDocumentoDTO> {

	@Mapping(target = "estadoId", source = "estado.estadoId")
	@Mapping(target = "estadoNombre", source = "estado.estadoNombre")
	@Override
	TipoDocumentoDTO toDTO(TipoDocumento entity);
	
	@InheritInverseConfiguration
	@Override
	TipoDocumento toEntity(TipoDocumentoDTO dto);
}
