package com.adriana.tienda.datos;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "MetodoEnvio")
public class MetodoEnvio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	@Column(name = "idMetodoEnvio")
	private int idMetodoEnvio;

	@NotNull
	@Column(name = "meTipo", length = 45)
	private String meTipo;

	@OneToMany(mappedBy = "metodoEnvio", cascade = CascadeType.ALL)
	private Set<Transacciones> transacciones = new HashSet<>();

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

	public Set<Transacciones> getTransacciones() {
		return transacciones;
	}

	public void setTransacciones(Set<Transacciones> transacciones) {
		this.transacciones = transacciones;
		for (Transacciones transaccion : transacciones) {
			transaccion.setMetodoEnvio(this);
		}
	}

	public MetodoEnvio() {
		super();
	}

	public MetodoEnvio(@NotNull int idMetodoEnvio, @NotNull String meTipo) {
		super();
		this.idMetodoEnvio = idMetodoEnvio;
		this.meTipo = meTipo;
	}
}
