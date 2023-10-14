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

import com.adriana.tienda.datos.dto.ReseñasDTO;
import com.adriana.tienda.servicio.RServicio;

@RestController
@RequestMapping("/shopall/reseñas")
public class RControlador {
	@Autowired
	private RServicio rServ;

	@PostMapping("/usuario/{usuarios}/producto/{productos}")
	public ResponseEntity<ReseñasDTO> addReseña(@PathVariable(value = "usuarios") int usuarios,
			@PathVariable(value = "productos") int productos, @RequestBody ReseñasDTO rDTO) {
		return new ResponseEntity<>(rServ.addReseña(usuarios, productos, rDTO), HttpStatus.CREATED);
	}

	@GetMapping("/mostrar-todos")
	public List<ReseñasDTO> getAll() {
		return rServ.getAll();
	}
}
