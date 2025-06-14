package com.sistema.pizzeria.elpirata.api.core.mappers;


import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.sistema.pizzeria.elpirata.api.core.commons.GenericMapper;
import com.sistema.pizzeria.elpirata.api.core.entities.AsignacionTurno;
import com.sistema.pizzeria.elpirata.api.web.dto.AsignacionTurnoDTO;

@Mapper(componentModel = "spring", 
uses = {ContratoMapper.class, TurnoMapper.class},
unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface AsignacionTurnoMapper extends GenericMapper<AsignacionTurno, AsignacionTurnoDTO> {

	

}
