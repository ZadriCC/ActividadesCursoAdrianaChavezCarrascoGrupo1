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

import com.adriana.tienda.dto.DetalleProductoDTO;
import com.adriana.tienda.servicio.DPServicio;

@RestController
@RequestMapping("/shopall/detalle-producto")
public class DPControlador {

	@Autowired
	private DPServicio dpServicio;

	@PostMapping("/producto/{idProducto}/agregar-detalle")
	public ResponseEntity<DetalleProductoDTO> addDetalleProducto(@PathVariable int idProducto,
			@RequestBody DetalleProductoDTO detalleProductoDTO) {
		DetalleProductoDTO nuevoDetalleProducto = dpServicio.addDProducto(idProducto, detalleProductoDTO);
		return new ResponseEntity<>(nuevoDetalleProducto, HttpStatus.CREATED);
	}

	@GetMapping("/{idDetalle}")
	public ResponseEntity<DetalleProductoDTO> getDetalleProducto(@PathVariable int idProducto,
			@PathVariable int idDetalle) {
		DetalleProductoDTO detalleProductoDTO = dpServicio.getById(idProducto, idDetalle);
		return new ResponseEntity<>(detalleProductoDTO, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<DetalleProductoDTO>> getAllDetallesProducto() {
		List<DetalleProductoDTO> detallesProducto = dpServicio.getAll();
		return new ResponseEntity<>(detallesProducto, HttpStatus.OK);
	}

	@PutMapping("/{idDetalle}")
	public ResponseEntity<DetalleProductoDTO> updateDetalleProducto(@PathVariable int idDetalle,
			@RequestBody DetalleProductoDTO detalleProductoDTO) {
		DetalleProductoDTO updatedDetalleProducto = dpServicio.updateDProducto(idDetalle, detalleProductoDTO);
		return new ResponseEntity<>(updatedDetalleProducto, HttpStatus.OK);
	}

	@DeleteMapping("/{idDetalle}")
	public ResponseEntity<String> deleteDetalleProducto(@PathVariable int idDetalle) {
		dpServicio.deleteDProducto(idDetalle);
		return new ResponseEntity<>("Detalle de producto eliminado con éxito", HttpStatus.OK);
	}
}
