package com.adriana.tienda.datos.dto;

import java.util.Set;

import com.adriana.tienda.datos.Categoria;
import com.adriana.tienda.datos.Productos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class SubcategoriaDTO {

	private int idSubcategoria;

	@NotEmpty(message = "El campo titulo no debe estar vacio")
	@Size(min = 5, max = 45)
	private String scTitulo;

	private Categoria categoria;

	private Set<Productos> productos;

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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Set<Productos> getProductos() {
		return productos;
	}

	public void setProductos(Set<Productos> productos) {
		this.productos = productos;
	}

	public SubcategoriaDTO() {
		super();
	}
}
