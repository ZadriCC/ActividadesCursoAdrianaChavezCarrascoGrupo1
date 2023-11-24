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

import com.adriana.tienda.dto.CategoriaDTO;
import com.adriana.tienda.servicio.CServicio;

@RestController
@RequestMapping("/shopall/categorias")
public class CControlador {

	@Autowired
	private CServicio ctSer;

	@PostMapping("/agregar")
	public ResponseEntity<CategoriaDTO> addCategoria(@RequestBody CategoriaDTO cDTO) {
		return new ResponseEntity<>(ctSer.addCategoria(cDTO), HttpStatus.CREATED);
	}

	@GetMapping
	public List<CategoriaDTO> getAll() {
		return ctSer.getAll();
	}

	@GetMapping("/{idCategoria}")
	public ResponseEntity<CategoriaDTO> getById(@PathVariable int idCategoria) {
		CategoriaDTO cat = ctSer.getById(idCategoria);
		return new ResponseEntity<>(cat, HttpStatus.OK);
	}

	@PutMapping("/actualizar/{idCategoria}")
	public ResponseEntity<CategoriaDTO> updateCategoria(@PathVariable int idCategoria, @RequestBody CategoriaDTO cDTO) {
		CategoriaDTO ctActualizada = ctSer.updateCategoria(idCategoria, cDTO);
		return new ResponseEntity<>(ctActualizada, HttpStatus.OK);
	}

	@DeleteMapping("/eliminar/{idCategoria}")
	public ResponseEntity<String> eliminarCategoria(@PathVariable(name = "idCategoria") int idCategoria) {
		ctSer.deleteCategoria(idCategoria);
		return new ResponseEntity<>("Categoria eliminada exitosamente", HttpStatus.OK);
	}
}
