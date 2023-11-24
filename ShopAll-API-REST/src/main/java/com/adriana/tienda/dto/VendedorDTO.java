package com.adriana.tienda.dto;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Validated
public class VendedorDTO {

	private int idVendedor;

	@NotNull
	@Size(min = 5, max = 45)
	private String vNombre;

	@NotNull
	@Size(min = 10, max = 140)
	private String vGiro;

	@NotNull
	@Size(min = 10, max = 1000)
	private String vDescripcion;

	@Size(max = 150)
	@Pattern(regexp = "^(https?://).*$")
	private String vSitioWeb;

	//private Usuario usuario;

	public int getIdVendedor() {
		return idVendedor;
	}

	public void setIdVendedor(int idVendedor) {
		this.idVendedor = idVendedor;
	}

	public String getvNombre() {
		return vNombre;
	}

	public void setvNombre(String vNombre) {
		this.vNombre = vNombre;
	}

	public String getvGiro() {
		return vGiro;
	}

	public void setvGiro(String vGiro) {
		this.vGiro = vGiro;
	}

	public String getvDescripcion() {
		return vDescripcion;
	}

	public void setvDescripcion(String vDescripcion) {
		this.vDescripcion = vDescripcion;
	}

	public String getvSitioWeb() {
		return vSitioWeb;
	}

	public void setvSitioWeb(String vSitioWeb) {
		this.vSitioWeb = vSitioWeb;
	}

	public VendedorDTO() {
		super();
	}
}
