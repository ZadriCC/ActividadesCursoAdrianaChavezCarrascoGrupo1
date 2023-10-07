package com.adriana.tienda.servicio;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adriana.tienda.datos.Categoria;
import com.adriana.tienda.datos.dto.CategoriaDTO;
import com.adriana.tienda.repositorio.CRepositorio;

@Service
public class CServicioImpl implements CServicio {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CRepositorio cRep;

	// convertir entidad a dto
	private CategoriaDTO mapearDTO(Categoria cat) {
		CategoriaDTO cDTO = modelMapper.map(cat, CategoriaDTO.class);
		return cDTO;
	}

	// convertir de DTO a Entidad
	private Categoria mapearEntidad(CategoriaDTO cDTO) {
		Categoria cat = modelMapper.map(cDTO, Categoria.class);
		return cat;
	}

	@Override
	public CategoriaDTO addCategoria(CategoriaDTO cDTO) {
		Categoria cat = mapearEntidad(cDTO);
		Categoria nuevaCat = cRep.save(cat);
		CategoriaDTO cRespuesta = mapearDTO(nuevaCat);
		return cRespuesta;
	}

	@Override
	public List<CategoriaDTO> getAll() {
		List<Categoria> lCategoria = cRep.findAll();
		return lCategoria.stream().map(cat -> mapearDTO(cat)).collect(Collectors.toList());
	}
}
