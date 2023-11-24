package com.adriana.tienda.datos;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "MetodoPago")
public class MetodoPago {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idMetodoPago")
	private int idMetodoPago;

	@Column(name = "mpTipo", length = 45)
	private String mpTipo;

	@OneToMany(mappedBy = "metodoPago", cascade = CascadeType.ALL)
	private List<Transaccion> transacciones;

	public int getIdMetodoPago() {
		return idMetodoPago;
	}

	public void setIdMetodoPago(int idMetodoPago) {
		this.idMetodoPago = idMetodoPago;
	}

	public String getMpTipo() {
		return mpTipo;
	}

	public void setMpTipo(String mpTipo) {
		this.mpTipo = mpTipo;
	}

	public List<Transaccion> getTransacciones() {
		return transacciones;
	}

	public void setTransacciones(List<Transaccion> transacciones) {
		this.transacciones = transacciones;
	}

	public MetodoPago() {
		super();
	}

	public MetodoPago(int idMetodoPago, String mpTipo, List<Transaccion> transacciones) {
		super();
		this.idMetodoPago = idMetodoPago;
		this.mpTipo = mpTipo;
		this.transacciones = transacciones;
	}
}
