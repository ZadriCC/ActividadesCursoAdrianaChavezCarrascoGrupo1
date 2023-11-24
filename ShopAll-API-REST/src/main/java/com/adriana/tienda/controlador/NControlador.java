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

import com.adriana.tienda.dto.NotificacionDTO;
import com.adriana.tienda.servicio.NServicio;

@RestController
@RequestMapping("/shopall/notificaciones")
public class NControlador {
	@Autowired
	private NServicio nServ;

	@PostMapping("/agregar/usuario/{idUsuario}")
	public ResponseEntity<NotificacionDTO> addNotificacion(@PathVariable(value = "idUsuario") int idUsuario,
			@RequestBody NotificacionDTO nDTO) {
		return new ResponseEntity<>(nServ.addNotificacion(idUsuario, nDTO), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<NotificacionDTO>> getAll() {
		List<NotificacionDTO> notificaciones = nServ.getAll();
		return new ResponseEntity<>(notificaciones, HttpStatus.OK);
	}

	@GetMapping("/usuario/{idUsuario}")
	public ResponseEntity<List<NotificacionDTO>> getNotificacionesPorUsuario(@PathVariable int idUsuario) {
		List<NotificacionDTO> notificaciones = nServ.getAllByUsuario(idUsuario);
		return new ResponseEntity<>(notificaciones, HttpStatus.OK);
	}

	@GetMapping("/usuario/{idUsuario}/notificacion/{idNotificacion}")
	public ResponseEntity<NotificacionDTO> getNotificacionById(@PathVariable(value = "idUsuario") int idUsuario,
			@PathVariable(value = "idNotificacion") int idNotificacion) {
		NotificacionDTO notificacion = nServ.getById(idUsuario, idNotificacion);
		return ResponseEntity.ok(notificacion);
	}

	@PutMapping("/actualizar/usuario/{idUsuario}/notificacion/{idNotificacion}")
	public ResponseEntity<NotificacionDTO> updateNotificacion(@PathVariable(value = "idUsuario") int idUsuario,
			@PathVariable(value = "idNotificacion") int idNotificacion, @RequestBody NotificacionDTO nDTO) {
		NotificacionDTO updatedNotificacion = nServ.updateNotificacion(idUsuario, idNotificacion, nDTO);
		return ResponseEntity.ok(updatedNotificacion);
	}

	@DeleteMapping("/eliminar/usuario/{idUsuario}/notificacion/{idNotificacion}")
	public ResponseEntity<String> deleteNotificacion(@PathVariable(value = "idUsuario") int idUsuario,
			@PathVariable(value = "idNotificacion") int idNotificacion) {
		nServ.deleteNotificacion(idUsuario, idNotificacion);
		return new ResponseEntity<>("Notificación eliminada correctamente.", HttpStatus.OK);
	}
}
