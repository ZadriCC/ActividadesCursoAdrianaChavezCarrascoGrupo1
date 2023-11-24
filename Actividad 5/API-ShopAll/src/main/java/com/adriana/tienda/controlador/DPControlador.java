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

import com.adriana.tienda.datos.dto.DetalleProductoDTO;
import com.adriana.tienda.servicio.DPServicio;

@RestController
@RequestMapping("/shopall/detalle-producto")
public class DPControlador {

	@Autowired
	private DPServicio dpServ;

	@PostMapping("/productos/{productos}")
	public ResponseEntity<DetalleProductoDTO> addDTUsuario(@PathVariable(value = "productos") int productos,
			@RequestBody DetalleProductoDTO dpDTO) {
		return new ResponseEntity<>(dpServ.addDProducto(productos, dpDTO), HttpStatus.CREATED);
	}

	@GetMapping("/mostrar-todos")
	public List<DetalleProductoDTO> getAll() {
		return dpServ.getAll();
	}

}
