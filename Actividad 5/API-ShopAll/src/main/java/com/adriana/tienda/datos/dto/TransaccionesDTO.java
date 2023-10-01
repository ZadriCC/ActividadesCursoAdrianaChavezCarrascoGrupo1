package com.adriana.tienda.datos.dto;

import java.util.Date;

import com.adriana.tienda.datos.MetodoEnvio;
import com.adriana.tienda.datos.MetodoPago;
import com.adriana.tienda.datos.Pedidos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class TransaccionesDTO {

	private int idTransaccion;

	@NotEmpty
	private Date tFecha;

	@NotEmpty(message = "El campo dirección no debe estar vacio")
	@Size(min = 10, max = 140)
	private String tDireccionEnvio;

	@NotEmpty
	private double tTotal;

	private Pedidos pedido;
	private MetodoPago metodoPago;
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

	public TransaccionesDTO() {
		super();
	}
}
