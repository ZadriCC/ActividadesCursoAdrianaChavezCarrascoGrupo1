package com.adriana.tienda.servicio;

import java.util.List;

import com.adriana.tienda.datos.dto.PedidosDTO;

public interface PdServicio {

	public PedidosDTO addPedidos(int idUser, PedidosDTO pdDTO);

	public List<PedidosDTO> getAll();

}
