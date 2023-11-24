package com.adriana.tienda.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RecursoNoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String nombreCampo;
	private String nombreRecurso;
	private int valorCampo;
	private String valorCampo2;

	public RecursoNoEncontradoException(String nombreCampo, String nombreRecurso, int valorCampo) {
		super(String.format("No hay %s con el %s no. '%s'", nombreCampo, nombreRecurso, valorCampo));
		this.nombreCampo = nombreCampo;
		this.nombreRecurso = nombreRecurso;
		this.valorCampo = valorCampo;
	}

	public RecursoNoEncontradoException(String nombreCampo, String nombreRecurso, String valorCampo) {
		super(String.format("No hay %s con el %s no. '%s'", nombreCampo, nombreRecurso, valorCampo));
		this.nombreCampo = nombreCampo;
		this.nombreRecurso = nombreRecurso;
		this.valorCampo2 = valorCampo;
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

	public int getValorCampo() {
		return valorCampo;
	}

	public void setValorCampo(int valorCampo) {
		this.valorCampo = valorCampo;
	}

	public String getValorCampo2() {
		return valorCampo2;
	}

	public void setValorCampo2(String valorCampo2) {
		this.valorCampo2 = valorCampo2;
	}
}
