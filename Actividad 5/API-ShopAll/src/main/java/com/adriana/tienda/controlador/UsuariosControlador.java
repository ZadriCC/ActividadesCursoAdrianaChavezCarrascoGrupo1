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

import com.adriana.tienda.datos.dto.UsuariosDTO;
import com.adriana.tienda.servicio.UsuariosServicio;

@RestController
@RequestMapping("/ShopAll/usuarios")
public class UsuariosControlador {
	@Autowired
	private UsuariosServicio uSer;

	@PostMapping
	public ResponseEntity<UsuariosDTO> addUsuario(@RequestBody UsuariosDTO uDTO) {
		return new ResponseEntity<>(uSer.addUsuario(uDTO), HttpStatus.CREATED);
	}

	@GetMapping
	public List<UsuariosDTO> getAll() {
		return uSer.getAll();
	}
}
