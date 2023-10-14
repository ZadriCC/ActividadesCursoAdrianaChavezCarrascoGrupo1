package com.adriana.tienda.servicio;

import java.util.List;

import com.adriana.tienda.datos.dto.DetallePedidoDTO;

public interface DPdServicio {

	public DetallePedidoDTO addDPedido(int idPedido, int idVendedor, int idProducto, DetallePedidoDTO dpdDTO);

	public List<DetallePedidoDTO> getAll();

}
