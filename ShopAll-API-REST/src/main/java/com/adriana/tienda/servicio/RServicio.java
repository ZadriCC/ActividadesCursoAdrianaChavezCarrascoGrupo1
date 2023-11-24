package com.adriana.tienda.servicio;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adriana.tienda.datos.Producto;
import com.adriana.tienda.datos.Reseña;
import com.adriana.tienda.datos.Usuario;
import com.adriana.tienda.dto.ReseñaDTO;
import com.adriana.tienda.excepciones.DatoNoValidoException;
import com.adriana.tienda.excepciones.RecursoNoEncontradoException;
import com.adriana.tienda.reposiorio.PRepositorio;
import com.adriana.tienda.reposiorio.RRepositorio;
import com.adriana.tienda.reposiorio.URepositorio;

@Service
public class RServicio {
	@Autowired
	private RRepositorio rRep;

	@Autowired
	private URepositorio uRep;

	@Autowired
	private PRepositorio pRep;

	// convertir entidad a dto
	private ReseñaDTO mapearDTO(Reseña r) {
		ReseñaDTO rDTO = new ReseñaDTO();
		rDTO.setIdReseña(r.getIdReseña());
		rDTO.setrComentario(r.getrComentario());
		rDTO.setrPuntuacion(r.getrPuntuacion());
		rDTO.setrFecha(r.getrFecha());
		return rDTO;
	}

	// convertir de DTO a Entidad
	private Reseña mapearEntidad(ReseñaDTO rDTO) {
		Reseña r = new Reseña();
		r.setIdReseña(rDTO.getIdReseña());
		r.setrComentario(rDTO.getrComentario());
		r.setrPuntuacion(rDTO.getrPuntuacion());
		r.setrFecha(rDTO.getrFecha());
		return r;
	}

	public ReseñaDTO addReseña(int idUsuario, int idProducto, ReseñaDTO rDTO) {
		Reseña r = mapearEntidad(rDTO);

		Usuario user = uRep.findById(idUsuario)
				.orElseThrow(() -> new RecursoNoEncontradoException("Usuario", "id", idUsuario));
		r.setUsuario(user);
		Producto p = pRep.findById(idProducto)
				.orElseThrow(() -> new RecursoNoEncontradoException("Producto", "id", idProducto));
		r.setProducto(p);

		LocalDate fecha = LocalDate.now();
		r.setrFecha(fecha);
		if (rDTO.getrPuntuacion() <= 0 || rDTO.getrPuntuacion() > 5) {
			throw new DatoNoValidoException("La puntuación debe estar entre 1 y 5.");
		}
		Reseña nuevaR = rRep.save(r);
		return mapearDTO(nuevaR);
	}

	public List<ReseñaDTO> getAll() {
		List<Reseña> lReseñas = rRep.findAll();
		return lReseñas.stream().map(r -> mapearDTO(r)).collect(Collectors.toList());
	}

	public ReseñaDTO getById(int id) {
		Reseña reseña = rRep.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("Reseña", "id", id));
		return mapearDTO(reseña);
	}

	public ReseñaDTO updateReseña(int id, ReseñaDTO rDTO) {
		Reseña r = rRep.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("Reseña", "id", id));

		r.setrComentario(rDTO.getrComentario());
		r.setrPuntuacion(rDTO.getrPuntuacion());
		if (rDTO.getrPuntuacion() <= 0 || rDTO.getrPuntuacion() > 5) {
			throw new DatoNoValidoException("La puntuación debe estar entre 1 y 5.");
		}
		Reseña reseñaActualizada = rRep.save(r);

		return mapearDTO(reseñaActualizada);
	}

	public void deleteReseña(int id) {
		Reseña reseña = rRep.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("Reseña", "id", id));
		rRep.delete(reseña);
	}
}
