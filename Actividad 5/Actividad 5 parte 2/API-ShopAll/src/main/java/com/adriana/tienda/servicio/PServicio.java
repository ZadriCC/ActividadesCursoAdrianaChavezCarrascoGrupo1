package com.adriana.tienda.servicio;

import java.util.List;

import com.adriana.tienda.datos.dto.ProductosDTO;

public interface PServicio {

	public ProductosDTO addProductos(ProductosDTO pDTO);

	public List<ProductosDTO> getAll();

}
