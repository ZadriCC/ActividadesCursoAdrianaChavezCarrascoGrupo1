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

import com.adriana.tienda.datos.dto.MetodoPagoDTO;
import com.adriana.tienda.servicio.MPServicio;

@RestController
@RequestMapping("/shopall/metodo-pago")
public class MPControlador {

	@Autowired
	private MPServicio mpSer;

	@PostMapping("/agregar-metodo-pago")
	public ResponseEntity<MetodoPagoDTO> addMPago(@RequestBody MetodoPagoDTO mpDTO) {
		return new ResponseEntity<>(mpSer.addMPago(mpDTO), HttpStatus.CREATED);
	}

	@GetMapping("/mostrar-todos")
	public List<MetodoPagoDTO> getAll() {
		return mpSer.getAll();
	}

}
