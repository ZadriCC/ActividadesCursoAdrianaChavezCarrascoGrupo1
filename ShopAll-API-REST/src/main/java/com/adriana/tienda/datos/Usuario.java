package com.adriana.tienda.datos;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idUsuario")
	private int idUsuario;

	@Column(name = "uNombre", nullable = false, length = 45)
	private String uNombre;

	@Column(name = "uApellidos", nullable = false, length = 45)
	private String uApellidos;

	@Column(name = "uEmail", nullable = false, length = 45, unique = true)
	private String uEmail;

	@Column(name = "uPassword", nullable = false, length = 255)
	private String uPassword;

	@Column(name = "uRol", nullable = false, length = 9)
	private String uRol;

	@Column(name = "uFecha", nullable = false)
	private LocalDate uFecha;

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	private List<DetalleUsuario> dtUsuarios;

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	private List<Notificacion> notificaciones;

	@OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
	private Vendedor vendedor;

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

	public List<DetalleUsuario> getDtUsuarios() {
		return dtUsuarios;
	}

	public void setDtUsuarios(List<DetalleUsuario> dtUsuarios) {
		this.dtUsuarios = dtUsuarios;
	}

	public List<Notificacion> getNotificaciones() {
		return notificaciones;
	}

	public void setNotificaciones(List<Notificacion> notificaciones) {
		this.notificaciones = notificaciones;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public Usuario() {
		super();
	}

	public Usuario(int idUsuario, String uNombre, String uApellidos, String uEmail, String uPassword, String uRol,
			LocalDate uFecha, List<DetalleUsuario> dtUsuarios, List<Notificacion> notificaciones, Vendedor vendedor) {
		super();
		this.idUsuario = idUsuario;
		this.uNombre = uNombre;
		this.uApellidos = uApellidos;
		this.uEmail = uEmail;
		this.uPassword = uPassword;
		this.uRol = uRol;
		this.uFecha = uFecha;
		this.dtUsuarios = dtUsuarios;
		this.notificaciones = notificaciones;
		this.vendedor = vendedor;
	}
}
