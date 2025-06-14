package com.sistema.pizzeria.elpirata.api.core.validations.anotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.sistema.pizzeria.elpirata.api.core.validations.validators.TurnoRangoHorarioValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = {TurnoRangoHorarioValidator.class })
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface TurnoValidation {
    String message() default "Al menos un campo no es válido";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
