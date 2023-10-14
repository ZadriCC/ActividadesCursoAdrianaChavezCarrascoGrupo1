package com.adriana.tienda.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class StockInsuficienteException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public StockInsuficienteException(String message) {
		super(message);
	}
}
