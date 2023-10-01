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

import com.adriana.tienda.datos.dto.DetalleUsuarioDTO;
import com.adriana.tienda.servicio.DetalleUsuarioServicio;

@RestController
@RequestMapping("/ShopAll")
public class DetalleUsuarioControlador {

	@Autowired
	private DetalleUsuarioServicio dtuServ;

	@PostMapping("/usuarios/{usuarios}/detalleusuario")
	public ResponseEntity<DetalleUsuarioDTO> addDTUsuario(@PathVariable(value = "usuarios") int usuarios,
			@RequestBody DetalleUsuarioDTO dtuDTO) {
		return new ResponseEntity<>(dtuServ.addDTUsuario(usuarios, dtuDTO), HttpStatus.CREATED);
	}
	@GetMapping("/usuarios/detalleusuario")
	public List<DetalleUsuarioDTO> getAll() {
		return dtuServ.getAll();
	}

}
