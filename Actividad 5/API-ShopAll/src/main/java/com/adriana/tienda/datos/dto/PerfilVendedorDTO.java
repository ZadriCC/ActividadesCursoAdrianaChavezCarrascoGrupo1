package com.adriana.tienda.datos.dto;

import java.util.Set;

import org.hibernate.validator.constraints.URL;

import com.adriana.tienda.datos.DetallePedido;
import com.adriana.tienda.datos.Productos;
import com.adriana.tienda.datos.Usuarios;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.adriana.tienda.datos.DetallePedido;
import com.adriana.tienda.datos.Pedidos;
import com.adriana.tienda.datos.Productos;
import com.adriana.tienda.datos.Usuarios;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@JsonIgnoreProperties({ "vendedor", "producto", "dtpedidos" })
public class PerfilVendedorDTO {

	private int idVendedor;

	@NotEmpty(message = "El campo nombre no debe estar vacio")
	@Size(min = 5, max = 45)
	private String vNombre;

	@NotEmpty(message = "El campo giro no debe estar vacio")
	@Size(min = 10, max = 140)
	private String vGiro;

	@NotEmpty(message = "El campo descripción no debe estar vacio")
	@Size(min = 140, max = 1000)
	private String vDescripcion;

	@Size(max = 150)
	private String vSitioWeb;

	private Usuarios usuarios;

	private Set<Pedidos> pedidos;
	private Set<Productos> producto;
	private Set<DetallePedido> dtpedidos;

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

	public Usuarios getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}

	public Set<Pedidos> getPedidos() {
		return pedidos;
	}

	public void setPedidos(Set<Pedidos> pedidos) {
		this.pedidos = pedidos;
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

	public PerfilVendedorDTO() {
		super();
	}
}
