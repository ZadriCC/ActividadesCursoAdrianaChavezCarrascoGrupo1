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
@Table(name = "DetalleUsuario")
public class DetalleUsuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idDUsuario")
	private int idDUsuario;

	@NotNull
	@Column(name = "duDireccion", length = 140)
	private String duDireccion;

	@NotNull
	@Column(name = "duTelefono", length = 16)
	private String duTelefono;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "idUsuario")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Usuarios usuarios;

	public int getIdDUsuario() {
		return idDUsuario;
	}

	public void setIdDUsuario(int idDUsuario) {
		this.idDUsuario = idDUsuario;
	}

	public String getDuDireccion() {
		return duDireccion;
	}

	public void setDuDireccion(String duDireccion) {
		this.duDireccion = duDireccion;
	}

	public String getDuTelefono() {
		return duTelefono;
	}

	public void setDuTelefono(String duTelefono) {
		this.duTelefono = duTelefono;
	}

	public Usuarios getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}

	public DetalleUsuario() {
		super();
	}
  
	public DetalleUsuario(int idDUsuario, @NotNull String duDireccion, @NotNull String duTelefono,
			Usuarios usuarios) {

		super();
		this.idDUsuario = idDUsuario;
		this.duDireccion = duDireccion;
		this.duTelefono = duTelefono;
		this.usuarios = usuarios;
	}
}
