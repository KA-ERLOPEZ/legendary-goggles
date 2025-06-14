package com.sistema.pizzeria.elpirata.api.core.validations.validators;

import com.sistema.pizzeria.elpirata.api.core.validations.anotations.AsignacionTurnoValidation;
import com.sistema.pizzeria.elpirata.api.web.dto.AsignacionTurnoDTO;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AsignacionTurnoFechaValidator implements ConstraintValidator<AsignacionTurnoValidation, AsignacionTurnoDTO> {

	@Override
	public boolean isValid(AsignacionTurnoDTO value, ConstraintValidatorContext context) {
		
		if(value == null) {
			return true;
		}
		if(value.getFechaInicio() == null || value.getFechaFin() == null) {
			return true;
		}
		
		if(!value.getFechaInicio().isAfter(value.getFechaFin())) {
			return true;
		}
		
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate("La fecha de inicio debe ser antes que la fecha fin")
		.addPropertyNode("FechaInicio").addConstraintViolation();
		return false;
	}

}
