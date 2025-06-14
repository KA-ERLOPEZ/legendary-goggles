package com.sistema.pizzeria.elpirata.api.core.commons;

import java.util.List;

public interface GenericMapper<T, D> {

	D toDTO(T entiy);

	// Mapea un DTO a una entidad
	T toEntity(D dto);

	// Mapea una lista de entidades a una lista de DTOs
	List<D> toDTOList(List<T> entities);

	// Mapea una lista de DTOs a una lista de entidades
	List<T> toEntityList(List<D> dtos);
}
