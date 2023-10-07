package com.adriana.tienda.servicio;

import java.util.List;

import com.adriana.tienda.datos.dto.CategoriaDTO;

public interface CServicio {

	public CategoriaDTO addCategoria(CategoriaDTO cDTO);

	public List<CategoriaDTO> getAll();

}
