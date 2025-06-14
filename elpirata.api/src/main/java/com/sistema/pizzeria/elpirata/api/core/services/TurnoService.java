package com.sistema.pizzeria.elpirata.api.core.services;

import java.util.List;

import com.sistema.pizzeria.elpirata.api.core.services.generics.GenericService;
import com.sistema.pizzeria.elpirata.api.web.dto.TurnoDTO;

/**
 * Interfaz de servicio para la entidad Turno.
 * 
 * @author Digital Dynamics
 */
public interface TurnoService extends GenericService<TurnoDTO, Long> {
	
	/**
	 * Actualiza el estado activo de un turno.
	 * 
	 * @param id     el ID del turno a actualizar
	 * @param activo el nuevo estado activo del turno
	 * @return el objeto TurnoDTO actualizado
	 */
	public TurnoDTO updateTurnoActivo(Long id, boolean activo);
	
	/**
	 * Obtiene todos los turnos activos.
	 * 
	 * @return una lista de objetos TurnoDTO
	 */
	public List<TurnoDTO> getAllActivos();
}
