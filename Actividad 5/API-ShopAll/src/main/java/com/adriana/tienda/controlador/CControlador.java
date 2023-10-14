package com.adriana.tienda.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adriana.tienda.datos.dto.CategoriaDTO;
import com.adriana.tienda.servicio.CServicio;

@RestController
@RequestMapping("/shopall/categorias")
public class CControlador {

	@Autowired
	private CServicio cSer;

	@PostMapping("/agregar-categoria")
	public ResponseEntity<CategoriaDTO> addCategoria(@RequestBody CategoriaDTO cDTO) {
		return new ResponseEntity<>(cSer.addCategoria(cDTO), HttpStatus.CREATED);
	}

	@GetMapping("/mostrar-todas")
	public List<CategoriaDTO> getAll() {
		return cSer.getAll();
	}

}
