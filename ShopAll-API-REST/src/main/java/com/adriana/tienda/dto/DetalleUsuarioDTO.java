package com.adriana.tienda.dto;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Validated
public class DetalleUsuarioDTO {

	private int idDUsuario;

	@NotNull
	@Size(min = 10, max = 140)
	private String duDireccion;

	@NotNull
	@Size(min = 10, max = 16)
	@Pattern(regexp = "\\d{10,16}")
	private String duTelefono;

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

	public DetalleUsuarioDTO() {
		super();
	}
}
