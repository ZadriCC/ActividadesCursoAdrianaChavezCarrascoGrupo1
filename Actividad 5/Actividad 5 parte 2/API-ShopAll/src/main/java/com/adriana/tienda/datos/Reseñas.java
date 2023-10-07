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
@Table(name = "Reseñas")
public class Reseñas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	@Column(name = "idReseña")
	private int idReseña;

	@NotNull
	@Column(name = "rPuntuacion")
	private int rPuntuacion;

	@NotNull
	@Column(name = "rComentario", length = 140)
	private String rComentario;

	@NotNull
	@Column(name = "rFecha")
	private Date rFecha;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "idUsuario")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Usuarios usuarios;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "idProducto")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Productos productos;

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

	public Date getrFecha() {
		return rFecha;
	}

	public void setrFecha(Date rFecha) {
		this.rFecha = rFecha;
	}

	public Usuarios getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}

	public Productos getProductos() {
		return productos;
	}

	public void setProductos(Productos productos) {
		this.productos = productos;
	}

	public Reseñas() {
		super();
	}

	public Reseñas(@NotNull int idReseña, @NotNull int rPuntuacion, @NotNull String rComentario, @NotNull Date rFecha,
			Usuarios usuarios, Productos productos) {
		super();
		this.idReseña = idReseña;
		this.rPuntuacion = rPuntuacion;
		this.rComentario = rComentario;
		this.rFecha = rFecha;
		this.usuarios = usuarios;
		this.productos = productos;
	}
}
