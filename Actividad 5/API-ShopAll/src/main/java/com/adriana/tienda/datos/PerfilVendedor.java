package com.adriana.tienda.datos;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "PerfilVendedor")
public class PerfilVendedor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	@Column(name = "idVendedor")
	private int idVendedor;

	@NotNull
	@Column(name = "vNombre", length = 45)
	private String vNombre;

	@NotNull
	@Column(name = "vGiro", length = 140)
	private String vGiro;

	@NotNull
	@Column(name = "vDescripcion", length = 1000)
	private String vDescripcion;

	@Column(name = "vSitioWeb", length = 150)
	private String vSitioWeb;

	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "idUsuario")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Usuarios vendedor;

	@OneToMany(mappedBy = "perfilVendedor", cascade = CascadeType.ALL)
	private Set<Pedidos> pedidos = new HashSet<>();
	
	@OneToMany(mappedBy = "vendedor", cascade = CascadeType.ALL)
	private Set<DetallePedido> dtpedidos = new HashSet<>();

	@ManyToMany(cascade = CascadeType.ALL)
	@JsonBackReference
	@JoinTable(name = "PerfilVendedor_Productos", joinColumns = @JoinColumn(name = "idVendedor", referencedColumnName = "idVendedor"), inverseJoinColumns = @JoinColumn(name = "idProducto", referencedColumnName = "idProducto"))
	private Set<Productos> producto = new HashSet<>();

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

	public Usuarios getVendedor() {
		return vendedor;
	}

	public void setVendedor(Usuarios vendedor) {
		this.vendedor = vendedor;
	}

	public Set<Pedidos> getPedidos() {
		return pedidos;
	}

	public void setPedidos(Set<Pedidos> pedidos) {
		this.pedidos = pedidos;
		for (Pedidos pedido : pedidos) {
			pedido.setVendedor(this);
		}
	}

	public Set<Productos> getProducto() {
		return producto;
	}

	public void setProducto(Set<Productos> producto) {
		this.producto = producto;
	}

	public Set<DetallePedido> getDtpedidos() {
		return dtpedidos;
	}

	public void setDtpedidos(Set<DetallePedido> dtpedidos) {
		this.dtpedidos = dtpedidos;
	}

	public PerfilVendedor() {
		super();
	}

	public PerfilVendedor(@NotNull int idVendedor, @NotNull String vNombre, @NotNull String vGiro,
			@NotNull String vDescripcion, String vSitioWeb, Usuarios vendedor) {
		super();
		this.idVendedor = idVendedor;
		this.vNombre = vNombre;
		this.vGiro = vGiro;
		this.vDescripcion = vDescripcion;
		this.vSitioWeb = vSitioWeb;
		this.vendedor = vendedor;
	}
}
