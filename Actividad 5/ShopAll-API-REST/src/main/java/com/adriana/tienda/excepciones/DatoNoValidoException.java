package com.adriana.tienda.excepciones;

public class DatoNoValidoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DatoNoValidoException(String message) {
		super(message);
	}

}
