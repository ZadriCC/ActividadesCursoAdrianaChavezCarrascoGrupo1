package com.adriana.tienda.dto;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Validated
public class ProductoDTO {

	private int idProducto;

	@NotNull
	@Size(min = 5, max = 45)
	private String pNombre;

	@NotNull
	@Size(min = 140, max = 1000)
	private String pDescripcion;

	@NotNull
	private double pPrecio;

	@NotNull
	private int pStock;

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getpNombre() {
		return pNombre;
	}

	public void setpNombre(String pNombre) {
		this.pNombre = pNombre;
	}

	public String getpDescripcion() {
		return pDescripcion;
	}

	public void setpDescripcion(String pDescripcion) {
		this.pDescripcion = pDescripcion;
	}

	public double getpPrecio() {
		return pPrecio;
	}

	public void setpPrecio(double pPrecio) {
		this.pPrecio = pPrecio;
	}

	public int getpStock() {
		return pStock;
	}

	public void setpStock(int pStock) {
		this.pStock = pStock;
	}

	public ProductoDTO() {
		super();
	}
}
