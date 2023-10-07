package com.adriana.tienda.datos.dto;

import java.util.Date;
import java.util.Set;

import com.adriana.tienda.datos.DetalleUsuario;
import com.adriana.tienda.datos.Notificaciones;
import com.adriana.tienda.datos.Pedidos;
import com.adriana.tienda.datos.PerfilVendedor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UsuariosDTO {

	private int idUsuario;

	@NotEmpty(message = "El campo nombre no debe estar vacio")
	@Size(min = 5, max = 45)
	private String uNombre;

	@NotEmpty(message = "El campo Apellidos no debe estar vacio")
	@Size(min = 5, max = 45)
	private String uApellidos;

	@NotEmpty(message = "El campo email no debe estar vacio")
	@Size(min = 5, max = 45)
	@Email
	private String uEmail;

	@NotEmpty(message = "El campo contraseña no debe estar vacio")
	@Size(min = 7, max = 8, message = "La contraseña debe tener 8 digitos")
	private String uPassword;

	@NotEmpty(message = "El campo rol no debe estar vacio")
	@Pattern(regexp = "^(comprador|vendedor$", message = "El rol debe ser comprador o vendedor")
	private String uRol;

	@NotEmpty
	private Date uFechaRegistro;

	private Set<DetalleUsuario> datos;
	private Set<PerfilVendedor> datosVendedor;
	private Set<Notificaciones> notificaciones;
	private Set<Pedidos> pedidos;

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

	public Date getuFechaRegistro() {
		return uFechaRegistro;
	}

	public void setuFechaRegistro(Date uFechaRegistro) {
		this.uFechaRegistro = uFechaRegistro;
	}

	public Set<DetalleUsuario> getDatos() {
		return datos;
	}

	public void setDatos(Set<DetalleUsuario> datos) {
		this.datos = datos;
	}

	public Set<PerfilVendedor> getDatosVendedor() {
		return datosVendedor;
	}

	public void setDatosVendedor(Set<PerfilVendedor> datosVendedor) {
		this.datosVendedor = datosVendedor;
	}

	public Set<Notificaciones> getNotificaciones() {
		return notificaciones;
	}

	public void setNotificaciones(Set<Notificaciones> notificaciones) {
		this.notificaciones = notificaciones;
	}

	public Set<Pedidos> getPedidos() {
		return pedidos;
	}

	public void setPedidos(Set<Pedidos> pedidos) {
		this.pedidos = pedidos;
	}

	public UsuariosDTO() {
		super();
	}
}
