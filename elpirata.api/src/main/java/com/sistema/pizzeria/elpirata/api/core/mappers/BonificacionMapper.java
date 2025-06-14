package com.sistema.pizzeria.elpirata.api.core.mappers;

import org.mapstruct.AfterMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import com.sistema.pizzeria.elpirata.api.core.commons.GenericMapper;
import com.sistema.pizzeria.elpirata.api.core.entities.Bonificacion;
import com.sistema.pizzeria.elpirata.api.core.entities.Contrato;
import com.sistema.pizzeria.elpirata.api.core.entities.EstadoConcepto;
import com.sistema.pizzeria.elpirata.api.core.entities.Persona;
import com.sistema.pizzeria.elpirata.api.web.dto.BonificacionDTO;

@Mapper(componentModel = "spring", uses = {ContratoMapper.class},
unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BonificacionMapper extends GenericMapper<Bonificacion, BonificacionDTO> {
	
	@Mapping(target = "estado", expression = "java(mapEstado(dto.getEstado()))")
	@Mapping(target = "contrato.persona.perNombre", source = "nombre")
	@Mapping( target = "contrato.persona.perApellido", source = "apellido")
	@Mapping(target = "contrato.persona.perNroCedula", source = "nroCedula")
	@Mapping(target = "concepto.id", source = "conceptoId")
	@Mapping(target = "concepto.nombre", source = "concepto")
	@Mapping(target = "contrato.id", source = "contratoId")
	@Override
	Bonificacion toEntity(BonificacionDTO dto); 
	
	
	
	@InheritInverseConfiguration
	@Override
	BonificacionDTO toDTO(Bonificacion dto);
	
	default EstadoConcepto mapEstado(String estado) {
		return EstadoConcepto.valueOf(estado);
	}
	
	// Inicialización de estructuras anidadas
    @AfterMapping
    default void initNestedObjects(@MappingTarget Bonificacion bonificacion, BonificacionDTO dto) {
        if (bonificacion.getContrato() == null) {
            bonificacion.setContrato(new Contrato());
            bonificacion.setContrato(new Contrato());
        }
        if (bonificacion.getContrato().getPersona() == null) {
            bonificacion.getContrato().setPersona(new Persona());
       
        }
    }


}
