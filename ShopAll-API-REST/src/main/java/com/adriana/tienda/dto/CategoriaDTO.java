package com.adriana.tienda.dto;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Validated
public class CategoriaDTO {

	private int idCategoria;

	@NotNull
	@Size(min = 4, max = 45)
	private String ctTitulo;

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getCtTitulo() {
		return ctTitulo;
	}

	public void setCtTitulo(String ctTitulo) {
		this.ctTitulo = ctTitulo;
	}

	public CategoriaDTO() {
		super();
	}
}
