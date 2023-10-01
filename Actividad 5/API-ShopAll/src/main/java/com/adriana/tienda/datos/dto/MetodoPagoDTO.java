package com.adriana.tienda.datos.dto;

import java.util.Set;

import com.adriana.tienda.datos.Transacciones;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class MetodoPagoDTO {

	private int idMetodoPago;

	@NotEmpty(message = "El campo tipo no debe estar vacio")
	@Size(min = 5, max = 45)
	private String mpTipo;

	private Set<Transacciones> transacciones;

	public int getIdMetodoPago() {
		return idMetodoPago;
	}

	public void setIdMetodoPago(int idMetodoPago) {
		this.idMetodoPago = idMetodoPago;
	}

	public String getMpTipo() {
		return mpTipo;
	}

	public void setMpTipo(String mpTipo) {
		this.mpTipo = mpTipo;
	}

	public Set<Transacciones> getTransacciones() {
		return transacciones;
	}

	public void setTransacciones(Set<Transacciones> transacciones) {
		this.transacciones = transacciones;
	}

	public MetodoPagoDTO() {
		super();
	}
}
