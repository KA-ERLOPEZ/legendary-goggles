package com.sistema.pizzeria.elpirata.api.core.exceptions;

import java.time.LocalDate;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    // Manejo de excepciones personalizadas
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ShowException> handleCustomException(CustomException ex, WebRequest request) {
        return new ResponseEntity<>(
                new ShowException(
                        LocalDate.now(),
                        ex.getMessage(),
                        request.getDescription(false)
                ),
                HttpStatus.NOT_FOUND
        );
    }

    // Manejo genérico de excepciones
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ShowException> handleGenericException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(
                new ShowException(
                        LocalDate.now(),
                        "Ocurrió un error inesperado. Por favor, intente más tarde.",
                        request.getDescription(false)
                ),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    // Manejo de errores de validación (MethodArgumentNotValidException)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        return new ResponseEntity<>(
                new ShowException(
                        LocalDate.now(),
                        "Errores de validación: " + message,
                        request.getDescription(false)
                ),
                HttpStatus.BAD_REQUEST
        );
    }

    // Manejo de excepciones específicas comunes
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ShowException> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        return new ResponseEntity<>(
                new ShowException(
                        LocalDate.now(),
                        ex.getMessage(),
                        request.getDescription(false)
                ),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ShowException> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
        String message = ex.getConstraintViolations().stream()
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .collect(Collectors.joining(", "));

        return new ResponseEntity<>(
                new ShowException(
                        LocalDate.now(),
                        "Errores de restricciones: " + message,
                        request.getDescription(false)
                ),
                HttpStatus.BAD_REQUEST
        );
    }
}

