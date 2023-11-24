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

import com.adriana.tienda.datos.dto.ProductosDTO;
import com.adriana.tienda.servicio.PServicio;

@RestController
@RequestMapping("/shopall/productos")
public class PControlador {

	@Autowired
	private PServicio pSer;

	@PostMapping("/agregar-producto")
	public ResponseEntity<ProductosDTO> addProductos(@RequestBody ProductosDTO pDTO) {
		return new ResponseEntity<>(pSer.addProductos(pDTO), HttpStatus.CREATED);
	}

	@GetMapping("/mostrar-todos")
	public List<ProductosDTO> getAll() {
		return pSer.getAll();
	}

}
