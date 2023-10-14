package com.adriana.tienda.datos;

import java.util.HashSet;
import java.util.Set;

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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Productos")
public class Productos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	@Column(name = "idProducto")
	private int idProducto;

	@NotNull
	@Column(name = "pNombre", length = 45)
	private String pNombre;

	@NotNull
	@Column(name = "pDescripcion", length = 1000)
	private String pDescripcion;

	@NotNull
	@Column(name = "pPrecio")
	private double pPrecio;

	@NotNull
	@Column(name = "pStock")
	private int pStock;

	@OneToMany(mappedBy = "productos", cascade = CascadeType.ALL)
	private Set<Reseñas> reseñas = new HashSet<>();

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "PerfilVendedor_Productos", joinColumns = @JoinColumn(name = "idProducto", referencedColumnName = "idProducto"), inverseJoinColumns = @JoinColumn(name = "idVendedor", referencedColumnName = "idVendedor"))
	private Set<PerfilVendedor> vendedor = new HashSet<>();

	@OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
	private Set<DetallePedido> detallePedido = new HashSet<>();

	@OneToMany(mappedBy = "productos", cascade = CascadeType.ALL)
	private Set<DetalleProducto> dtProductos = new HashSet<>();

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "idSubcategoria")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Subcategoria subcategoria;

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

	public Set<Reseñas> getReseñas() {
		return reseñas;
	}

	public void setReseñas(Set<Reseñas> reseñas) {
		this.reseñas = reseñas;
	}

	public Set<PerfilVendedor> getVendedor() {
		return vendedor;
	}

	public void setVendedor(Set<PerfilVendedor> vendedor) {
		this.vendedor = vendedor;
	}

	public Set<DetallePedido> getDetallePedido() {
		return detallePedido;
	}

	public void setDetallePedido(Set<DetallePedido> detallePedido) {
		this.detallePedido = detallePedido;
	}

	public Set<DetalleProducto> getDtProductos() {
		return dtProductos;
	}

	public void setDtProductos(Set<DetalleProducto> dtProductos) {
		this.dtProductos = dtProductos;
	}

	public Productos() {
		super();
	}

	public Subcategoria getSubcategoria() {
		return subcategoria;
	}

	public void setSubcategoria(Subcategoria subcategoria) {
		this.subcategoria = subcategoria;
	}

	public Productos(@NotNull int idProducto, @NotNull String pNombre, @NotNull String pDescripcion,
			@NotNull double pPrecio, @NotNull int pStock, Set<Reseñas> reseñas, Set<PerfilVendedor> vendedor,
			Set<DetallePedido> detallePedido, Set<DetalleProducto> dtProductos, Subcategoria subcategoria) {
		super();
		this.idProducto = idProducto;
		this.pNombre = pNombre;
		this.pDescripcion = pDescripcion;
		this.pPrecio = pPrecio;
		this.pStock = pStock;
		this.reseñas = reseñas;
		this.vendedor = vendedor;
		this.detallePedido = detallePedido;
		this.dtProductos = dtProductos;
		this.subcategoria = subcategoria;
	}
}
