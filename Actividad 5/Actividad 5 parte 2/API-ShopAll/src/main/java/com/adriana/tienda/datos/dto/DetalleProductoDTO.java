package com.adriana.tienda.datos.dto;

import com.adriana.tienda.datos.Productos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class DetalleProductoDTO {

	private int idDProducto;

	@NotEmpty(message = "El campo atributo no debe estar vacio")
	@Size(min = 10, max = 45)
	private String dpAtributo;

	@NotEmpty(message = "El campo valor no debe estar vacio")
	@Size(min = 10, max = 45)
	private String dpValor;

	private Productos productos;

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

	public Productos getProductos() {
		return productos;
	}

	public void setProductos(Productos productos) {
		this.productos = productos;
	}

	public DetalleProductoDTO() {
		super();
	}
}
