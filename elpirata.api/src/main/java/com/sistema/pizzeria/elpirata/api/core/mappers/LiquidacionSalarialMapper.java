package com.sistema.pizzeria.elpirata.api.core.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sistema.pizzeria.elpirata.api.core.commons.GenericMapper;
import com.sistema.pizzeria.elpirata.api.core.entities.LiquidacionSalarial;
import com.sistema.pizzeria.elpirata.api.web.dto.LiquidacionSalarialDTO;

@Mapper(componentModel = "spring", uses = { ContratoMapper.class })
public interface LiquidacionSalarialMapper extends GenericMapper<LiquidacionSalarial, LiquidacionSalarialDTO> {
	
	/**
	 * Convierte una entidad LiquidacionSalarial a un DTO LiquidacionSalarialDTO.
	 * 
	 * @param entity la entidad a convertir
	 * @return el DTO convertido
	 */
	@Mapping(target = "contratoId", source = "contrato.id")
	@Mapping(target = "nombreEmpleado", source = "contrato.persona.perNombre")
	@Mapping(target = "apellidoEmpleado", source = "contrato.persona.perApellido")
	@Mapping(target = "ciEmpleado", source = "contrato.persona.perNroCedula")
	@Override
	LiquidacionSalarialDTO toDTO(LiquidacionSalarial entity);

	/**
	 * Convierte un DTO LiquidacionSalarialDTO a una entidad LiquidacionSalarial.
	 * 
	 * @param dto el DTO a convertir
	 * @return la entidad convertida
	 */
	@InheritInverseConfiguration
	@Override
	LiquidacionSalarial toEntity(LiquidacionSalarialDTO dto);

	/**
	 * Inicializa los objetos anidados de LiquidacionSalarial.
	 *
	 * @param liquidacionSalarial el objeto LiquidacionSalarial a inicializar
	 */
	@org.mapstruct.AfterMapping
	default void initNestedObjects(LiquidacionSalarial liquidacionSalarial) {
        if (liquidacionSalarial.getContrato() == null) {
            liquidacionSalarial.setContrato(new com.sistema.pizzeria.elpirata.api.core.entities.Contrato());
        }
    }
	
}
