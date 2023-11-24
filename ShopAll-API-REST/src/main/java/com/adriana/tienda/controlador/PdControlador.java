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

import com.adriana.tienda.dto.PedidoDTO;
import com.adriana.tienda.servicio.PdServicio;

@RestController
@RequestMapping("/shopall/pedidos")
public class PdControlador {

	@Autowired
	private PdServicio pdServ;

	@PostMapping("/usuario/{idUsuario}/nuevo-pedido")
	public ResponseEntity<PedidoDTO> addPedidos(@PathVariable(value = "idUsuario") int idUsuario,
			@RequestBody PedidoDTO pdDTO) {
		return new ResponseEntity<>(pdServ.addPedido(idUsuario, pdDTO), HttpStatus.CREATED);
	}

	@GetMapping
	public List<PedidoDTO> getAll() {
		return pdServ.getAll();
	}
}
