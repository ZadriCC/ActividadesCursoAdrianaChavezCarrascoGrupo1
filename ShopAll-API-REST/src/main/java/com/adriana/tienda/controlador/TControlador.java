package com.adriana.tienda.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adriana.tienda.dto.TransaccionDTO;
import com.adriana.tienda.servicio.TServicio;

@RestController
@RequestMapping("/shopall/transacciones")
public class TControlador {

	@Autowired
	private TServicio tSer;

	@PostMapping("/pedido/{idPedido}/metodoPago/{idMPago}/metodoEnvio/{idMEnvio}")
	public ResponseEntity<TransaccionDTO> addTranaccion(@PathVariable(value = "idPedido") int idPedido,
			@PathVariable(value = "idMPago") int idMPago, @PathVariable(value = "idMEnvio") int idMEnvio,
			//@RequestParam(value = "direccion", required = false) Integer direccion,
			@RequestBody TransaccionDTO tDTO) {
		return new ResponseEntity<>(tSer.addTransaccion(idPedido, idMPago, idMEnvio, tDTO),
				HttpStatus.CREATED);
	}

}
