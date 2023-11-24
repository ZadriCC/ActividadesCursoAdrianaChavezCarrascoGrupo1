package com.adriana.tienda.dto;

import java.time.LocalDate;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Validated
public class TransaccionDTO {

	private int idTransaccion;

	private LocalDate tFecha;

	@Size(min = 5, max = 140)
	private String tNotasCliente;

	@NotNull
	@Size(max = 25)
	private String tNoSeguimiento;

	@NotNull
	@Size(min = 10, max = 140)
	private String tDireccionEnvio;

	@NotNull
	private double tTotal;

	public int getIdTransaccion() {
		return idTransaccion;
	}

	public void setIdTransaccion(int idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

	public LocalDate gettFecha() {
		return tFecha;
	}

	public void settFecha(LocalDate tFecha) {
		this.tFecha = tFecha;
	}

	public String gettNotasCliente() {
		return tNotasCliente;
	}

	public void settNotasCliente(String tNotasCliente) {
		this.tNotasCliente = tNotasCliente;
	}

	public String gettNoSeguimiento() {
		return tNoSeguimiento;
	}

	public void settNoSeguimiento(String tNoSeguimiento) {
		this.tNoSeguimiento = tNoSeguimiento;
	}

	public String gettDireccionEnvio() {
		return tDireccionEnvio;
	}

	public void settDireccionEnvio(String tDireccionEnvio) {
		this.tDireccionEnvio = tDireccionEnvio;
	}

	public double gettTotal() {
		return tTotal;
	}

	public void settTotal(double tTotal) {
		this.tTotal = tTotal;
	}

	public TransaccionDTO() {
		super();
	}
}
