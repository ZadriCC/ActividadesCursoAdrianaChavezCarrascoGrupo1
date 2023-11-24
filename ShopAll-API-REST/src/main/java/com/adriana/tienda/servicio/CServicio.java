package com.adriana.tienda.servicio;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adriana.tienda.datos.Categoria;
import com.adriana.tienda.dto.CategoriaDTO;
import com.adriana.tienda.excepciones.CampoDuplicadoException;
import com.adriana.tienda.excepciones.RecursoNoEncontradoException;
import com.adriana.tienda.reposiorio.CRepositorio;
import com.adriana.tienda.utilidades.Validaciones;

@Service
public class CServicio {

	@Autowired
	private CRepositorio ctRep;

	// convertir entidad a dto
	private CategoriaDTO mapearDTO(Categoria cat) {
		CategoriaDTO cDTO = new CategoriaDTO();
		cDTO.setIdCategoria(cat.getIdCategoria());
		cDTO.setCtTitulo(cat.getCtTitulo());
		return cDTO;
	}

	// convertir de DTO a Entidad
	private Categoria mapearEntidad(CategoriaDTO cDTO) {
		Categoria cat = new Categoria();
		cat.setIdCategoria(cDTO.getIdCategoria());
		cat.setCtTitulo(cDTO.getCtTitulo());
		return cat;
	}

	public CategoriaDTO addCategoria(CategoriaDTO cDTO) {
		// Verificar si la categoría ya existe por su título
		if (ctRep.existsByCtTitulo(cDTO.getCtTitulo())) {
			throw new CampoDuplicadoException("La categoría ya existe");
		}
		Validaciones.validarCampoNuloOVacio(cDTO.getCtTitulo(), "Titulo");
		Categoria cat = mapearEntidad(cDTO);
		Categoria nuevaCat = ctRep.save(cat);
		CategoriaDTO ctRespuesta = mapearDTO(nuevaCat);
		return ctRespuesta;
	}

	public List<CategoriaDTO> getAll() {
		List<Categoria> lCategorias = ctRep.findAll();
		return lCategorias.stream().map(cat -> mapearDTO(cat)).collect(Collectors.toList());
	}

	public CategoriaDTO getById(int idCategoria) {
		Categoria cat = ctRep.findById(idCategoria)
				.orElseThrow(() -> new RecursoNoEncontradoException("Categoria", "id", idCategoria));
		return mapearDTO(cat);
	}

	public CategoriaDTO updateCategoria(int idCategoria, CategoriaDTO cDTO) {
		Categoria cat = ctRep.findById(idCategoria)
				.orElseThrow(() -> new RecursoNoEncontradoException("Categoria", "id", idCategoria));
		// Verificar si la categoría ya existe por su título
		if (ctRep.existsByCtTitulo(cDTO.getCtTitulo())) {
			throw new CampoDuplicadoException("La categoría ya existe");
		}
		Validaciones.validarCampoNuloOVacio(cDTO.getCtTitulo(), "Titulo");
		cat.setCtTitulo(cDTO.getCtTitulo());
		Categoria ctActualizada = ctRep.save(cat);
		return mapearDTO(ctActualizada);
	}

	public void deleteCategoria(int idCategoria) {
		Categoria cat = ctRep.findById(idCategoria)
				.orElseThrow(() -> new RecursoNoEncontradoException("Categoria", "id", idCategoria));
		ctRep.delete(cat);
	}
}
