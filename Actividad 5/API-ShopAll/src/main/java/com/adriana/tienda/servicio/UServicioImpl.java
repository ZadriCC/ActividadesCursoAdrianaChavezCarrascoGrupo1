package com.adriana.tienda.servicio;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adriana.tienda.datos.Usuarios;
import com.adriana.tienda.datos.dto.NotificacionesDTO;
import com.adriana.tienda.datos.dto.UsuariosDTO;
import com.adriana.tienda.excepciones.CampoNuloOVacioException;
import com.adriana.tienda.excepciones.EmailDuplicadoException;
import com.adriana.tienda.excepciones.PasswordInvalidException;
import com.adriana.tienda.excepciones.ResourceNotFoundException;
import com.adriana.tienda.repositorio.URepositorio;

@Service
public class UServicioImpl implements UServicio {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private URepositorio uRep;

	@Autowired
	private NServicio nSer;

	// convertir entidad a dto
	private UsuariosDTO mapearDTO(Usuarios user) {
		UsuariosDTO uDTO = modelMapper.map(user, UsuariosDTO.class);
		return uDTO;
	}

	// convertir de DTO a Entidad
	private Usuarios mapearEntidad(UsuariosDTO uDTO) {
		Usuarios user = modelMapper.map(uDTO, Usuarios.class);
		return user;
	}

	@Override
	public UsuariosDTO addUsuario(UsuariosDTO uDTO) {
		//verificar si ya existe el email
		if (uRep.existsByuEmail(uDTO.getuEmail())) {
			throw new EmailDuplicadoException("El correo electrónico ya está en uso.");
		}
		//validaciones de campos nulos o vacios
		if (uDTO.getuNombre() == null || uDTO.getuNombre().isEmpty()) {
			throw new CampoNuloOVacioException("El campo 'Nombre' no puede estar vacío.");
		}
		if (uDTO.getuApellidos() == null || uDTO.getuApellidos().isEmpty()) {
			throw new CampoNuloOVacioException("El campo 'Apellidos' no puede estar vacío.");
		}
		if (uDTO.getuEmail() == null || uDTO.getuEmail().isEmpty()) {
			throw new CampoNuloOVacioException("El campo 'Email' no puede estar vacío.");
		}

		if (uDTO.getuPassword() == null || uDTO.getuPassword().isEmpty()) {
			throw new CampoNuloOVacioException("El campo 'Contraseña' no puede estar vacío.");
		}
		//validar la contraseña
		if (!isValidPassword(uDTO.getuPassword())) {
			throw new PasswordInvalidException("La contraseña no cumple con los requisitos.");
		}
		Usuarios user = mapearEntidad(uDTO);

		ZoneId zonaHoraria = ZoneId.of("America/Mexico_City");
		LocalDateTime fechaHoraActual = LocalDateTime.now(zonaHoraria);
		Date fechaHora = Date.from(fechaHoraActual.atZone(zonaHoraria).toInstant());
		user.setuFechaRegistro(fechaHora);
		user.setuRol("comprador");

		Usuarios nuevoUsuario = uRep.save(user);
		UsuariosDTO uRespuesta = mapearDTO(nuevoUsuario);

		NotificacionesDTO nuevaNDTO = new NotificacionesDTO();
		nuevaNDTO.setnDescripcion("¡Bienvenido(a) a ShopAll ! Tu cuenta ha sido creada con éxito.");
		nSer.addNotificaciones(nuevoUsuario.getIdUsuario(), nuevaNDTO);

		return uRespuesta;
	}

	@Override
	public List<UsuariosDTO> getAll() {
		List<Usuarios> lUsuarios = uRep.findAll();
		return lUsuarios.stream().map(user -> mapearDTO(user)).collect(Collectors.toList());
	}

	@Override
	public UsuariosDTO getById(int idUsuario) {
		Usuarios user = uRep.findById(idUsuario)
				.orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", idUsuario));
		return mapearDTO(user);
	}

	@Override
	public UsuariosDTO updateUsuario(UsuariosDTO uDTO, int idUsuario) {
		Usuarios user = uRep.findById(idUsuario)
				.orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", idUsuario));

		// Verificar si el email ha cambiado
		if (!user.getuEmail().equals(uDTO.getuEmail())) {
			//Verifica si el nuevo email ya existe
			if (uRep.existsByuEmail(uDTO.getuEmail())) {
				throw new EmailDuplicadoException("El correo electrónico ya está en uso.");
			}
			user.setuEmail(uDTO.getuEmail());
		}

		// Verificar la contraseña
		if (!isValidPassword(uDTO.getuPassword())) {
			throw new PasswordInvalidException("La contraseña no cumple con los requisitos.");
		}

		user.setuPassword(uDTO.getuPassword());
		user.setuNombre(uDTO.getuNombre());
		user.setuApellidos(uDTO.getuApellidos());
		//validación de campos vacios o nulos
		if (uDTO.getuNombre() == null || uDTO.getuNombre().isEmpty()) {
			throw new CampoNuloOVacioException("El campo 'Nombre' no puede estar vacío.");
		}
		if (uDTO.getuApellidos() == null || uDTO.getuApellidos().isEmpty()) {
			throw new CampoNuloOVacioException("El campo 'Apellidos' no puede estar vacío.");
		}
		if (uDTO.getuEmail() == null || uDTO.getuEmail().isEmpty()) {
			throw new CampoNuloOVacioException("El campo 'Email' no puede estar vacío.");
		}

		if (uDTO.getuPassword() == null || uDTO.getuPassword().isEmpty()) {
			throw new CampoNuloOVacioException("El campo 'Contraseña' no puede estar vacío.");
		}

		Usuarios uActualizada = uRep.save(user);
		return mapearDTO(uActualizada);
	}

	@Override
	public void deleteUsuario(int idUsuario) {
		Usuarios user = uRep.findById(idUsuario)
				.orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", idUsuario));
		uRep.delete(user);

	}

	private boolean isValidPassword(String password) {
		//validación de la contraseña
		if (password.length() < 8) { //validar si tiene 8 digitos
			return false;
		}
		if (!password.matches(".*[a-z].*")) {//validar si al menos tiene una letra minúscula
			return false;
		}
		if (!password.matches(".*[A-Z].*")) {//validar si al menos tiene una letra mayuscula
			return false;
		}
		if (!password.matches(".*\\d.*")) {//validar si al menos tiene un número
			return false;
		}
		if (!password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\",.<>/?\\\\|].*")) {//validar si al menos tiene un caracter especial
			return false;
		}

		return true;
	}

}
