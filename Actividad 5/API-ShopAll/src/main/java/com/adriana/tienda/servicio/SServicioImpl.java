package com.adriana.tienda.servicio;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adriana.tienda.datos.Categoria;
import com.adriana.tienda.datos.Subcategoria;
import com.adriana.tienda.datos.dto.SubcategoriaDTO;
import com.adriana.tienda.repositorio.CRepositorio;
import com.adriana.tienda.repositorio.SRepositorio;

@Service
public class SServicioImpl implements SServicio {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private SRepositorio sRep;

	@Autowired
	private CRepositorio cRep;

	// convertir entidad a dto
	private SubcategoriaDTO mapearDTO(Subcategoria sub) {
		SubcategoriaDTO sDTO = modelMapper.map(sub, SubcategoriaDTO.class);
		return sDTO;
	}

	// convertir de DTO a Entidad
	private Subcategoria mapearEntidad(SubcategoriaDTO sDTO) {
		Subcategoria sub = modelMapper.map(sDTO, Subcategoria.class);
		return sub;
	}

	@Override
	public SubcategoriaDTO addSubcategoria(int categoria, SubcategoriaDTO sDTO) {
		Subcategoria sub = mapearEntidad(sDTO);
		Categoria cat = cRep.findById(categoria).orElseThrow();
		sub.setCategoria(cat);
		Subcategoria nuevaSub = sRep.save(sub);
		SubcategoriaDTO sRespuesta = mapearDTO(nuevaSub);
		return sRespuesta;
	}

	@Override
	public List<SubcategoriaDTO> getAll() {
		List<Subcategoria> lSubcategoria = sRep.findAll();
		return lSubcategoria.stream().map(sub -> mapearDTO(sub)).collect(Collectors.toList());
	}

	@Override
	public SubcategoriaDTO getSubcategoriaByIdSubcategoria(int idSubcategoria) {
		Subcategoria sub = sRep.findById(idSubcategoria).orElseThrow();
		return mapearDTO(sub);
	}
}
