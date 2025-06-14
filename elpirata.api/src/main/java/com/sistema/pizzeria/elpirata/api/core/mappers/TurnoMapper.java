package com.sistema.pizzeria.elpirata.api.core.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.sistema.pizzeria.elpirata.api.core.commons.GenericMapper;
import com.sistema.pizzeria.elpirata.api.core.entities.Turno;
import com.sistema.pizzeria.elpirata.api.web.dto.TurnoDTO;

/**
 * Mapper interface for converting between Turno entity and TurnoDTO.
 * 
 * @author Digital Dynamics
 */
@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TurnoMapper extends GenericMapper<Turno, TurnoDTO> {
	
}
