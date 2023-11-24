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

import com.adriana.tienda.datos.dto.DetalleUsuarioDTO;
import com.adriana.tienda.servicio.DUServicio;

@RestController
@RequestMapping("/shopall/detalle-usuario")
public class DUControlador {

	@Autowired
	private DUServicio dtuServ;

	@PostMapping("/usuario/detalle-usuario/{usuarios}")
	public ResponseEntity<DetalleUsuarioDTO> addDTUsuario(@PathVariable(value = "usuarios") int usuarios,
			@RequestBody DetalleUsuarioDTO dtuDTO) {
		return new ResponseEntity<>(dtuServ.addDTUsuario(usuarios, dtuDTO), HttpStatus.CREATED);
	}

	@GetMapping
	public List<DetalleUsuarioDTO> getAll() {
		return dtuServ.getAll();
	}

	@GetMapping("/usuario/{idUsuario}/detalle-usuario/{idDUsuario}")
	public ResponseEntity<DetalleUsuarioDTO> obtenerDTUsuarioPorID(@PathVariable(value = "idUsuario") Integer idUsuario,
			@PathVariable(value = "idDUsuario") Integer idDUsuario) {
		DetalleUsuarioDTO dtuDTO = dtuServ.getById(idUsuario, idDUsuario);
		return new ResponseEntity<>(dtuDTO, HttpStatus.OK);
	}

	@PutMapping("/usuario/{idUsuario}/detalle-usuario/{idDUsuario}")
	public ResponseEntity<DetalleUsuarioDTO> actualizarDTUsuario(@PathVariable(value = "idUsuario") Integer idUsuario,
			@PathVariable(value = "idDUsuario") Integer idDUsuario, @RequestBody DetalleUsuarioDTO dtuDTO) {
		DetalleUsuarioDTO dtuActualizado = dtuServ.updateDTUsuario(idUsuario, idDUsuario, dtuDTO);
		return new ResponseEntity<>(dtuActualizado, HttpStatus.OK);
	}

	@DeleteMapping("/usuario/{idUsuario}/detalle-usuario/{idDUsuario}")
	public ResponseEntity<String> eliminarDTUsuario(@PathVariable(value = "idUsuario") Integer idUsuario,
			@PathVariable(value = "idDUsuario") Integer idDUsuario) {
		dtuServ.deleteDTUsuario(idUsuario, idDUsuario);
		return new ResponseEntity<>("Se eliminaron los datos con exito", HttpStatus.OK);
	}

}
