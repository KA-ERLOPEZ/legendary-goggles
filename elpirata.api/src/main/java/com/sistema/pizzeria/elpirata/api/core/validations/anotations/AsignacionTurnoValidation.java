package com.sistema.pizzeria.elpirata.api.core.validations.anotations;

import static java.lang.annotation.RetentionPolicy.CLASS;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.sistema.pizzeria.elpirata.api.core.validations.validators.AsignacionTurnoFechaValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint( validatedBy  = {AsignacionTurnoFechaValidator.class})
@Target({ElementType.TYPE})
@Retention(CLASS)
public @interface AsignacionTurnoValidation {
	
	Class<?>[] groups () default{};
	Class<? extends Payload>[] payload() default{};

}
