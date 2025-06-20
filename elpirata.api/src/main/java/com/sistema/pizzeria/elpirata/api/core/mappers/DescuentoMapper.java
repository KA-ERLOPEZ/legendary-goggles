package com.sistema.pizzeria.elpirata.api.core.mappers;

import org.mapstruct.AfterMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sistema.pizzeria.elpirata.api.Enums.EstadoConcepto;
import com.sistema.pizzeria.elpirata.api.core.commons.GenericMapper;
import com.sistema.pizzeria.elpirata.api.core.entities.Descuento;
import com.sistema.pizzeria.elpirata.api.web.dto.DescuentoDTO;

@Mapper(componentModel = "spring", 
uses = { ContratoMapper.class, ConceptoMapper.class }, unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface DescuentoMapper extends GenericMapper<Descuento, DescuentoDTO> {

	
	/**
	 * Convierte un objeto DescuentoDTO a un objeto Descuento.
	 *
	 * @param descuentoDTO el objeto DescuentoDTO a convertir
	 * @return el objeto Descuento resultante
	 */
	@Mapping(target = "estado", expression = "java(mapEstado(descuentoDTO.getEstado()))")
	@Mapping(target = "contrato", source = "descuentoDTO.contrato")
	@Mapping(target = "concepto.nombre", source = "descuentoDTO.conceptoNombre")
	@Mapping(target = "concepto.id", source = "descuentoDTO.conceptoId")
	@Override
	Descuento toEntity(DescuentoDTO descuentoDTO);
	/**
	 * Convierte un objeto Descuento a un objeto DescuentoDTO.
	 *
	 * @param descuento el objeto Descuento a convertir
	 * @return el objeto DescuentoDTO resultante
	 */
	@InheritInverseConfiguration
	@Override
	DescuentoDTO toDTO(Descuento descuento);

	
	/**
	 * Inicializa los objetos anidados de Descuento.
	 *
	 * @param descuento el objeto Descuento a inicializar
	 */
	@AfterMapping
	default void initNestedObjects(Descuento descuento) {
		if (descuento.getContrato() == null) {
			descuento.setContrato(new com.sistema.pizzeria.elpirata.api.core.entities.Contrato());
		}
		if (descuento.getConcepto() == null) {
			descuento.setConcepto(new com.sistema.pizzeria.elpirata.api.core.entities.Concepto());
		}
	}
	
	default EstadoConcepto mapEstado(String estado) {
		return EstadoConcepto.valueOf(estado);
	}
}
