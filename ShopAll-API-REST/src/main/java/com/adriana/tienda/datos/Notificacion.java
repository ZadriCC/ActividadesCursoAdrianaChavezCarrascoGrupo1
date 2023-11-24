package com.adriana.tienda.datos;

import java.time.LocalDate;

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

@Entity
@Table(name = "Notificaciones")
public class Notificacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idNotificacion")
	private int idNotificacion;

	@Column(name = "nDescripcion", nullable = false, length = 140)
	private String nDescripcion;

	@Column(name = "nFecha", nullable = false)
	private LocalDate nFecha;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "idUsuario")
	@JsonProperty(access = Access.WRITE_ONLY)
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

	public Notificacion() {
		super();
	}

	public Notificacion(int idNotificacion, String nDescripcion, LocalDate nFecha, Usuario usuario) {
		super();
		this.idNotificacion = idNotificacion;
		this.nDescripcion = nDescripcion;
		this.nFecha = nFecha;
		this.usuario = usuario;
	}
}
