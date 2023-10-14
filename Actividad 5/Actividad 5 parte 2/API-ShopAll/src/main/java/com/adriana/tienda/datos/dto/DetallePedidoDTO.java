package com.adriana.tienda.datos.dto;

import com.adriana.tienda.datos.Pedidos;
import com.adriana.tienda.datos.PerfilVendedor;
import com.adriana.tienda.datos.Productos;

import jakarta.validation.constraints.NotEmpty;

public class DetallePedidoDTO {

	private int idDPedido;

	@NotEmpty
	private int dtpCantidad;

	@NotEmpty
	private double dtpTotal;

	private Pedidos pedido;

	private Productos producto;

	private PerfilVendedor vendedor;

	public int getIdDPedido() {
		return idDPedido;
	}

	public void setIdDPedido(int idDPedido) {
		this.idDPedido = idDPedido;
	}

	public int getDtpCantidad() {
		return dtpCantidad;
	}

	public void setDtpCantidad(int dtpCantidad) {
		this.dtpCantidad = dtpCantidad;
	}

	public double getDtpTotal() {
		return dtpTotal;
	}

	public void setDtpTotal(double dtpTotal) {
		this.dtpTotal = dtpTotal;
	}

	public Pedidos getPedido() {
		return pedido;
	}

	public void setPedido(Pedidos pedido) {
		this.pedido = pedido;
	}

	public Productos getProducto() {
		return producto;
	}

	public void setProducto(Productos producto) {
		this.producto = producto;
	}

	public PerfilVendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(PerfilVendedor vendedor) {
		this.vendedor = vendedor;
	}

	public DetallePedidoDTO() {
		super();
	}
}
