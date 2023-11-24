package com.adriana.tienda.servicio;

import java.util.List;

import com.adriana.tienda.datos.dto.NotificacionesDTO;

public interface NServicio {

	public NotificacionesDTO addNotificaciones(int idUser, NotificacionesDTO nDTO);

	public List<NotificacionesDTO> getAll();

}
