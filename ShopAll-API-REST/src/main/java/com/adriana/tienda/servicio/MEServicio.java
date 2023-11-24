package com.adriana.tienda.servicio;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adriana.tienda.datos.MetodoEnvio;
import com.adriana.tienda.dto.MetodoEnvioDTO;
import com.adriana.tienda.excepciones.RecursoNoEncontradoException;
import com.adriana.tienda.reposiorio.MERepositorio;
import com.adriana.tienda.utilidades.Validaciones;

@Service
public class MEServicio {

	@Autowired
	private MERepositorio meRep;

	// convertir entidad a dto
	private MetodoEnvioDTO mapearDTO(MetodoEnvio me) {
		MetodoEnvioDTO meDTO = new MetodoEnvioDTO();
		meDTO.setIdMetodoEnvio(me.getIdMetodoEnvio());
		meDTO.setMeTipo(me.getMeTipo());
		meDTO.setMePrecio(me.getMePrecio());
		return meDTO;
	}

	// convertir de DTO a Entidad
	private MetodoEnvio mapearEntidad(MetodoEnvioDTO meDTO) {
		MetodoEnvio me = new MetodoEnvio();
		me.setIdMetodoEnvio(meDTO.getIdMetodoEnvio());
		me.setMeTipo(meDTO.getMeTipo());
		me.setMePrecio(meDTO.getMePrecio());
		return me;
	}

	public MetodoEnvioDTO addMEnvio(MetodoEnvioDTO meDTO) {
		MetodoEnvio me = mapearEntidad(meDTO);
		Validaciones.validarCampoNuloOVacio(meDTO.getMeTipo(), "Tipo");
		MetodoEnvio nuevoME = meRep.save(me);
		MetodoEnvioDTO meRespuesta = mapearDTO(nuevoME);
		return meRespuesta;
	}

	public List<MetodoEnvioDTO> getAll() {
		List<MetodoEnvio> lMEnvio = meRep.findAll();
		return lMEnvio.stream().map(me -> mapearDTO(me)).collect(Collectors.toList());
	}

	public MetodoEnvioDTO getMEnvioById(int id) {
		MetodoEnvio me = meRep.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoException("Método de Envio", "id", id));
		return mapearDTO(me);
	}

	public MetodoEnvioDTO updateMEnvio(int id, MetodoEnvioDTO meDTO) {
		MetodoEnvio me = meRep.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoException("Método de Envio", "id", id));
		me.setMeTipo(meDTO.getMeTipo());
		me.setMePrecio(meDTO.getMePrecio());
		Validaciones.validarCampoNuloOVacio(meDTO.getMeTipo(), "Tipo");
		MetodoEnvio meActualizado = meRep.save(me);
		return mapearDTO(meActualizado);
	}

	public void deleteMEnvio(int id) {
		MetodoEnvio me = meRep.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoException("Método de Envio", "id", id));
		meRep.delete(me);
	}

}
