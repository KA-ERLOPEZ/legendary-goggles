package com.sistema.pizzeria.elpirata.api.core.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.sistema.pizzeria.elpirata.api.core.commons.GenericMapper;
import com.sistema.pizzeria.elpirata.api.core.entities.Contrato;
import com.sistema.pizzeria.elpirata.api.web.dto.ContratoDTO;

@Mapper(componentModel = "spring", uses = {
		PersonaMapper.class, 
		TipoDocumentoMapper.class,
		EstadoMapper.class,
		CargoMapper.class,
		},
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ContratoMapper extends GenericMapper<Contrato, ContratoDTO> {
	
	/**
	 * Convierte un DTO a una entidad
	 * 
	 * @param dto el DTO a convertir
	 * @return la entidad convertida
	 */
	@Mapping(target = "persona", source = "persona")
	@Mapping(target = "tipoDocumento", source = "tipoDocumento")
	@Mapping(target = "estado.estadoId", source = "estadoId")
	@Mapping(target = "cargo.cargoId", source = "cargoId")
	@Override
	Contrato toEntity(ContratoDTO dto);

	/**
	 * Convierte una entidad a un DTO
	 * 
	 * @param entity la entidad a convertir
	 * @return el DTO convertido
	 */
	@InheritInverseConfiguration
	@Override
	ContratoDTO toDTO(Contrato entity);

}
