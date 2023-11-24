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

import com.adriana.tienda.dto.ReseñaDTO;
import com.adriana.tienda.servicio.RServicio;

@RestController
@RequestMapping("/shopall/reseñas")
public class RControlador {

	@Autowired
	private RServicio rServ;

	@PostMapping("/usuario/{idUsuario}/producto/{idProducto}")
	public ResponseEntity<ReseñaDTO> addReseña(@PathVariable(value = "idUsuario") int idUsuario,
			@PathVariable(value = "idProducto") int idProducto, @RequestBody ReseñaDTO rDTO) {
		return new ResponseEntity<>(rServ.addReseña(idUsuario, idProducto, rDTO), HttpStatus.CREATED);
	}

	@GetMapping
	public List<ReseñaDTO> getAll() {
		return rServ.getAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<ReseñaDTO> getReseñaById(@PathVariable int id) {
		ReseñaDTO reseñaDTO = rServ.getById(id);
		return new ResponseEntity<>(reseñaDTO, HttpStatus.OK);
	}

	@PutMapping("/actualizar/{id}")
	public ResponseEntity<ReseñaDTO> updateReseña(@PathVariable int id, @RequestBody ReseñaDTO rDTO) {
		ReseñaDTO reseñaActualizada = rServ.updateReseña(id, rDTO);
		return new ResponseEntity<>(reseñaActualizada, HttpStatus.OK);
	}

	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<String> deleteReseña(@PathVariable int id) {
		rServ.deleteReseña(id);
		return new ResponseEntity<>("Reseña eliminada exitosamente", HttpStatus.OK);
	}
}
