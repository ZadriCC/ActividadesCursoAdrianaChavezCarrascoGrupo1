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
import com.adriana.tienda.datos.dto.UsuariosDTO;
import com.adriana.tienda.repositorio.UsuariosRepositorio;

@Service
public class UsuariosServicioImpl implements UsuariosServicio {
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UsuariosRepositorio uRep;

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

		// Establecer la zona horaria
		ZoneId zonaHoraria = ZoneId.of("America/Mexico_City");
		// Obtener la fecha y hora actual
		LocalDateTime fechaHoraActual = LocalDateTime.now(zonaHoraria);
		// Convertir LocalDateTime a Date
		Date fechaHora = Date.from(fechaHoraActual.atZone(zonaHoraria).toInstant());
		// Asignar la fecha y hora restada al campo uFechaRegistro
		user.setuFechaRegistro(fechaHora);

		Usuarios nuevoUsuario = uRep.save(user);

		UsuariosDTO uRespuesta = mapearDTO(nuevoUsuario);
		return uRespuesta;
	}

	@Override
	public List<UsuariosDTO> getAll() {
		List<Usuarios> lUsuarios = uRep.findAll();
		return lUsuarios.stream().map(user -> mapearDTO(user)).collect(Collectors.toList());
	}

}
