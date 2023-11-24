package com.adriana.tienda.servicio;

import java.util.List;

import com.adriana.tienda.datos.dto.SubcategoriaDTO;

public interface SServicio {

	public SubcategoriaDTO addSubcategoria(int categoria, SubcategoriaDTO sDTO);

	public SubcategoriaDTO getSubcategoriaByIdSubcategoria(int idSubcategoria);

	public List<SubcategoriaDTO> getAll();

}
