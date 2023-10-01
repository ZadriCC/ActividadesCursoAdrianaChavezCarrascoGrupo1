package com.adriana.tienda.datos.dto;

import java.util.Set;

import com.adriana.tienda.datos.DetallePedido;
import com.adriana.tienda.datos.DetalleProducto;
import com.adriana.tienda.datos.PerfilVendedor;
import com.adriana.tienda.datos.Transacciones;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class ProductosDTO {

	private int idProducto;

	@NotEmpty(message = "El campo nombre no debe estar vacio")
	@Size(min = 5, max = 45)
	private String pNombre;

	@NotEmpty(message = "El campo descripción no debe estar vacio")
	@Size(min = 140, max = 1000)
	private String pDescripcion;

	@NotEmpty(message = "El campo precio no debe estar vacio")
	private double pPrecio;

	@NotEmpty(message = "El campo stock no debe estar vacio")
	private int pStock;

	private Set<Transacciones> transacciones;
	private Set<DetallePedido> detallePedido;
	private Set<PerfilVendedor> vendedor;
	private Set<DetalleProducto> dtProductos;

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

	public Set<Transacciones> getTransacciones() {
		return transacciones;
	}

	public void setTransacciones(Set<Transacciones> transacciones) {
		this.transacciones = transacciones;
	}

	public Set<DetallePedido> getDetallePedido() {
		return detallePedido;
	}

	public void setDetallePedido(Set<DetallePedido> detallePedido) {
		this.detallePedido = detallePedido;
	}

	public Set<PerfilVendedor> getVendedor() {
		return vendedor;
	}

	public void setVendedor(Set<PerfilVendedor> vendedor) {
		this.vendedor = vendedor;
	}

	public Set<DetalleProducto> getDtProductos() {
		return dtProductos;
	}

	public void setDtProductos(Set<DetalleProducto> dtProductos) {
		this.dtProductos = dtProductos;
	}

	public ProductosDTO() {
		super();
	}
}
