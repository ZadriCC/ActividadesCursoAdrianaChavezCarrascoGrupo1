package com.adriana.tienda.datos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Subcategorias")
public class Subcategoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idSubcategoria")
	private int idSubcategoria;

	@Column(name = "scTitulo", length = 45)
	private String scTitulo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idCategoria")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Categoria categoria;

	@OneToMany(mappedBy = "subcategoria", cascade = CascadeType.ALL)
	private List<Producto> productos;

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

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public Subcategoria() {
		super();
	}

	public Subcategoria(int idSubcategoria, String scTitulo, Categoria categoria, List<Producto> productos) {
		super();
		this.idSubcategoria = idSubcategoria;
		this.scTitulo = scTitulo;
		this.categoria = categoria;
		this.productos = productos;
	}
}
