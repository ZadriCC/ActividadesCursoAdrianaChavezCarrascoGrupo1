package com.adriana.tienda.dto;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Validated
public class MetodoEnvioDTO {

	private int idMetodoEnvio;

	@NotNull
	@Size(min = 5, max = 45)
	private String meTipo;

	@NotNull
	private double mePrecio;

	public int getIdMetodoEnvio() {
		return idMetodoEnvio;
	}

	public void setIdMetodoEnvio(int idMetodoEnvio) {
		this.idMetodoEnvio = idMetodoEnvio;
	}

	public String getMeTipo() {
		return meTipo;
	}

	public void setMeTipo(String meTipo) {
		this.meTipo = meTipo;
	}

	public double getMePrecio() {
		return mePrecio;
	}

	public void setMePrecio(double mePrecio) {
		this.mePrecio = mePrecio;
	}

	public MetodoEnvioDTO() {
		super();
	}
}
