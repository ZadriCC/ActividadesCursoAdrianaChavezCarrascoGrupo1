package com.adriana.tienda.servicio;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adriana.tienda.datos.Productos;
import com.adriana.tienda.datos.Reseñas;
import com.adriana.tienda.datos.Usuarios;
import com.adriana.tienda.datos.dto.ReseñasDTO;
import com.adriana.tienda.repositorio.PRepositorio;
import com.adriana.tienda.repositorio.RRepositorio;
import com.adriana.tienda.repositorio.URepositorio;

@Service
public class RServicioImpl implements RServicio {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private RRepositorio rRep;

	@Autowired
	private URepositorio uRep;

	@Autowired
	private PRepositorio pRep;

	// convertir entidad a dto
	private ReseñasDTO mapearDTO(Reseñas r) {
		ReseñasDTO rDTO = modelMapper.map(r, ReseñasDTO.class);
		return rDTO;
	}

	// convertir de DTO a Entidad
	private Reseñas mapearEntidad(ReseñasDTO rDTO) {
		Reseñas r = modelMapper.map(rDTO, Reseñas.class);
		return r;
	}

	@Override
	public ReseñasDTO addReseña(int idUsuario, int idProducto, ReseñasDTO rDTO) {
		Reseñas r = mapearEntidad(rDTO);
		Usuarios user = uRep.findById(idUsuario).orElseThrow();
		r.setUsuarios(user);
		Productos p = pRep.findById(idProducto).orElseThrow();
		r.setProductos(p);
		ZoneId zonaHoraria = ZoneId.of("America/Mexico_City");
		LocalDateTime fechaHoraActual = LocalDateTime.now(zonaHoraria);
		Date fechaHora = Date.from(fechaHoraActual.atZone(zonaHoraria).toInstant());
		r.setrFecha(fechaHora);
		Reseñas nuevaR = rRep.save(r);
		return mapearDTO(nuevaR);
	}

	@Override
	public List<ReseñasDTO> getAll() {
		List<Reseñas> lReseñas = rRep.findAll();
		return lReseñas.stream().map(r -> mapearDTO(r)).collect(Collectors.toList());
	}
}
