package com.adriana.tienda.datos;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Usuarios")
public class Usuarios {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	@Column(name = "idUsuario")
	private int idUsuario;

	@NotNull
	@Column(name = "uNombre", length = 45)
	private String uNombre;

	@NotNull
	@Column(name = "uApellidos", length = 45)
	private String uApellidos;

	@NotNull
	@Column(name = "uEmail", length = 45, unique = true)
	private String uEmail;

	@NotNull
	@Column(name = "uPassword", length = 8)
	private String uPassword;

	@NotNull
	@Column(name = "uRol", length = 9)
	private String uRol;

	@NotNull
	@Column(name = "uFechaRegistro")
	private Date uFechaRegistro;

	@OneToMany(mappedBy = "usuarios", cascade = CascadeType.ALL)
	private Set<DetalleUsuario> datos = new HashSet<>();

	@OneToOne(mappedBy = "vendedor", cascade = CascadeType.ALL)
	private PerfilVendedor datosVendedor;

	@OneToMany(mappedBy = "usuarios", cascade = CascadeType.ALL)
	private Set<Notificaciones> notificaciones = new HashSet<>();

	@OneToMany(mappedBy = "usuarios", cascade = CascadeType.ALL)
	private Set<Pedidos> pedidos = new HashSet<>();

	@OneToMany(mappedBy = "usuarios", cascade = CascadeType.ALL)
	private Set<Reseñas> reseñas = new HashSet<>();

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

	public PerfilVendedor getDatosVendedor() {
		return datosVendedor;
	}

	public void setDatosVendedor(PerfilVendedor datosVendedor) {
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

	public Set<Reseñas> getReseñas() {
		return reseñas;
	}

	public void setReseñas(Set<Reseñas> reseñas) {
		this.reseñas = reseñas;
	}

	public Usuarios() {
		super();
	}

	public Usuarios(@NotNull int idUsuario, @NotNull String uNombre, @NotNull String uApellidos, @NotNull String uEmail,
			@NotNull String uPassword, @NotNull String uRol, @NotNull Date uFechaRegistro, Set<DetalleUsuario> datos,
			PerfilVendedor datosVendedor, Set<Notificaciones> notificaciones, Set<Pedidos> pedidos,
			Set<Reseñas> reseñas) {
		super();
		this.idUsuario = idUsuario;
		this.uNombre = uNombre;
		this.uApellidos = uApellidos;
		this.uEmail = uEmail;
		this.uPassword = uPassword;
		this.uRol = uRol;
		this.uFechaRegistro = uFechaRegistro;
		this.datos = datos;
		this.datosVendedor = datosVendedor;
		this.notificaciones = notificaciones;
		this.pedidos = pedidos;
		this.reseñas = reseñas;
	}

}
