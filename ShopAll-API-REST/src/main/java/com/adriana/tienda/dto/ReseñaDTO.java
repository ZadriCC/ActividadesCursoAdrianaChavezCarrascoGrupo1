package com.adriana.tienda.dto;

import java.time.LocalDate;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Validated
public class ReseñaDTO {

	private int idReseña;

	@NotNull
	@Min(value = 1)
	@Max(value = 5)
	private int rPuntuacion;

	@Size(max = 140)
	private String rComentario;

	@NotNull
	private LocalDate rFecha;

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

	public ReseñaDTO() {
		super();
	}
}
