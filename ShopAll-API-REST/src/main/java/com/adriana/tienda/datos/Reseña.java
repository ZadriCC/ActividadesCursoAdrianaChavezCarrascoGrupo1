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
@Table(name = "Reseñas")
public class Reseña {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idReseña")
	private int idReseña;

	@Column(name = "rPuntuacion")
	private int rPuntuacion;

	@Column(name = "rComentario", length = 140)
	private String rComentario;

	@Column(name = "rFecha")
	private LocalDate rFecha;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "idUsuario")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Usuario usuario;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "idProducto")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Producto producto;

	public int getIdReseña() {
		return idReseña;
	}

	public void setIdReseña(int idReseña) {
		this.idReseña = idReseña;
	}

	public int getrPuntuacion() {
		return rPuntuacion;
	}

	public void setrPuntuacion(int rPuntuacion) {
		this.rPuntuacion = rPuntuacion;
	}

	public String getrComentario() {
		return rComentario;
	}

	public void setrComentario(String rComentario) {
		this.rComentario = rComentario;
	}

	public LocalDate getrFecha() {
		return rFecha;
	}

	public void setrFecha(LocalDate rFecha) {
		this.rFecha = rFecha;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Reseña() {
		super();
	}

	public Reseña(int idReseña, int rPuntuacion, String rComentario, LocalDate rFecha, Usuario usuario,
			Producto producto) {
		super();
		this.idReseña = idReseña;
		this.rPuntuacion = rPuntuacion;
		this.rComentario = rComentario;
		this.rFecha = rFecha;
		this.usuario = usuario;
		this.producto = producto;
	}

}
