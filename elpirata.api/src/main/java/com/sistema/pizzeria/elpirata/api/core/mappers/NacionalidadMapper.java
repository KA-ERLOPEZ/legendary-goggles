package com.sistema.pizzeria.elpirata.api.core.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.sistema.pizzeria.elpirata.api.core.commons.GenericMapper;
import com.sistema.pizzeria.elpirata.api.core.entities.Nacionalidad;
import com.sistema.pizzeria.elpirata.api.web.dto.NacionalidadDTO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NacionalidadMapper extends GenericMapper<Nacionalidad, NacionalidadDTO> {

}
