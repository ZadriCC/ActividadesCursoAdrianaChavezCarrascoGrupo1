package com.assessment.servicio;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.assessment.dto.TareaDTO;
import com.assessment.dto.TareaRespuesta;
import com.assessment.entidades.Tarea;
import com.assessment.excepciones.ResourceNotFoundException;
import com.assessment.repositorio.TareaRepositorio;

@Service
public class TareaServicioImpl implements TareaServicio {
	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private TareaRepositorio tareaRepositorio;

	@Override
	public TareaDTO crearTarea(TareaDTO tareaDTO) {
		Tarea t = mapearEntidad(tareaDTO);
		Tarea nuevaTarea = tareaRepositorio.save(t);

		TareaDTO tRespuesta = mapearDTO(nuevaTarea);
		return tRespuesta;
	}

	@Override
	public TareaRespuesta obtenerTareas(int numeroPagina, int medidaPagina) {
		Pageable pageble = PageRequest.of(numeroPagina, medidaPagina);
		Page<Tarea> tareas = tareaRepositorio.findAll(pageble);
		List<Tarea> tLista = tareas.getContent();
		List<TareaDTO> contenido = tLista.stream().map(tarea -> mapearDTO(tarea)).collect(Collectors.toList());
		TareaRespuesta tRes = new TareaRespuesta();
		tRes.setContenido(contenido);
		tRes.setNoPagina(tareas.getNumber());
		tRes.setMedidaPag(tareas.getSize());
		tRes.setTotalEle(tareas.getTotalElements());
		tRes.setTotalPag(tareas.getTotalPages());
		tRes.setUltima(tareas.isLast());
		return tRes;
	}

	// convertir entidad a dto
	private TareaDTO mapearDTO(Tarea t) {
		TareaDTO tDTO= modelMapper.map(t, TareaDTO.class);
		return tDTO;
	}

	// convertie de DTO a Entidad
	private Tarea mapearEntidad(TareaDTO tDTO) {
		Tarea t = modelMapper.map(tDTO, Tarea.class);
		return t;
	}

	@Override
	public TareaDTO obtenerPorId(long id) {
		Tarea t = tareaRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tarea", "id", id));
		return mapearDTO(t);
	}

	@Override
	public TareaDTO actualizarTarea(TareaDTO tDTO, long id) {
		Tarea t = tareaRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tarea", "id", id));
		t.setDescripcion(tDTO.getDescripcion());
		t.setStatus(tDTO.getStatus());
		Tarea tActual = tareaRepositorio.save(t);
		return mapearDTO(tActual);
	}

	@Override
	public void eliminarTarea(long id) {
		Tarea t = tareaRepositorio.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tarea", "id", id));
		tareaRepositorio.delete(t);
	}

}
