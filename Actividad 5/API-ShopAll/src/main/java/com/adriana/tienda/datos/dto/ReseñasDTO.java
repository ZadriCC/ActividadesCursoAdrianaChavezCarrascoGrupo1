package com.adriana.tienda.datos.dto;

import java.util.Date;

import com.adriana.tienda.datos.Productos;
import com.adriana.tienda.datos.Usuarios;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class ReseñasDTO {

	private int idReseña;

	@NotEmpty(message = "El campo puntuación no debe estar vacio")
	@Min(value = 1, message = "La puntuación mínima permitida es 1")
	@Max(value = 5, message = "La puntuación máxima permitida es 5")
	private int rPuntuacion;

	@Size(max = 140)
	private String rComentario;

	@NotEmpty
	private Date rFecha;

	private Usuarios usuarios;
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

	public ReseñasDTO() {
		super();
	}
}
