package com.adriana.tienda.servicio;

import java.util.List;

import com.adriana.tienda.datos.dto.DetalleProductoDTO;

public interface DPServicio {

	public DetalleProductoDTO addDProducto(int producto, DetalleProductoDTO dpDTO);

	public List<DetalleProductoDTO> getAll();
}
