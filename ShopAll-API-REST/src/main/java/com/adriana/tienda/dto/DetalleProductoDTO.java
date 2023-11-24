package com.adriana.tienda.dto;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Validated
public class DetalleProductoDTO {

	private int idDProducto;

	@NotNull
	@Size(min = 10, max = 45)
	private String dpAtributo;

	@NotNull
	@Size(min = 10, max = 45)
	private String dpValor;

	public int getIdDProducto() {
		return idDProducto;
	}

	public void setIdDProducto(int idDProducto) {
		this.idDProducto = idDProducto;
	}

	public String getDpAtributo() {
		return dpAtributo;
	}

	public void setDpAtributo(String dpAtributo) {
		this.dpAtributo = dpAtributo;
	}

	public String getDpValor() {
		return dpValor;
	}

	public void setDpValor(String dpValor) {
		this.dpValor = dpValor;
	}

	public DetalleProductoDTO() {
		super();
	}
}
