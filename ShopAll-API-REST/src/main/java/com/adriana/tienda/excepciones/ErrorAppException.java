package com.adriana.tienda.excepciones;

public class ErrorAppException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ErrorAppException(String message) {
        super(message);
    }

}