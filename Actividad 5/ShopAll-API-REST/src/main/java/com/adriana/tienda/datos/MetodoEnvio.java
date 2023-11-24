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
@Table(name = "MetodoEnvio")
public class MetodoEnvio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idMetodoEnvio")
	private int idMetodoEnvio;

	@Column(name = "meTipo", length = 45)
	private String meTipo;

	@Column(name = "mePrecio")
	private double mePrecio;

	@OneToMany(mappedBy = "metodoEnvio", cascade = CascadeType.ALL)
	private List<Transaccion> transacciones;

	public int getIdMetodoEnvio() {
		return idMetodoEnvio;
	}

	public void setIdMetodoEnvio(int idMetodoEnvio) {
		this.idMetodoEnvio = idMetodoEnvio;
	}

	public String getMeTipo() {
		return meTipo;
	}

	public void setMeTipo(String meTipo) {
		this.meTipo = meTipo;
	}

	public double getMePrecio() {
		return mePrecio;
	}

	public void setMePrecio(double mePrecio) {
		this.mePrecio = mePrecio;
	}

	public List<Transaccion> getTransacciones() {
		return transacciones;
	}

	public void setTransacciones(List<Transaccion> transacciones) {
		this.transacciones = transacciones;
	}

	public MetodoEnvio() {
		super();
	}

	public MetodoEnvio(int idMetodoEnvio, String meTipo, double mePrecio, List<Transaccion> transacciones) {
		super();
		this.idMetodoEnvio = idMetodoEnvio;
		this.meTipo = meTipo;
		this.mePrecio = mePrecio;
		this.transacciones = transacciones;
	}

}
