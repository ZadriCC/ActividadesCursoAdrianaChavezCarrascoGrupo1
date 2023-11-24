package com.adriana.tienda.datos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "DetallePedido")
public class DetallePedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idDPedido")
	private int idDPedido;

	@Column(name = "dtpCantidad")
	private int dtpCantidad;

	@Column(name = "dtpTotal")
	private double dtpTotal;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "idPedido")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Pedido pedido;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "idVendedor")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Vendedor vendedor;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "idProducto")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Producto producto;

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

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public DetallePedido() {
		super();
	}

	public DetallePedido(int idDPedido, int dtpCantidad, double dtpTotal, Pedido pedido, Vendedor vendedor,
			Producto producto) {
		super();
		this.idDPedido = idDPedido;
		this.dtpCantidad = dtpCantidad;
		this.dtpTotal = dtpTotal;
		this.pedido = pedido;
		this.vendedor = vendedor;
		this.producto = producto;
	}
}
