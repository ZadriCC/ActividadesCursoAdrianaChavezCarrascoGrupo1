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

import com.adriana.tienda.dto.RespuestGenerica;
import com.adriana.tienda.dto.UsuarioDTO;
import com.adriana.tienda.servicio.UServicio;

@RestController
@RequestMapping("/shopall/usuarios")
public class UControlador {

	@Autowired
	private UServicio uSer;

	@PostMapping("/agregar")
	public ResponseEntity<UsuarioDTO> guardarUsuario(@RequestBody UsuarioDTO uDTO) {
		return new ResponseEntity<>(uSer.addUsuario(uDTO), HttpStatus.CREATED);

	}

	@GetMapping
	public List<UsuarioDTO> listarUsuarios() {
		return uSer.getAll();
	}

	@GetMapping("/{idUsuario}")
	public ResponseEntity<UsuarioDTO> obtenerUsuariosPorId(@PathVariable(name = "idUsuario") int idUsuario) {
		return ResponseEntity.ok(uSer.getById(idUsuario));
	}

	@PutMapping("/actualizar/{idUsuario}")
	public ResponseEntity<UsuarioDTO> actualizarUsuario(@RequestBody UsuarioDTO uDTO,
			@PathVariable(name = "idUsuario") int idUsuario) {
		UsuarioDTO uRespuesta = uSer.updateUsuario(uDTO, idUsuario);
		return new ResponseEntity<>(uRespuesta, HttpStatus.OK);
	}

	@DeleteMapping("eliminar/{idUsuario}")
	public ResponseEntity<String> eliminarUsuario(@PathVariable(name = "idUsuario") int idUsuario) {
		uSer.deleteUsuario(idUsuario);
		return new ResponseEntity<>("Usuario eliminado exitosamente", HttpStatus.OK);
	}
}