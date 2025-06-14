package com.sistema.pizzeria.elpirata.api.core.validations.validators;

import com.sistema.pizzeria.elpirata.api.core.validations.anotations.RangoHorarioValido;
import com.sistema.pizzeria.elpirata.api.web.dto.HoraExtraDTO;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class HoraExtraRangoHorarioValidator implements ConstraintValidator<RangoHorarioValido, HoraExtraDTO> {

	@Override
	public boolean isValid(HoraExtraDTO value, ConstraintValidatorContext context) {
		
		if (value.getHoraInicio() == null || value.getHoraFin() == null) {
			return true; // Si no hay horas, no se valida
		}
		
		if (value.getHoraInicio().isBefore(value.getHoraFin())) {
			return true; // Rango horario válido
		}
		
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate("El hora de inicio debe ser anterior a la hora de fin")
		.addPropertyNode("horaIncio").addConstraintViolation();
		
		return false; // Rango horario inválido
	}

}
