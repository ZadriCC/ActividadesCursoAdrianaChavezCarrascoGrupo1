package com.adriana.tienda.servicio;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.adriana.tienda.datos.Usuario;
import com.adriana.tienda.dto.NotificacionDTO;
import com.adriana.tienda.dto.UsuarioDTO;
import com.adriana.tienda.excepciones.CampoDuplicadoException;
import com.adriana.tienda.excepciones.DatoNoValidoException;
import com.adriana.tienda.excepciones.RecursoNoEncontradoException;
import com.adriana.tienda.reposiorio.URepositorio;
import com.adriana.tienda.utilidades.Validaciones;

@Service
public class UServicio {

	@Autowired
	private URepositorio uRep;

	@Autowired
	private NServicio nSer;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// convertir entidad a dto
	private UsuarioDTO mapearDTO(Usuario user) {
		UsuarioDTO uDTO = new UsuarioDTO();
		uDTO.setIdUsuario(user.getIdUsuario());
		uDTO.setuNombre(user.getuNombre());
		uDTO.setuApellidos(user.getuApellidos());
		uDTO.setuEmail(user.getuEmail());
		uDTO.setuPassword(user.getuPassword());
		uDTO.setuRol(user.getuRol());
		uDTO.setuFecha(user.getuFecha());
		return uDTO;
	}

	// convertir de DTO a Entidad
	private Usuario mapearEntidad(UsuarioDTO uDTO) {
		Usuario user = new Usuario();
		user.setIdUsuario(uDTO.getIdUsuario());
		user.setuNombre(uDTO.getuNombre());
		user.setuApellidos(uDTO.getuApellidos());
		user.setuEmail(uDTO.getuEmail());
		user.setuPassword(uDTO.getuPassword());
		user.setuRol(uDTO.getuRol());
		user.setuFecha(uDTO.getuFecha());
		return user;
	}

	private void validarEmailDuplicado(String email) {
		if (uRep.existsByuEmail(email)) {
			throw new CampoDuplicadoException("El correo electrónico ya está en uso.");
		}
	}

	private boolean validarPassword(String password) {

		if ((password.length() < 8) || (password.length() > 16)) {
			throw new DatoNoValidoException("La contraseña debe tener entre 8 y 16 caracteres.");
		}
		if (!password.matches(".*[a-z].*")) {
			throw new DatoNoValidoException("La contraseña debe contener al menos una letra minúscula.");
		}
		if (!password.matches(".*[A-Z].*")) {
			throw new DatoNoValidoException("La contraseña debe contener al menos una letra mayúscula.");
		}
		if (!password.matches(".*\\d.*")) {
			throw new DatoNoValidoException("La contraseña debe contener al menos un número.");
		}
		if (!password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\",.<>/?\\\\|].*")) {
			throw new DatoNoValidoException("La contraseña debe contener al menos un carácter especial.");
		}

		return true;
	}

	public UsuarioDTO addUsuario(UsuarioDTO uDTO) {

		Validaciones.validarCampoNuloOVacio(uDTO.getuNombre(), "Nombre");
		Validaciones.validarCampoNuloOVacio(uDTO.getuApellidos(), "Apellidos");
		Validaciones.validarCampoNuloOVacio(uDTO.getuEmail(), "Email");
		Validaciones.validarCampoNuloOVacio(uDTO.getuPassword(), "Contraseña");

		validarEmailDuplicado(uDTO.getuEmail());
		validarPassword(uDTO.getuPassword());

		Usuario user = mapearEntidad(uDTO);
		LocalDate fecha = LocalDate.now();
		user.setuFecha(fecha);
		user.setuRol("comprador");
		user.setuPassword(passwordEncoder.encode(uDTO.getuPassword()));
		Usuario nuevoUsuario = uRep.save(user);

		UsuarioDTO uRespuesta = mapearDTO(nuevoUsuario);

		NotificacionDTO nuevaNDTO = new NotificacionDTO();
		String nombre = nuevoUsuario.getuNombre();
		nuevaNDTO.setnDescripcion(nombre + ", " + "¡Bienvenido(a) a ShopAll ! Tu cuenta ha sido creada con éxito.");
		nSer.addNotificacion(nuevoUsuario.getIdUsuario(), nuevaNDTO);

		return uRespuesta;
	}

	public List<UsuarioDTO> getAll() {
		List<Usuario> lUsuarios = uRep.findAll();
		return lUsuarios.stream().map(user -> mapearDTO(user)).collect(Collectors.toList());
	}

	public UsuarioDTO getById(int idUsuario) {
		Usuario user = uRep.findById(idUsuario)
				.orElseThrow(() -> new RecursoNoEncontradoException("Usuario", "id", idUsuario));
		return mapearDTO(user);
	}

	public UsuarioDTO updateUsuario(UsuarioDTO uDTO, int idUsuario) {
		Usuario user = uRep.findById(idUsuario)
				.orElseThrow(() -> new RecursoNoEncontradoException("Usuario", "id", idUsuario));

		Validaciones.validarCampoNuloOVacio(uDTO.getuNombre(), "Nombre");
		Validaciones.validarCampoNuloOVacio(uDTO.getuApellidos(), "Apellidos");
		Validaciones.validarCampoNuloOVacio(uDTO.getuEmail(), "Email");
		// Verificar si el email ha cambiado
		if (!user.getuEmail().equals(uDTO.getuEmail())) {
			// Verifica si el nuevo email ya existe
			validarEmailDuplicado(uDTO.getuEmail());
			user.setuEmail(uDTO.getuEmail());
		}
		Validaciones.validarCampoNuloOVacio(uDTO.getuPassword(), "Contraseña");
		if (!validarPassword(uDTO.getuPassword())) {
			throw new DatoNoValidoException("La contraseña no cumple con los requisitos.");
		} else {
			user.setuPassword(passwordEncoder.encode(uDTO.getuPassword()));
		}

		user.setuNombre(uDTO.getuNombre());
		user.setuApellidos(uDTO.getuApellidos());

		Usuario uActualizada = uRep.save(user);

		NotificacionDTO nuevaNDTO = new NotificacionDTO();
		nuevaNDTO.setnDescripcion("Se han actualizado los datos con éxito.");
		nSer.addNotificacion(uActualizada.getIdUsuario(), nuevaNDTO);
		return mapearDTO(uActualizada);
	}

	public void deleteUsuario(int idUsuario) {
		Usuario user = uRep.findById(idUsuario)
				.orElseThrow(() -> new RecursoNoEncontradoException("Usuario", "id", idUsuario));
		uRep.delete(user);
	}
}
