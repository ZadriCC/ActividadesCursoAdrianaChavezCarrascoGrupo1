package com.adriana.tienda.datos;

import java.util.HashSet;
import java.util.Set;

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
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Pedidos")
public class Pedidos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	@Column(name = "idPedido")
	private int idPedido;

	@NotNull
	@Column(name = "pdEstado", length = 10)
	private String pdEstado;

	@Column(name = "pdNotasCliente", length = 140)
	private String pdNotasCliente;

	@Column(name = "pdNoSeguimiento", length = 25)
	private String pdNoSeguimiento;

	@NotNull
	@Column(name = "pdSubtotal")
	private double pdSubtotal;

	@NotNull
	@Column(name = "pdTotal")
	private double pdTotal;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "idUsuario")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Usuarios usuarios;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "idVendedor")
	@JsonProperty(access = Access.WRITE_ONLY)
	private PerfilVendedor perfilVendedor;

	@OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL)
	private Transacciones transacciones;

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	private Set<DetallePedido> detallePedido = new HashSet<>();

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
		return perfilVendedor;
	}

	public void setVendedor(PerfilVendedor vendedor) {
		this.perfilVendedor = vendedor;
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

	public Pedidos() {
		super();
	}

	public Pedidos(@NotNull int idPedido, @NotNull String pdEstado, String pdNotasCliente, String pdNoSeguimiento,
			@NotNull double pdSubtotal, @NotNull double pdTotal, Usuarios usuarios) {
		super();
		this.idPedido = idPedido;
		this.pdEstado = pdEstado;
		this.pdNotasCliente = pdNotasCliente;
		this.pdNoSeguimiento = pdNoSeguimiento;
		this.pdSubtotal = pdSubtotal;
		this.pdTotal = pdTotal;
		this.usuarios = usuarios;
		this.perfilVendedor = vendedor;
	}
}
