package com.adriana.tienda.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adriana.tienda.dto.SubcategoriaDTO;
import com.adriana.tienda.servicio.SServicio;

@RestController
@RequestMapping("/shopall/subcategorias")
public class SControlador {
	@Autowired
	private SServicio sServ;

	@PostMapping("/agregar/categoria/{idCategoria}")
	public ResponseEntity<SubcategoriaDTO> addSubcategorias(@PathVariable(value = "idCategoria") int idCategoria,
			@RequestBody SubcategoriaDTO scDTO) {
		return new ResponseEntity<>(sServ.addSubcategoria(idCategoria, scDTO), HttpStatus.CREATED);
	}

	@GetMapping
	public List<SubcategoriaDTO> getAll() {
		return sServ.getAll();
	}

	@GetMapping("/categoria/{idCategoria}/subcategoria/{idSubcategoria}")
	public ResponseEntity<SubcategoriaDTO> getCategoriaById(@PathVariable(value = "idCategoria") int idCategoria,
			@PathVariable(value = "idSubcategoria") int idSubcategoria) {
		SubcategoriaDTO subcategoria = sServ.getById(idCategoria, idSubcategoria);
		return new ResponseEntity<>(subcategoria, HttpStatus.OK);
	}

	@PutMapping("/actualizar/categoria/{idCategoria}/subcategoria/{idSubcategoria}")
	public ResponseEntity<SubcategoriaDTO> updateSubcategoria(@PathVariable(value = "idCategoria") int idCategoria,
			@PathVariable(value = "idSubcategoria") int idSubcategoria, @RequestBody SubcategoriaDTO sDTO) {
		SubcategoriaDTO scActualizado = sServ.updateSubcategoria(idCategoria, idSubcategoria, sDTO);
		return new ResponseEntity<>(scActualizado, HttpStatus.OK);
	}

	@DeleteMapping("/eliminar/categoria/{idCategoria}/subcategoria/{idSubcategoria}")
	public ResponseEntity<String> eliminarSubcategoria(@PathVariable(value = "idCategoria") int idCategoria,
			@PathVariable(value = "idSubcategoria") int idSubcategoria) {
		sServ.deleteSubcategoria(idCategoria, idSubcategoria);
		return new ResponseEntity<>("Subcategoria eliminada exitosamente", HttpStatus.OK);
	}
}
