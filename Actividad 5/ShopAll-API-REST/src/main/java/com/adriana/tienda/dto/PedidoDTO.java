package com.adriana.tienda.dto;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Validated
public class PedidoDTO {

	private int idPedido;

	@NotNull
	@Pattern(regexp = "^(pendiente|procesando|enviado|cancelado)$")
	private String pdEstado;

	@NotNull
	private double pdSubtotal;

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public String getPdEstado() {
		return pdEstado;
	}

	public void setPdEstado(String pdEstado) {
		this.pdEstado = pdEstado;
	}

	public double getPdSubtotal() {
		return pdSubtotal;
	}

	public void setPdSubtotal(double pdSubtotal) {
		this.pdSubtotal = pdSubtotal;
	}

	public PedidoDTO() {
		super();
	}
}
