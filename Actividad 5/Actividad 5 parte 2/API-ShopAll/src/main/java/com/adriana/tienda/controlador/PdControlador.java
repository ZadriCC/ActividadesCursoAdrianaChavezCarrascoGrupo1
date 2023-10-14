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

import com.adriana.tienda.datos.dto.PedidosDTO;
import com.adriana.tienda.servicio.PdServicio;

@RestController
@RequestMapping("/shopall/pedidos")
public class PdControlador {
	
	@Autowired
	private PdServicio pdServ;

	@PostMapping("/nuevo-pedido/usuario/{usuarios}")
	public ResponseEntity<PedidosDTO> addPedidos(@PathVariable(value = "usuarios") int usuarios,
			@RequestBody PedidosDTO pdDTO) {
		return new ResponseEntity<>(pdServ.addPedidos(usuarios, pdDTO), HttpStatus.CREATED);
	}
	
	@GetMapping("/mostrar-todos")
	public List<PedidosDTO> getAll() {
		return pdServ.getAll();
	}

}
