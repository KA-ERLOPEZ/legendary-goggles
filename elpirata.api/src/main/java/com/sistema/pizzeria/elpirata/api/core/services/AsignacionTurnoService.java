package com.sistema.pizzeria.elpirata.api.core.services;

import com.sistema.pizzeria.elpirata.api.core.services.generics.GenericService;
import com.sistema.pizzeria.elpirata.api.web.dto.AsignacionTurnoDTO;
import com.sistema.pizzeria.elpirata.api.web.dto.PageResponseDTO;

public interface AsignacionTurnoService extends GenericService<AsignacionTurnoDTO, Long> {

    /**
     * Listar todas las asignaciones paginadas
     * 
     * @param page número de página
     * @param size tamaño de cada página
     * @return PageResponseDTO<AsignacionTurnoDTO>
     */
    PageResponseDTO<AsignacionTurnoDTO> getAllPage(int page, int size);

    /**
     * Listar todas las asignaciones activas
     * 
     * @param page número de página
     * @param size tamaño de cada página
     * @return PageResponseDTO<AsignacionTurnoDTO>
     */
    PageResponseDTO<AsignacionTurnoDTO> getAllByActivoTrue(int page, int size);

    /**
     * Listar todas las asignaciones activas por contrato
     * 
     * @param page número de página
     * @param size tamaño de cada página
     * @param contratoId ID del contrato
     * @return PageResponseDTO<AsignacionTurnoDTO>
     */
    PageResponseDTO<AsignacionTurnoDTO> getAllByContratoAndActivoTrue(int page, int size, long contratoId);

    /**
     * Listar todas las asignaciones activas por turno
     * 
     * @param page número de página
     * @param size tamaño de cada página
     * @param turnoId ID del turno
     * @return PageResponseDTO<AsignacionTurnoDTO>
     */
    PageResponseDTO<AsignacionTurnoDTO> getAllByTurnoAndActivoTrue(int page, int size, long turnoId);

    /**
     * Actualizar una asignación de turno por su ID
     * 
     * @param id ID de la asignación
     * @param asignacionTurnoDTO DTO con los nuevos datos
     * @return AsignacionTurnoDTO actualizado
     */
    AsignacionTurnoDTO update(long id, AsignacionTurnoDTO asignacionTurnoDTO);
}

