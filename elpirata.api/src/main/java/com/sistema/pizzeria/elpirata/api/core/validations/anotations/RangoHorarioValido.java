package com.sistema.pizzeria.elpirata.api.core.validations.anotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.sistema.pizzeria.elpirata.api.core.validations.validators.HoraExtraRangoHorarioValidator;

import jakarta.validation.Constraint;

@Constraint(validatedBy = {HoraExtraRangoHorarioValidator.class})
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface RangoHorarioValido {

	String message() default "Al menos un  campo no es válido";
	
	Class<?>[] groups() default {};
	Class<? extends jakarta.validation.Payload>[] payload() default {};
}
