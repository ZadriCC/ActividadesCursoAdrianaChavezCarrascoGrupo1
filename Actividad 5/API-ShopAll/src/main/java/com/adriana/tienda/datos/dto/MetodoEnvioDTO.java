package com.adriana.tienda.datos.dto;

import java.util.Set;

import com.adriana.tienda.datos.Transacciones;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class MetodoEnvioDTO {

	private int idMetodoEnvio;

	@NotEmpty(message = "El campo tipo no debe estar vacio")
	@Size(min = 5, max = 45)
	private String meTipo;

	private Set<Transacciones> transacciones;

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

	public Set<Transacciones> getTransacciones() {
		return transacciones;
	}

	public void setTransacciones(Set<Transacciones> transacciones) {
		this.transacciones = transacciones;
	}

	public MetodoEnvioDTO() {
		super();
	}
}
