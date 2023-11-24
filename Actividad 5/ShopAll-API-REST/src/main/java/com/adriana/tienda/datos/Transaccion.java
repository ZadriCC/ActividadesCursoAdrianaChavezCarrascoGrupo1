package com.adriana.tienda.datos;

import java.time.LocalDate;

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

@Entity
@Table(name = "Transacciones")
public class Transaccion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idTransaccion")
	private int idTransaccion;

	@Column(name = "tFecha")
	private LocalDate tFecha;

	@Column(name = "tNotasCliente", length = 140)
	private String tNotasCliente;

	@Column(name = "tNoSeguimiento", length = 25)
	private String tNoSeguimiento;

	@Column(name = "tDireccionEnvio", length = 140)
	private String tDireccionEnvio;

	@Column(name = "tTotal")
	private double tTotal;

	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "idPedido")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Pedido pedido;

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

	public LocalDate gettFecha() {
		return tFecha;
	}

	public void settFecha(LocalDate tFecha) {
		this.tFecha = tFecha;
	}

	public String gettNotasCliente() {
		return tNotasCliente;
	}

	public void settNotasCliente(String tNotasCliente) {
		this.tNotasCliente = tNotasCliente;
	}

	public String gettNoSeguimiento() {
		return tNoSeguimiento;
	}

	public void settNoSeguimiento(String tNoSeguimiento) {
		this.tNoSeguimiento = tNoSeguimiento;
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

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
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

	public Transaccion() {
		super();
	}

	public Transaccion(int idTransaccion, LocalDate tFecha, String tNotasCliente, String tNoSeguimiento,
			String tDireccionEnvio, double tTotal, Pedido pedido, MetodoPago metodoPago, MetodoEnvio metodoEnvio) {
		super();
		this.idTransaccion = idTransaccion;
		this.tFecha = tFecha;
		this.tNotasCliente = tNotasCliente;
		this.tNoSeguimiento = tNoSeguimiento;
		this.tDireccionEnvio = tDireccionEnvio;
		this.tTotal = tTotal;
		this.pedido = pedido;
		this.metodoPago = metodoPago;
		this.metodoEnvio = metodoEnvio;
	}
}
