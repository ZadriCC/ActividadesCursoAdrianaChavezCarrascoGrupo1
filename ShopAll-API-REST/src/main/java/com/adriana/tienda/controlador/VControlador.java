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

import com.adriana.tienda.dto.VendedorDTO;
import com.adriana.tienda.servicio.VServicio;

@RestController
@RequestMapping("/shopall/vendedor")
public class VControlador {

	@Autowired
	private VServicio vSer;

	@PostMapping("/agregar/usuarios/{idUsuario}")
	public ResponseEntity<VendedorDTO> addVendedor(@PathVariable(value = "idUsuario") int idUsuario,
			@RequestBody VendedorDTO pvDTO) {
		return new ResponseEntity<>(vSer.addVendedor(idUsuario, pvDTO), HttpStatus.CREATED);
	}

	@GetMapping
	public List<VendedorDTO> getAll() {
		return vSer.getAll();
	}

	@GetMapping("/usuario/{idUsuario}/vendedor/{idVendedor}")
	public ResponseEntity<VendedorDTO> obtenerPVendedorPorID(@PathVariable(value = "idUsuario") int idUsuario,
			@PathVariable(value = "idVendedor") Integer idPVendedor) {
		VendedorDTO pvDTO = vSer.getById(idUsuario, idPVendedor);
		return new ResponseEntity<>(pvDTO, HttpStatus.OK);
	}

	@PutMapping("/actualizar/usuario/{idUsuario}/vendedor/{idVendedor}")
	public ResponseEntity<VendedorDTO> actualizarDTUsuario(@PathVariable(value = "idUsuario") int idUsuario,
			@PathVariable(value = "idVendedor") Integer idVendedor, @RequestBody VendedorDTO pvDTO) {
		VendedorDTO pvActualizado = vSer.updateVendedor(idUsuario, idVendedor, pvDTO);
		return new ResponseEntity<>(pvActualizado, HttpStatus.OK);
	}

	@DeleteMapping("/eliminar/usuario/{idUsuario}/vendedor/{idVendedor}")
	public ResponseEntity<String> eliminarDTUsuario(@PathVariable(value = "idUsuario") Integer idUsuario,
			@PathVariable(value = "idVendedor") Integer idVendedor) {
		vSer.deleteVendedor(idUsuario, idVendedor);
		return new ResponseEntity<>("Se eliminaron los datos con exito", HttpStatus.OK);
	}

}
