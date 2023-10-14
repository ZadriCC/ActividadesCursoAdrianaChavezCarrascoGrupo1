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

import com.adriana.tienda.datos.dto.UsuariosDTO;
import com.adriana.tienda.servicio.UServicio;

@RestController
@RequestMapping("/shopall/usuarios")
public class UControlador {

	@Autowired
	private UServicio uSer;

	@PostMapping("/agregar-usuario")
	public ResponseEntity<UsuariosDTO> guardarUsuario(@RequestBody UsuariosDTO uDTO) {
		return new ResponseEntity<>(uSer.addUsuario(uDTO), HttpStatus.CREATED);

	}

	@GetMapping
	public List<UsuariosDTO> listarUsuarios() {
		return uSer.getAll();
	}

	@GetMapping("/usuario/{id}")
	public ResponseEntity<UsuariosDTO> obtenerUsuariosPorId(@PathVariable(name = "id") int id) {
		return ResponseEntity.ok(uSer.getById(id));
	}

	@PutMapping("/actualizar/usuario/{id}")
	public ResponseEntity<UsuariosDTO> actualizarUsuario(@RequestBody UsuariosDTO uDTO,
			@PathVariable(name = "id") int id) {
		UsuariosDTO uRespuesta = uSer.updateUsuario(uDTO, id);
		return new ResponseEntity<>(uRespuesta, HttpStatus.OK);
	}

	@DeleteMapping("eliminar/usuario/{id}")
	public ResponseEntity<String> eliminarUsuario(@PathVariable(name = "id") int id) {
		uSer.deleteUsuario(id);
		return new ResponseEntity<>("Usuario eliminado exitosamente", HttpStatus.OK);
	}
}
