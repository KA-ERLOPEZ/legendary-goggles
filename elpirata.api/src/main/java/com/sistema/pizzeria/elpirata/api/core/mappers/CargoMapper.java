package com.sistema.pizzeria.elpirata.api.core.mappers;

import org.mapstruct.Mapper;

import com.sistema.pizzeria.elpirata.api.core.commons.GenericMapper;
import com.sistema.pizzeria.elpirata.api.core.entities.Cargo;
import com.sistema.pizzeria.elpirata.api.web.dto.CargoDTO;

@Mapper(componentModel = "spring")
public interface CargoMapper extends GenericMapper<Cargo, CargoDTO> {

}
