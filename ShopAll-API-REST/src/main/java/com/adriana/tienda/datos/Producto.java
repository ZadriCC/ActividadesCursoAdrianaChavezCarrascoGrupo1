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
@Table(name = "Productos")
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idProducto")
	private int idProducto;

	@Column(name = "pNombre", length = 45)
	private String pNombre;

	@Column(name = "pDescripcion", length = 1000)
	private String pDescripcion;

	@Column(name = "pPrecio")
	private double pPrecio;

	@Column(name = "pStock")
	private int pStock;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "idVendedor")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Vendedor vendedor;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "idSubcategoria")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Subcategoria subcategoria;

	@OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
	private List<Reseña> reseñas;

	@OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
	private List<DetalleProducto> dtProductos;

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

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public Subcategoria getSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(Subcategoria subcategoria) {
		this.subcategoria = subcategoria;
	}

	public List<Reseña> getReseñas() {
		return reseñas;
	}

	public void setReseñas(List<Reseña> reseñas) {
		this.reseñas = reseñas;
	}

	public List<DetalleProducto> getDtProductos() {
		return dtProductos;
	}

	public void setDtProductos(List<DetalleProducto> dtProductos) {
		this.dtProductos = dtProductos;
	}

	public Producto() {
		super();
	}

	public Producto(int idProducto, String pNombre, String pDescripcion, double pPrecio, int pStock, Vendedor vendedor,
			Subcategoria subcategoria, List<Reseña> reseñas, List<DetalleProducto> dtProductos) {
		super();
		this.idProducto = idProducto;
		this.pNombre = pNombre;
		this.pDescripcion = pDescripcion;
		this.pPrecio = pPrecio;
		this.pStock = pStock;
		this.vendedor = vendedor;
		this.subcategoria = subcategoria;
		this.reseñas = reseñas;
		this.dtProductos = dtProductos;
	}
}
