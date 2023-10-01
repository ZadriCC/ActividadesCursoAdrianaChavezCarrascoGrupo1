package com.assessment.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String nombreCampo;
	private String nombreRecurso;
	private long valorCampo;

	public ResourceNotFoundException(String nombreCampo, String nombreRecurso, long valorCampo) {
		super(String.format("%s no enontrada con el %s no. '%s'", nombreCampo, nombreRecurso, valorCampo));
		this.nombreCampo = nombreCampo;
		this.nombreRecurso = nombreRecurso;
		this.valorCampo = valorCampo;
	}

	public String getNombreCampo() {
		return nombreCampo;
	}

	public void setNombreCampo(String nombreCampo) {
		this.nombreCampo = nombreCampo;
	}

	public String getNombreRecurso() {
		return nombreRecurso;
	}

	public void setNombreRecurso(String nombreRecurso) {
		this.nombreRecurso = nombreRecurso;
	}

	public long getValorCampo() {
		return valorCampo;
	}

	public void setValorCampo(long valorCampo) {
		this.valorCampo = valorCampo;
	}

}
