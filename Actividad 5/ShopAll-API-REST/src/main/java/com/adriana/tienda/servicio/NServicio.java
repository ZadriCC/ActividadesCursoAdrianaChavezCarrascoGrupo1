package com.adriana.tienda.servicio;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adriana.tienda.datos.Notificacion;
import com.adriana.tienda.datos.Usuario;
import com.adriana.tienda.dto.NotificacionDTO;
import com.adriana.tienda.excepciones.ErrorAppException;
import com.adriana.tienda.excepciones.CampoNuloOVacioException;
import com.adriana.tienda.excepciones.RecursoNoEncontradoException;
import com.adriana.tienda.reposiorio.NRepositorio;
import com.adriana.tienda.reposiorio.URepositorio;

@Service
public class NServicio {

	@Autowired
	private NRepositorio nRep;

	@Autowired
	private URepositorio uRep;

	// convertir entidad a dto
	private NotificacionDTO mapearDTO(Notificacion noti) {
		NotificacionDTO nDTO = new NotificacionDTO();
		nDTO.setIdNotificacion(noti.getIdNotificacion());
		nDTO.setnDescripcion(noti.getnDescripcion());
		nDTO.setnFecha(noti.getnFecha());
		nDTO.setUsuario(noti.getUsuario());
		return nDTO;
	}

	// convertir de DTO a Entidad
	private Notificacion mapearEntidad(NotificacionDTO nDTO) {
		Notificacion noti = new Notificacion();
		noti.setnDescripcion(nDTO.getnDescripcion());
		noti.setnFecha(nDTO.getnFecha());
		noti.setUsuario(nDTO.getUsuario());
		return noti;
	}

	private void compararIds(Notificacion notificacion, int idUsuario) {
		if (!Integer.valueOf(notificacion.getUsuario().getIdUsuario()).equals(idUsuario)) {
			throw new ErrorAppException("La notificación no pertenece al usuario proporcionado");
		}
	}

	public NotificacionDTO addNotificacion(int idUsuario, NotificacionDTO nDTO) {
		Notificacion noti = mapearEntidad(nDTO);
		Usuario user = uRep.findById(idUsuario)
				.orElseThrow(() -> new RecursoNoEncontradoException("Usuario", "id", idUsuario));
		noti.setUsuario(user);

		LocalDate fecha = LocalDate.now();
		noti.setnFecha(fecha);

		if (nDTO.getnDescripcion() == null || nDTO.getnDescripcion().trim().isEmpty()) {
			throw new CampoNuloOVacioException("La descripción no puede estar vacía o nula.");
		}

		Notificacion nuevoDTUser = nRep.save(noti);
		return mapearDTO(nuevoDTUser);
	}

	public List<NotificacionDTO> getAll() {
		List<Notificacion> lNotificaciones = nRep.findAll();
		return lNotificaciones.stream().map(noti -> mapearDTO(noti)).collect(Collectors.toList());
	}

	public List<NotificacionDTO> getAllByUsuario(int idUsuario) {
		Usuario user = uRep.findById(idUsuario)
				.orElseThrow(() -> new RecursoNoEncontradoException("Usuario", "id", idUsuario));

		List<Notificacion> notificaciones = nRep.findByUsuario(user);
		return notificaciones.stream().map(this::mapearDTO).collect(Collectors.toList());
	}

	public NotificacionDTO getById(int idUsuario, int idNotificacion) {
		uRep.findById(idUsuario).orElseThrow(() -> new RecursoNoEncontradoException("Usuario", "id", idUsuario));
		Notificacion noti = nRep.findById(idNotificacion)
				.orElseThrow(() -> new RecursoNoEncontradoException("Notificación", "id", idNotificacion));

		compararIds(noti, idUsuario);

		return mapearDTO(noti);
	}

	public NotificacionDTO updateNotificacion(int idUsuario, int idNotificacion, NotificacionDTO nDTO) {
		uRep.findById(idUsuario).orElseThrow(() -> new RecursoNoEncontradoException("Usuario", "id", idUsuario));
		Notificacion noti = nRep.findById(idNotificacion)
				.orElseThrow(() -> new RecursoNoEncontradoException("Notificación", "id", idNotificacion));
		compararIds(noti, idUsuario);
		noti.setnDescripcion(nDTO.getnDescripcion());
		if (nDTO.getnDescripcion() == null || nDTO.getnDescripcion().trim().isEmpty()) {
			throw new CampoNuloOVacioException("La descripción no puede estar vacía o nula.");
		}
		Notificacion updatedNoti = nRep.save(noti);

		return mapearDTO(updatedNoti);
	}

	public void deleteNotificacion(int idUsuario, int idNotificacion) {
		uRep.findById(idUsuario).orElseThrow(() -> new RecursoNoEncontradoException("Usuario", "id", idUsuario));
		Notificacion noti = nRep.findById(idNotificacion)
				.orElseThrow(() -> new RecursoNoEncontradoException("Notificación", "id", idNotificacion));
		compararIds(noti, idUsuario);
		nRep.delete(noti);
	}

}
