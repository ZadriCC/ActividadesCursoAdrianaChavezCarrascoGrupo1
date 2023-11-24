package com.adriana.tienda.dto;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Validated
public class SubcategoriaDTO {

	private int idSubcategoria;

	@NotNull
	@Size(min = 5, max = 45)
	private String scTitulo;

	public int getIdSubcategoria() {
		return idSubcategoria;
	}

	public void setIdSubcategoria(int idSubcategoria) {
		this.idSubcategoria = idSubcategoria;
	}

	public String getScTitulo() {
		return scTitulo;
	}

	public void setScTitulo(String scTitulo) {
		this.scTitulo = scTitulo;
	}

	public SubcategoriaDTO() {
		super();
	}
}
