package com.adriana.tienda.datos.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class DetalleUsuarioDTO {

	private int idDUsuario;

	@NotEmpty(message = "El campo dirección no debe estar vacio")
	@Size(min = 10, max = 140)
	private String duDireccion;

	@NotEmpty(message = "El campo teléfono no debe estar vacio")
	@Size(min = 10, max = 16)
	@Pattern(regexp = "\\d{10,16}", message = "El teléfono debe tener entre 10 y 16 dígitos")
	private String duTelefono;

	// private Usuarios usuarios;

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

//	public Usuarios getUsuarios() {
//		return usuarios;
//	}
//
//	public void setUsuarios(Usuarios usuarios) {
//		this.usuarios = usuarios;
//	}

	public DetalleUsuarioDTO() {
		super();
	}
}
