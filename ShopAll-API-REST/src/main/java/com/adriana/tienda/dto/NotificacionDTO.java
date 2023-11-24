package com.adriana.tienda.dto;

import java.time.LocalDate;

import org.springframework.validation.annotation.Validated;

import com.adriana.tienda.datos.Usuario;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Validated
public class NotificacionDTO {

	private int idNotificacion;

	@NotNull
	@Size(min = 10, max = 140)
	private String nDescripcion;

	@NotNull
	private LocalDate nFecha;

	private Usuario usuario;

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

	public LocalDate getnFecha() {
		return nFecha;
	}

	public void setnFecha(LocalDate nFecha) {
		this.nFecha = nFecha;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public NotificacionDTO() {
		super();
	}
}
