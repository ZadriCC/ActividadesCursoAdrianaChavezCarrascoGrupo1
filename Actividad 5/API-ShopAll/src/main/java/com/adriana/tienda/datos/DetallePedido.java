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
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "DetallePedido")
public class DetallePedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	@Column(name = "idDPedido")
	private int idDPedido;
	
	@NotNull
	@Column(name = "dtpCantidad")
	private int dtpCantidad;
	
	@NotNull
	@Column(name = "dtpTotal")
	private double dtpTotal;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "idPedido")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Pedidos pedido;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "idVendedor")
	@JsonProperty(access = Access.WRITE_ONLY)
	private PerfilVendedor vendedor;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "idProducto")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Productos producto;

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

	public DetallePedido() {
		super();
	}

	public DetallePedido(@NotNull int idDPedido, @NotNull int dtpCantidad, @NotNull double dtpTotal, Pedidos pedido,
			Productos producto) {
		super();
		this.idDPedido = idDPedido;
		this.dtpCantidad = dtpCantidad;
		this.dtpTotal = dtpTotal;
		this.pedido = pedido;
		this.producto = producto;
	}
}
