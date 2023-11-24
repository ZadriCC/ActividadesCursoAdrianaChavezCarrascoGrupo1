package com.adriana.tienda.dto;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Validated
public class MetodoPagoDTO {

	private int idMetodoPago;

	@NotNull
	@Size(min = 5, max = 45)
	private String mpTipo;

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

	public MetodoPagoDTO() {
		super();
	}
}
