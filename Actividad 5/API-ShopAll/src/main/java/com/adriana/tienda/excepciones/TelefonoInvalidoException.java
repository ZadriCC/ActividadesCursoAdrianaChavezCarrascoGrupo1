package com.adriana.tienda.excepciones;

public class TelefonoInvalidoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TelefonoInvalidoException(String message) {
		super(message);
	}

}
