package com.adriana.tienda.datos;

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
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "DetalleProducto")
public class DetalleProducto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	@Column(name = "idDProducto")
	private int idDProducto;

	@NotNull
	@Column(name = "dpAtributo", length = 45)
	private String dpAtributo;

	@NotNull
	@Column(name = "dpValor", length = 45)
	private String dpValor;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "idProducto")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Producto producto;

	public int getIdDProducto() {
		return idDProducto;
	}

	public void setIdDProducto(int idDProducto) {
		this.idDProducto = idDProducto;
	}

	public String getDpAtributo() {
		return dpAtributo;
	}

	public void setDpAtributo(String dpAtributo) {
		this.dpAtributo = dpAtributo;
	}

	public String getDpValor() {
		return dpValor;
	}

	public void setDpValor(String dpValor) {
		this.dpValor = dpValor;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public DetalleProducto() {
		super();
	}

	public DetalleProducto(@NotNull int idDProducto, @NotNull String dpAtributo, @NotNull String dpValor,
			Producto producto) {
		super();
		this.idDProducto = idDProducto;
		this.dpAtributo = dpAtributo;
		this.dpValor = dpValor;
		this.producto = producto;
	}
}
