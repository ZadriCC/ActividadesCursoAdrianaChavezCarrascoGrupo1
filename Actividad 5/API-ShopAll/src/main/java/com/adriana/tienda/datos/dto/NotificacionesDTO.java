package com.adriana.tienda.datos.dto;

import java.util.Date;

import com.adriana.tienda.datos.Usuarios;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class NotificacionesDTO {

	private int idNotificacion;

	@NotEmpty(message = "El campo descripción no debe estar vacio")
	@Size(min = 10, max = 140)
	private String nDescripcion;

	@NotEmpty
	private Date nFecha;

	private Usuarios usuarios;

	public int getIdNotificacion() {
		return idNotificacion;
	}

	public void setIdNotificacion(int idNotificacion) {
		this.idNotificacion = idNotificacion;
	}

	public String getnDescripcion() {
		return nDescripcion;
	}

	public void setnDescripcion(String nDescripcion) {
		this.nDescripcion = nDescripcion;
	}

	public Date getnFecha() {
		return nFecha;
	}

	public void setnFecha(Date nFecha) {
		this.nFecha = nFecha;
	}

	public Usuarios getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}

	public NotificacionesDTO() {
		super();
	}
}
