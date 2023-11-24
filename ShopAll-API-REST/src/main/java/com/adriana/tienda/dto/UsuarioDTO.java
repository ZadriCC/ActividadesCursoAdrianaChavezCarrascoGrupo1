package com.adriana.tienda.dto;

import java.time.LocalDate;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Validated
public class UsuarioDTO {

	private int idUsuario;

	@NotNull
	@Size(min = 4, max = 45)
	private String uNombre;

	@NotNull
	@Size(min = 5, max = 45)
	private String uApellidos;

	@NotNull
	@Size(min = 5, max = 45)
	@Email
	private String uEmail;

	@NotNull
	@Size(min = 8, max = 255)
	private String uPassword;

	@NotNull
	@Pattern(regexp = "^(comprador|vendedor|Admin)$")
	private String uRol;

	@NotNull
	private LocalDate uFecha;

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getuNombre() {
		return uNombre;
	}

	public void setuNombre(String uNombre) {
		this.uNombre = uNombre;
	}

	public String getuApellidos() {
		return uApellidos;
	}

	public void setuApellidos(String uApellidos) {
		this.uApellidos = uApellidos;
	}

	public String getuEmail() {
		return uEmail;
	}

	public void setuEmail(String uEmail) {
		this.uEmail = uEmail;
	}

	public String getuPassword() {
		return uPassword;
	}

	public void setuPassword(String uPassword) {
		this.uPassword = uPassword;
	}

	public String getuRol() {
		return uRol;
	}

	public void setuRol(String uRol) {
		this.uRol = uRol;
	}

	public LocalDate getuFecha() {
		return uFecha;
	}

	public void setuFecha(LocalDate uFecha) {
		this.uFecha = uFecha;
	}

	public UsuarioDTO() {
		super();
	}
}
