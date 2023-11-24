package com.adriana.tienda.servicio;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adriana.tienda.datos.Categoria;
import com.adriana.tienda.datos.Subcategoria;
import com.adriana.tienda.dto.SubcategoriaDTO;
import com.adriana.tienda.excepciones.CampoDuplicadoException;
import com.adriana.tienda.excepciones.ErrorAppException;
import com.adriana.tienda.excepciones.RecursoNoEncontradoException;
import com.adriana.tienda.reposiorio.CRepositorio;
import com.adriana.tienda.reposiorio.SRepositorio;
import com.adriana.tienda.utilidades.Validaciones;

@Service
public class SServicio {

	@Autowired
	private SRepositorio sRep;

	@Autowired
	private CRepositorio cRep;

	// convertir entidad a dto
	private SubcategoriaDTO mapearDTO(Subcategoria sub) {
		SubcategoriaDTO sDTO = new SubcategoriaDTO();
		sDTO.setIdSubcategoria(sub.getIdSubcategoria());
		sDTO.setScTitulo(sub.getScTitulo());
		return sDTO;
	}

	// convertir de DTO a Entidad
	private Subcategoria mapearEntidad(SubcategoriaDTO sDTO) {
		Subcategoria sub = new Subcategoria();
		sub.setIdSubcategoria(sDTO.getIdSubcategoria());
		sub.setScTitulo(sDTO.getScTitulo());
		return sub;
	}

	public SubcategoriaDTO addSubcategoria(int idCategoria, SubcategoriaDTO sDTO) {
		if (sRep.existsByScTitulo(sDTO.getScTitulo())) {
			throw new CampoDuplicadoException("La categoría ya existe");
		}
		Validaciones.validarCampoNuloOVacio(sDTO.getScTitulo(), "Titulo");
		Subcategoria sc = mapearEntidad(sDTO);
		Categoria ct = cRep.findById(idCategoria)
				.orElseThrow(() -> new RecursoNoEncontradoException("Categoria", "id", idCategoria));
		sc.setCategoria(ct);
		Subcategoria nuevaSub = sRep.save(sc);
		SubcategoriaDTO sRespuesta = mapearDTO(nuevaSub);
		return sRespuesta;
	}

	public SubcategoriaDTO getById(int idCategoria, int idSubcategoria) {
		Categoria ct = cRep.findById(idCategoria)
				.orElseThrow(() -> new RecursoNoEncontradoException("Categoria", "id", idCategoria));
		Subcategoria sc = sRep.findById(idSubcategoria)
				.orElseThrow(() -> new RecursoNoEncontradoException("Subcategoria", "id", idSubcategoria));

		if (sc.getCategoria().getIdCategoria() != ct.getIdCategoria()) {
			throw new ErrorAppException(
					"IDs incorrectos: " + sc.getCategoria().getIdCategoria() + " y " + ct.getIdCategoria());
		}
		return mapearDTO(sc);
	}

	public List<SubcategoriaDTO> getAll() {
		List<Subcategoria> lSubcategorias = sRep.findAll();
		return lSubcategorias.stream().map(sub -> mapearDTO(sub)).collect(Collectors.toList());
	}

	public SubcategoriaDTO updateSubcategoria(int idCategoria, int idSubcategoria, SubcategoriaDTO sDTO) {
		Categoria ct = cRep.findById(idCategoria)
				.orElseThrow(() -> new RecursoNoEncontradoException("Categoria", "id", idCategoria));
		Subcategoria sc = sRep.findById(idSubcategoria)
				.orElseThrow(() -> new RecursoNoEncontradoException("Subcategoria", "id", idSubcategoria));
		if (sc.getCategoria().getIdCategoria() != ct.getIdCategoria()) {
			throw new ErrorAppException(
					"IDs incorrectos: " + sc.getCategoria().getIdCategoria() + " y " + ct.getIdCategoria());
		}
		Validaciones.validarCampoNuloOVacio(sDTO.getScTitulo(), "Titulo");
		sc.setScTitulo(sDTO.getScTitulo());
		Subcategoria scActualizada = sRep.save(sc);
		return mapearDTO(scActualizada);
	}

	public void deleteSubcategoria(int idCategoria, int idSubcategoria) {
		Categoria ct = cRep.findById(idCategoria)
				.orElseThrow(() -> new RecursoNoEncontradoException("Categoria", "id", idCategoria));
		Subcategoria sc = sRep.findById(idSubcategoria)
				.orElseThrow(() -> new RecursoNoEncontradoException("Subcategoría", "id", idSubcategoria));
		if (sc.getCategoria().getIdCategoria() != ct.getIdCategoria()) {
			throw new ErrorAppException(
					"IDs incorrectos: " + sc.getCategoria().getIdCategoria() + " y " + ct.getIdCategoria());
		}
		sRep.delete(sc);

	}
}
