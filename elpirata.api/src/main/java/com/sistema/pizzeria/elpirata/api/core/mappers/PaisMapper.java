package com.sistema.pizzeria.elpirata.api.core.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.sistema.pizzeria.elpirata.api.core.commons.GenericMapper;
import com.sistema.pizzeria.elpirata.api.core.entities.Pais;
import com.sistema.pizzeria.elpirata.api.web.dto.PaisDTO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {CiudadMapper.class, EstadoMapper.class})
public interface PaisMapper extends GenericMapper<Pais,PaisDTO> {

}
