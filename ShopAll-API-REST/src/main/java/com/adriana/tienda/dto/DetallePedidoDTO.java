package com.adriana.tienda.dto;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotNull;

@Validated
public class DetallePedidoDTO {

	private int idDPedido;

	@NotNull
	private int dtpCantidad;

	private double dtpTotal;

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

	public DetallePedidoDTO() {
		super();
	}
}
