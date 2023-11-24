package com.adriana.tienda.excepciones;

import java.time.LocalDate;

public class ErrorDetalles {
	private LocalDate Tiempo;
	private String mensaje;
	private String detalles;

	public ErrorDetalles(LocalDate Tiempo, String mensaje, String detalles) {
		super();
		this.Tiempo = Tiempo;
		this.mensaje = mensaje;
		this.detalles = detalles;
	}

	public LocalDate getTiempo() {
		return Tiempo;
	}

	public void setTiempo(LocalDate Tiempo) {
		this.Tiempo = Tiempo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getDetalles() {
		return detalles;
	}

	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}

}
