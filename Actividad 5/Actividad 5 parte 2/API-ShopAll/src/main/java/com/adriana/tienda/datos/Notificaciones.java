package com.adriana.tienda.datos;

import java.util.Date;

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
@Table(name = "Notificaciones")
public class Notificaciones {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	@Column(name = "idNotificacion")
	private int idNotificacion;

	@NotNull
	@Column(name = "nDescripcion", length = 140)
	private String nDescripcion;

	@NotNull
	@Column(name = "nFecha")
	private Date nFecha;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "idUsuario")
	@JsonProperty(access = Access.WRITE_ONLY)
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

	public Notificaciones() {
		super();
	}

	public Notificaciones(@NotNull int idNotificacion, @NotNull String nDescripcion, @NotNull Date nFecha,
			Usuarios usuarios) {
		super();
		this.idNotificacion = idNotificacion;
		this.nDescripcion = nDescripcion;
		this.nFecha = nFecha;
		this.usuarios = usuarios;
	}
}
