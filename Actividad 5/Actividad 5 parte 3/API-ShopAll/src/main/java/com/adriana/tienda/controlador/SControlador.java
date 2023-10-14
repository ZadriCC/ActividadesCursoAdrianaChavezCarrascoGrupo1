package com.adriana.tienda.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adriana.tienda.datos.dto.SubcategoriaDTO;
import com.adriana.tienda.servicio.SServicio;

@RestController
@RequestMapping("/shopall/subcategorias")
public class SControlador {

	@Autowired
	private SServicio sServ;

	@PostMapping("/categorias/{categoria}")
	public ResponseEntity<SubcategoriaDTO> addSubcategorias(@PathVariable(value = "categoria") int categoria,
			@RequestBody SubcategoriaDTO sDTO) {
		return new ResponseEntity<>(sServ.addSubcategoria(categoria, sDTO), HttpStatus.CREATED);
	}

	@GetMapping("/mostrar-todos")
	public List<SubcategoriaDTO> getAll() {
		return sServ.getAll();
	}

}
