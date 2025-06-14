package com.sistema.pizzeria.elpirata.api.core.mappers;



import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.sistema.pizzeria.elpirata.api.core.commons.GenericMapper;
import com.sistema.pizzeria.elpirata.api.core.entities.Dominio;
import com.sistema.pizzeria.elpirata.api.web.dto.DominioDTO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DominioMapper extends GenericMapper<Dominio, DominioDTO>{

}

