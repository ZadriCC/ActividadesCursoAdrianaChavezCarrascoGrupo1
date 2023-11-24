package com.adriana.tienda.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT)
public class CampoNuloOVacioException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CampoNuloOVacioException(String message) {
		super(message);
	}

}
