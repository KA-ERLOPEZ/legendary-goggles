package com.sistema.pizzeria.elpirata.api.core.validations.validators;

import com.sistema.pizzeria.elpirata.api.core.validations.anotations.TurnoValidation;
import com.sistema.pizzeria.elpirata.api.web.dto.TurnoDTO;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;



public class TurnoRangoHorarioValidator implements ConstraintValidator<TurnoValidation, TurnoDTO> {

	@Override
	public boolean isValid(TurnoDTO value, ConstraintValidatorContext context) {
		
		if (value == null) {
			return true; // No se valida si el objeto es nulo
		}
		if (value.getHoraInicio() == null || value.getHoraFin() == null) {
			return true; // No se valida si las horas son nulas
		}
		
		if (value.getHoraInicio().isAfter(value.getHoraFin())) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate("La hora de inicio debe ser menor a la hora de fin")
					.addPropertyNode("horaInicio").addConstraintViolation();
			return false;
		}
		return true;
	}

}
