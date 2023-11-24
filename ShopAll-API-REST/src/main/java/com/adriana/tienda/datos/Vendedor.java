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
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Vendedores")
public class Vendedor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idVendedor")
	private int idVendedor;
	
	@Column(name = "vNombre", length = 45, unique = true)
	private String vNombre;

	@Column(name = "vGiro", length = 140)
	private String vGiro;

	@Column(name = "vDescripcion", length = 1000)
	private String vDescripcion;

	@Column(name = "vSitioWeb", length = 150)
	private String vSitioWeb;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "idUsuario")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Usuario usuario;

	@OneToMany(mappedBy = "vendedor", cascade = CascadeType.ALL)
	private List<Producto> productos;

	public int getIdVendedor() {
		return idVendedor;
	}

	public void setIdVendedor(int idVendedor) {
		this.idVendedor = idVendedor;
	}

	public String getvNombre() {
		return vNombre;
	}

	public void setvNombre(String vNombre) {
		this.vNombre = vNombre;
	}

	public String getvGiro() {
		return vGiro;
	}

	public void setvGiro(String vGiro) {
		this.vGiro = vGiro;
	}

	public String getvDescripcion() {
		return vDescripcion;
	}

	public void setvDescripcion(String vDescripcion) {
		this.vDescripcion = vDescripcion;
	}

	public String getvSitioWeb() {
		return vSitioWeb;
	}

	public void setvSitioWeb(String vSitioWeb) {
		this.vSitioWeb = vSitioWeb;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public Vendedor() {
		super();
	}

	public Vendedor(int idVendedor, @NotNull String vNombre, @NotNull String vGiro, @NotNull String vDescripcion,
			String vSitioWeb, Usuario usuario, List<Producto> productos) {
		super();
		this.idVendedor = idVendedor;
		this.vNombre = vNombre;
		this.vGiro = vGiro;
		this.vDescripcion = vDescripcion;
		this.vSitioWeb = vSitioWeb;
		this.usuario = usuario;
		this.productos = productos;
	}
}
