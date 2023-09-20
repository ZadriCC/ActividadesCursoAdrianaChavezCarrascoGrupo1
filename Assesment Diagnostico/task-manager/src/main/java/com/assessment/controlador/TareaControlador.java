package com.assessment.controlador;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.dto.TareaDTO;
import com.assessment.dto.TareaRespuesta;
import com.assessment.servicio.TareaServicio;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tareas")
public class TareaControlador {

	@Autowired
	private TareaServicio tareaServicio;

	@GetMapping
	public TareaRespuesta listarTareas(@RequestParam(value = "pageNo", defaultValue = "0", required = false) int noPage,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) int medidaPagina) {
		return tareaServicio.obtenerTareas(noPage,medidaPagina);
	}

	@GetMapping("/{id}")
	public ResponseEntity<TareaDTO> obtenerTareaId(@PathVariable(name = "id") long id) {
		return ResponseEntity.ok(tareaServicio.obtenerPorId(id));
	}

	@PostMapping
	public ResponseEntity<TareaDTO> guardarTarea(@Valid @RequestBody TareaDTO tareaDTO) {
		return new ResponseEntity<>(tareaServicio.crearTarea(tareaDTO), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<TareaDTO> actualizarTarea(@PathVariable(name = "id") long id,@Valid
			@RequestBody TareaDTO tareaDTO) {
		TareaDTO tRespuesta = tareaServicio.actualizarTarea(tareaDTO, id);
		return new ResponseEntity<>(tRespuesta, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminarTarea(@PathVariable(name = "id") long id) {
		tareaServicio.eliminarTarea(id);
		return new ResponseEntity<>("Tarea eliminada con exito", HttpStatus.OK);
	}

}
