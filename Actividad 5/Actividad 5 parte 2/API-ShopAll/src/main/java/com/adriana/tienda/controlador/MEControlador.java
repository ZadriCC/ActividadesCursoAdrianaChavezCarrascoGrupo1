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

import com.adriana.tienda.datos.dto.MetodoEnvioDTO;
import com.adriana.tienda.servicio.MEServicio;

@RestController
@RequestMapping("/shopall/metodo-envio")
public class MEControlador {

	@Autowired
	private MEServicio meSer;

	@PostMapping("/agregar-metodo-envio")
	public ResponseEntity<MetodoEnvioDTO> addMEnvio(@RequestBody MetodoEnvioDTO meDTO) {
		return new ResponseEntity<>(meSer.addMEnvio(meDTO), HttpStatus.CREATED);
	}

	@GetMapping("/mostrar-todos")
	public List<MetodoEnvioDTO> getAll() {
		return meSer.getAll();
	}

}
