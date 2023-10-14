package com.adriana.tienda.servicio;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adriana.tienda.datos.Notificaciones;
import com.adriana.tienda.datos.Usuarios;
import com.adriana.tienda.datos.dto.NotificacionesDTO;
import com.adriana.tienda.repositorio.NRepositorio;
import com.adriana.tienda.repositorio.URepositorio;

@Service
public class NServicioImpl implements NServicio {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private NRepositorio nRep;

	@Autowired
	private URepositorio uRep;

	// convertir entidad a dto
	private NotificacionesDTO mapearDTO(Notificaciones noti) {
		NotificacionesDTO nDTO = modelMapper.map(noti, NotificacionesDTO.class);
		return nDTO;
	}

	// convertir de DTO a Entidad
	private Notificaciones mapearEntidad(NotificacionesDTO nDTO) {
		Notificaciones noti = modelMapper.map(nDTO, Notificaciones.class);
		return noti;
	}

	@Override
	public NotificacionesDTO addNotificaciones(int idUser, NotificacionesDTO nDTO) {
		Notificaciones noti = mapearEntidad(nDTO);
		Usuarios user = uRep.findById(idUser).orElseThrow();
		noti.setUsuarios(user);
		
		ZoneId zonaHoraria = ZoneId.of("America/Mexico_City");
		LocalDateTime fechaHoraActual = LocalDateTime.now(zonaHoraria);
		Date fechaHora = Date.from(fechaHoraActual.atZone(zonaHoraria).toInstant());
		noti.setnFecha(fechaHora);
		
		Notificaciones nuevoDTUser = nRep.save(noti);
		return mapearDTO(nuevoDTUser);
	}

	@Override
	public List<NotificacionesDTO> getAll() {
		List<Notificaciones> lNotificaciones = nRep.findAll();
		return lNotificaciones.stream().map(noti -> mapearDTO(noti)).collect(Collectors.toList());
	}
}
