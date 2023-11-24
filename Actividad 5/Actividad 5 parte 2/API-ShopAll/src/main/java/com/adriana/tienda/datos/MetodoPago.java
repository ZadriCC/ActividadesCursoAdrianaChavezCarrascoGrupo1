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
@Table(name = "MetodoPago")
public class MetodoPago {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	@Column(name = "idMetodoPago")
	private int idMetodoPago;

	@NotNull
	@Column(name = "mpTipo", length = 45)
	private String mpTipo;

	@OneToMany(mappedBy = "metodoPago", cascade = CascadeType.ALL)
	private Set<Transacciones> transacciones = new HashSet<>();

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

	public Set<Transacciones> getTransacciones() {
		return transacciones;
	}

	public void setTransacciones(Set<Transacciones> transacciones) {
		this.transacciones = transacciones;
	}

	public MetodoPago() {
		super();
	}

	public MetodoPago(@NotNull int idMetodoPago, @NotNull String mpTipo) {
		super();
		this.idMetodoPago = idMetodoPago;
		this.mpTipo = mpTipo;
	}
}
