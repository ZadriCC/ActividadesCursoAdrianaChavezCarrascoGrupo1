package com.adriana.tienda.datos;

import java.util.Date;

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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Transacciones")
public class Transacciones {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	@Column(name = "idTransaccion")
	private int idTransaccion;

	@NotNull
	@Column(name = "tFecha")
	private Date tFecha;

	@NotNull
	@Column(name = "tDireccionEnvio", length = 140)
	private String tDireccionEnvio;

	@NotNull
	@Column(name = "tTotal")
	private double tTotal;

	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "idPedido")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Pedidos pedido;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "idMetodoPago")
	@JsonProperty(access = Access.WRITE_ONLY)
	private MetodoPago metodoPago;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "idMetodoEnvio")
	@JsonProperty(access = Access.WRITE_ONLY)
	private MetodoEnvio metodoEnvio;

	public int getIdTransaccion() {
		return idTransaccion;
	}

	public void setIdTransaccion(int idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

	public Date gettFecha() {
		return tFecha;
	}

	public void settFecha(Date tFecha) {
		this.tFecha = tFecha;
	}

	public String gettDireccionEnvio() {
		return tDireccionEnvio;
	}

	public void settDireccionEnvio(String tDireccionEnvio) {
		this.tDireccionEnvio = tDireccionEnvio;
	}

	public double gettTotal() {
		return tTotal;
	}

	public void settTotal(double tTotal) {
		this.tTotal = tTotal;
	}

	public Pedidos getPedido() {
		return pedido;
	}

	public void setPedido(Pedidos pedido) {
		this.pedido = pedido;
	}

	public MetodoPago getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(MetodoPago metodoPago) {
		this.metodoPago = metodoPago;
	}

	public MetodoEnvio getMetodoEnvio() {
		return metodoEnvio;
	}

	public void setMetodoEnvio(MetodoEnvio metodoEnvio) {
		this.metodoEnvio = metodoEnvio;
	}

	public Transacciones() {
		super();
	}

	public Transacciones(@NotNull int idTransaccion, @NotNull Date tFecha, @NotNull String tDireccionEnvio,
			@NotNull double tTotal, Pedidos pedido, MetodoPago metodoPago, MetodoEnvio metodoEnvio) {
		super();
		this.idTransaccion = idTransaccion;
		this.tFecha = tFecha;
		this.tDireccionEnvio = tDireccionEnvio;
		this.tTotal = tTotal;
		this.pedido = pedido;
		this.metodoPago = metodoPago;
		this.metodoEnvio = metodoEnvio;
	}
}
