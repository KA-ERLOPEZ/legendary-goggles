package com.sistema.pizzeria.elpirata.api.core.services;

import java.util.List;

import com.sistema.pizzeria.elpirata.api.core.services.generics.GenericService;
import com.sistema.pizzeria.elpirata.api.web.dto.BarrioDTO;

public interface BarrioService extends GenericService<BarrioDTO, Long> {

	boolean existsByBarNombreAndCiudad_CiuId(String barNombre, long ciuId);

	List<BarrioDTO> findByCiudad_CiuId(long ciuId);
}
