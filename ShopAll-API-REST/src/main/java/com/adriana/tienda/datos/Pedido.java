package com.adriana.tienda.datos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Pedidos")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idPedido")
	private int idPedido;

	@Column(name = "pdEstado", length = 10)
	private String pdEstado;

	@Column(name = "pdSubtotal")
	private double pdSubtotal;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "idUsuario")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Usuario usuario;

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	private List<DetallePedido> detallePedido;

	@OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL)
	private Transaccion transacciones;

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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<DetallePedido> getDetallePedido() {
		return detallePedido;
	}

	public void setDetallePedido(List<DetallePedido> detallePedido) {
		this.detallePedido = detallePedido;
	}

	public Transaccion getTransacciones() {
		return transacciones;
	}

	public void setTransacciones(Transaccion transacciones) {
		this.transacciones = transacciones;
	}

	public Pedido() {
		super();
	}

	public Pedido(int idPedido, String pdEstado, double pdSubtotal, Usuario usuario, List<DetallePedido> detallePedido,
			Transaccion transacciones) {
		super();
		this.idPedido = idPedido;
		this.pdEstado = pdEstado;
		this.pdSubtotal = pdSubtotal;
		this.usuario = usuario;
		this.detallePedido = detallePedido;
		this.transacciones = transacciones;
	}
}
