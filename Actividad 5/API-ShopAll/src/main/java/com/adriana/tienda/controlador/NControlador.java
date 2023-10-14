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

import com.adriana.tienda.datos.dto.NotificacionesDTO;
import com.adriana.tienda.servicio.NServicio;

@RestController
@RequestMapping("/shopall/notificaciones")
public class NControlador {

	@Autowired
	private NServicio nServ;

	@PostMapping("/usuarios/{usuarios}")
	public ResponseEntity<NotificacionesDTO> addNotificaciones(@PathVariable(value = "usuarios") int usuarios,
			@RequestBody NotificacionesDTO nDTO) {
		return new ResponseEntity<>(nServ.addNotificaciones(usuarios, nDTO), HttpStatus.CREATED);
	}

	@GetMapping("/mostrar-todos")
	public List<NotificacionesDTO> getAll() {
		return nServ.getAll();
	}

}
