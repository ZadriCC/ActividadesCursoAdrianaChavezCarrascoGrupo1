package com.adriana.tienda.servicio;

import java.util.List;

import com.adriana.tienda.datos.dto.ReseñasDTO;

public interface RServicio {
	
	public ReseñasDTO addReseña(int idUsuario, int idProducto, ReseñasDTO rDTO);
	
	public List<ReseñasDTO> getAll();

}
