package com.adriana.tienda.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adriana.tienda.dto.DetallePedidoDTO;
import com.adriana.tienda.servicio.DPdServicio;

@RestController
@RequestMapping("/shopall/detalle-pedido")
public class DPdControlador {

	@Autowired
	private DPdServicio dpdServ;

	@PostMapping("/agregar/pedido/{pedido}/vendedor/{vendedor}/producto/{producto}")
	public ResponseEntity<DetallePedidoDTO> addDPedido(@PathVariable(value = "pedido") int pedido,
			@PathVariable(value = "vendedor") int vendedor, @PathVariable(value = "producto") int producto,
			@RequestBody DetallePedidoDTO dpdDTO) {
		return new ResponseEntity<>(dpdServ.addDPedido(pedido, vendedor, producto, dpdDTO), HttpStatus.CREATED);
	}

	@DeleteMapping("/eliminar/{idDetallePedido}")
	public ResponseEntity<String> deleteQuantityDetallePedido(@PathVariable("idDetallePedido") int idDetallePedido,
			@RequestParam("cantidad") int cantidadAEliminar) {
		dpdServ.deleteDetallePedido(idDetallePedido, cantidadAEliminar);
		return ResponseEntity.ok("Cantidad eliminada del detalle de pedido y stock actualizado.");
	}
}
