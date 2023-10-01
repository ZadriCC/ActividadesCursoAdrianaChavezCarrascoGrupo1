package com.adriana.tienda.datos.dto;

import java.util.Set;

import com.adriana.tienda.datos.DetallePedido;
import com.adriana.tienda.datos.PerfilVendedor;
import com.adriana.tienda.datos.Transacciones;
import com.adriana.tienda.datos.Usuarios;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class PedidosDTO {

	private int idPedido;

	@NotEmpty(message = "El campo estado no debe estar vacio")
	@Pattern(regexp = "^(pendiente|procesado|enviado$", message = "El estado debe ser pendiente, procesado o enviado")
	private String pdEstado;

	@Size(min = 5, max = 140)
	private String pdNotasCliente;

	@Size(max = 25)
	private String pdNoSeguimiento;

	private double pdSubtotal;

	private double pdTotal;

	private Usuarios usuarios;

	private PerfilVendedor vendedor;

	private Transacciones transacciones;

	private Set<DetallePedido> detallePedido;

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

	public String getPdNotasCliente() {
		return pdNotasCliente;
	}

	public void setPdNotasCliente(String pdNotasCliente) {
		this.pdNotasCliente = pdNotasCliente;
	}

	public String getPdNoSeguimiento() {
		return pdNoSeguimiento;
	}

	public void setPdNoSeguimiento(String pdNoSeguimiento) {
		this.pdNoSeguimiento = pdNoSeguimiento;
	}

	public double getPdSubtotal() {
		return pdSubtotal;
	}

	public void setPdSubtotal(double pdSubtotal) {
		this.pdSubtotal = pdSubtotal;
	}

	public double getPdTotal() {
		return pdTotal;
	}

	public void setPdTotal(double pdTotal) {
		this.pdTotal = pdTotal;
	}

	public Usuarios getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}

	public PerfilVendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(PerfilVendedor vendedor) {
		this.vendedor = vendedor;
	}

	public Transacciones getTransacciones() {
		return transacciones;
	}

	public void setTransacciones(Transacciones transacciones) {
		this.transacciones = transacciones;
	}

	public Set<DetallePedido> getDetallePedido() {
		return detallePedido;
	}

	public void setDetallePedido(Set<DetallePedido> detallePedido) {
		this.detallePedido = detallePedido;
	}

	public PedidosDTO() {
		super();
	}
}
