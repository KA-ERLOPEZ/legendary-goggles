package com.sistema.pizzeria.elpirata.api.core.services.generics;

import java.util.List;

public interface GenericService<T, ID> {

	List<T> findAll();

	T findById(ID id);

	T save(T dto);
	
	T update(T dto);

	void deleteById(ID id);
}
