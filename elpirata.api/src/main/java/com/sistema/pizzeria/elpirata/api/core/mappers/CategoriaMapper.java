package com.sistema.pizzeria.elpirata.api.core.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sistema.pizzeria.elpirata.api.core.commons.GenericMapper;
import com.sistema.pizzeria.elpirata.api.core.entities.Categoria;
import com.sistema.pizzeria.elpirata.api.web.dto.CategoriaDTO;

@Mapper(componentModel = "spring", uses = { EstadoMapper.class })
public interface CategoriaMapper extends GenericMapper<Categoria, CategoriaDTO> {

	/**
	 * Convierte un CategoriaDTO a un Categoria.
	 * 
	 * @param categoriaDTO El CategoriaDTO a convertir.
	 * @return El Categoria convertido.
	 */
	@Mapping(target = "estado.estadoId", source = "estadoId")
	@Override
	Categoria toEntity(CategoriaDTO categoriaDTO);

	/**
	 * Convierte un Categoria a un CategoriaDTO.
	 * 
	 * @param categoria El Categoria a convertir.
	 * @return El CategoriaDTO convertido.
	 */
	@Mapping(target = "estadoId", source = "estado.estadoId")
	@Mapping(target = "estadoNombre", source = "estado.estadoNombre")
	@Override
	CategoriaDTO toDTO(Categoria categoria);
}
