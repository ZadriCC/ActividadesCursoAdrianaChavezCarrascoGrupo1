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

import com.adriana.tienda.dto.MetodoEnvioDTO;
import com.adriana.tienda.servicio.MEServicio;

@RestController
@RequestMapping("/shopall/metodo-envio")
public class MEControlador {

	@Autowired
	private MEServicio meSer;

	@PostMapping("/agregar")
	public ResponseEntity<MetodoEnvioDTO> addMEnvio(@RequestBody MetodoEnvioDTO meDTO) {
		return new ResponseEntity<>(meSer.addMEnvio(meDTO), HttpStatus.CREATED);
	}

	@GetMapping
	public List<MetodoEnvioDTO> getAll() {
		return meSer.getAll();
	}

	@GetMapping("/{idMEnvio}")
	public ResponseEntity<MetodoEnvioDTO> getMEnvioById(@PathVariable int idMEnvio) {
		MetodoEnvioDTO mp = meSer.getMEnvioById(idMEnvio);
		return new ResponseEntity<>(mp, HttpStatus.OK);
	}

	@PutMapping("/actualizar/{idMEnvio}")
	public ResponseEntity<MetodoEnvioDTO> updateMEnvio(@PathVariable int idMEnvio, @RequestBody MetodoEnvioDTO meDTO) {
		MetodoEnvioDTO mpActualizado = meSer.updateMEnvio(idMEnvio, meDTO);
		return new ResponseEntity<>(mpActualizado, HttpStatus.OK);
	}

	@DeleteMapping("/eliminar/{idMEnvio}")
	public ResponseEntity<String> deleteMPago(@PathVariable(name = "idMEnvio") int idMEnvio) {
		meSer.deleteMEnvio(idMEnvio);
		return new ResponseEntity<>("Metodo de Envio eliminado exitosamente.", HttpStatus.OK);
	}
}
