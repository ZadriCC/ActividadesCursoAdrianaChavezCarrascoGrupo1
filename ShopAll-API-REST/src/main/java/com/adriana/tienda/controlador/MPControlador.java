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

import com.adriana.tienda.dto.MetodoPagoDTO;
import com.adriana.tienda.servicio.MPServicio;

@RestController
@RequestMapping("/shopall/metodo-pago")
public class MPControlador {

	@Autowired
	private MPServicio mpSer;

	@PostMapping("/agregar")
	public ResponseEntity<MetodoPagoDTO> addMPago(@RequestBody MetodoPagoDTO mpDTO) {
		return new ResponseEntity<>(mpSer.addMPago(mpDTO), HttpStatus.CREATED);
	}

	@GetMapping
	public List<MetodoPagoDTO> getAll() {
		return mpSer.getAll();
	}

	@GetMapping("/{idMPago}")
	public ResponseEntity<MetodoPagoDTO> getMPagoById(@PathVariable int idMPago) {
		MetodoPagoDTO mp = mpSer.getMPagoById(idMPago);
		return new ResponseEntity<>(mp, HttpStatus.OK);
	}

	@PutMapping("/actualizar/{idMPago}")
	public ResponseEntity<MetodoPagoDTO> updateMPago(@PathVariable int idMPago, @RequestBody MetodoPagoDTO mpDTO) {
		MetodoPagoDTO mpActualizado = mpSer.updateMPago(idMPago, mpDTO);
		return new ResponseEntity<>(mpActualizado, HttpStatus.OK);
	}

	@DeleteMapping("/eliminar/{idMPago}")
	public ResponseEntity<String> deleteMPago(@PathVariable(name = "idMPago") int idMPago) {
		mpSer.deleteMPago(idMPago);
		return new ResponseEntity<>("Metodo de Pago eliminado exitosamente.", HttpStatus.OK);
	}

}
