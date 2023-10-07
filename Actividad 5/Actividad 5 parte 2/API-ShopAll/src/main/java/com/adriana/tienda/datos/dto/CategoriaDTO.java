package com.adriana.tienda.datos.dto;

import java.util.Set;

import com.adriana.tienda.datos.Subcategoria;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class CategoriaDTO {

	private int idCategoria;

	@NotEmpty(message = "El campo titulo no debe estar vacio")
	@Size(min = 5, max = 45)
	private String ctTitulo;

	private Set<Subcategoria> subcategorias;

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

	public Set<Subcategoria> getSubcategoria() {
		return subcategorias;
	}

	public void setSubcategoria(Set<Subcategoria> subcategoria) {
		this.subcategorias = subcategoria;
	}

	public CategoriaDTO() {
		super();
	}
}
