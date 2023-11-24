package com.adriana.tienda.datos;

import java.util.HashSet;
import java.util.Set;

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
@Table(name = "Categoria")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	@Column(name = "idCategoria")
	private int idCategoria;

	@NotNull
	@Column(name = "ctTitulo", length = 45, unique = true)
	private String ctTitulo;

	@OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
	private Set<Subcategoria> subcategorias = new HashSet<>();

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

	public Categoria() {
		super();
	}

	public Categoria(@NotNull int idCategoria, @NotNull String ctTitulo, Set<Subcategoria> subcategoria) {
		super();
		this.idCategoria = idCategoria;
		this.ctTitulo = ctTitulo;
		this.subcategorias = subcategoria;
	}
}
