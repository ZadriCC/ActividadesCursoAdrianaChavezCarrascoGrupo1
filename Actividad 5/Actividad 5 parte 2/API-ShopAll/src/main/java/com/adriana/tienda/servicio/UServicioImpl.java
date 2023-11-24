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
		Usuarios user = mapearEntidad(uDTO);

		ZoneId zonaHoraria = ZoneId.of("America/Mexico_City");
		LocalDateTime fechaHoraActual = LocalDateTime.now(zonaHoraria);
		Date fechaHora = Date.from(fechaHoraActual.atZone(zonaHoraria).toInstant());
		user.setuFechaRegistro(fechaHora);

		Usuarios nuevoUsuario = uRep.save(user);
		UsuariosDTO uRespuesta = mapearDTO(nuevoUsuario);

		NotificacionesDTO nuevaNDTO = new NotificacionesDTO();
		nuevaNDTO.setnDescripcion("¡Bienvenido/a! Tu cuenta ha sido creada con éxito.");
		nSer.addNotificaciones(nuevoUsuario.getIdUsuario(), nuevaNDTO);

		return uRespuesta;
	}

	@Override
	public List<UsuariosDTO> getAll() {
		List<Usuarios> lUsuarios = uRep.findAll();
		return lUsuarios.stream().map(user -> mapearDTO(user)).collect(Collectors.toList());
	}

}
