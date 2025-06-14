package com.sistema.pizzeria.elpirata.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class EstadoDTO {

	private int estadoId;
    private boolean estadoEnabled;
    private String estadoNombre;

    private DominioDTO dominio; // Detalles completos del dominio
}
