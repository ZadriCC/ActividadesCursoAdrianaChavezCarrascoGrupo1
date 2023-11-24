package com.adriana.tienda.datos;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Categorias")
public class Categoria {

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idCetegoria")
	private int idCategoria;

	@NotNull
	@Column(name = "ctTitulo", nullable = false, length = 45, unique = true)
	private String ctTitulo;

	@OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
	private List<Subcategoria> subcategorias;

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

	public List<Subcategoria> getSubcategorias() {
		return subcategorias;
	}

	public void setSubcategorias(List<Subcategoria> subcategorias) {
		this.subcategorias = subcategorias;
	}

	public Categoria() {
		super();
	}

	public Categoria(@NotNull int idCategoria, @NotNull String ctTitulo, List<Subcategoria> subcategorias) {
		super();
		this.idCategoria = idCategoria;
		this.ctTitulo = ctTitulo;
		this.subcategorias = subcategorias;
	}
}
