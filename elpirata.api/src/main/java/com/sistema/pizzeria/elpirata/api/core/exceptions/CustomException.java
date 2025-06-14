package com.sistema.pizzeria.elpirata.api.core.exceptions;

public class CustomException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 public CustomException(String message) {
	        super(message); // Envía el mensaje al constructor de RuntimeException
	    }

	    public CustomException(String message, Throwable cause) {
	        super(message, cause); // Permite adjuntar una excepción raíz
	    }

}
