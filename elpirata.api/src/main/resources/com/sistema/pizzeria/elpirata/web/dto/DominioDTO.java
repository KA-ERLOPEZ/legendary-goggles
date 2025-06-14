package com.sistema.pizzeria.elpirata.web.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class DominioDTO {
	
    private int dominioId;

    @NotNull(message = "El código del dominio no puede ser nulo.")
    @Size(max = 50, message = "El código del dominio no puede exceder 50 caracteres.")
    private String dominioCodigo;

    @NotNull(message = "El estado del dominio no puede ser nulo.")
    private byte dominioEnabled;

    @NotNull(message = "El nombre del dominio no puede ser nulo.")
    @Size(max = 100, message = "El nombre del dominio no puede exceder 100 caracteres.")
    private String dominioNombre;

    // Getters y Setters
}

