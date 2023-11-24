package com.adriana.tienda.servicio;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adriana.tienda.datos.MetodoPago;
import com.adriana.tienda.dto.MetodoPagoDTO;
import com.adriana.tienda.excepciones.RecursoNoEncontradoException;
import com.adriana.tienda.reposiorio.MPRepositorio;
import com.adriana.tienda.utilidades.Validaciones;

@Service
public class MPServicio {

	@Autowired
	private MPRepositorio mpRep;

	// convertir entidad a dto
	private MetodoPagoDTO mapearDTO(MetodoPago mp) {
		MetodoPagoDTO mpDTO = new MetodoPagoDTO();
		mpDTO.setIdMetodoPago(mp.getIdMetodoPago());
		mpDTO.setMpTipo(mp.getMpTipo());
		return mpDTO;
	}

	// convertir de DTO a Entidad
	private MetodoPago mapearEntidad(MetodoPagoDTO mpDTO) {
		MetodoPago mp = new MetodoPago();
		mp.setIdMetodoPago(mpDTO.getIdMetodoPago());
		mp.setMpTipo(mpDTO.getMpTipo());
		return mp;
	}

	public MetodoPagoDTO addMPago(MetodoPagoDTO mpDTO) {
		MetodoPago mp = mapearEntidad(mpDTO);
		Validaciones.validarCampoNuloOVacio(mpDTO.getMpTipo(), "Tipo");
		MetodoPago nuevoMP = mpRep.save(mp);
		MetodoPagoDTO mpRespuesta = mapearDTO(nuevoMP);
		return mpRespuesta;
	}

	public List<MetodoPagoDTO> getAll() {
		List<MetodoPago> lMPago = mpRep.findAll();
		return lMPago.stream().map(mp -> mapearDTO(mp)).collect(Collectors.toList());
	}

	public MetodoPagoDTO getMPagoById(int id) {
		MetodoPago mp = mpRep.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoException("Método de Pago", "id", id));
		return mapearDTO(mp);
	}

	public MetodoPagoDTO updateMPago(int id, MetodoPagoDTO mpDTO) {
		MetodoPago mp = mpRep.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoException("Método de Pago", "id", id));
		mp.setMpTipo(mpDTO.getMpTipo());
		Validaciones.validarCampoNuloOVacio(mpDTO.getMpTipo(), "Tipo");
		MetodoPago mpActualizado = mpRep.save(mp);
		return mapearDTO(mpActualizado);
	}

	public void deleteMPago(int id) {
		MetodoPago mp = mpRep.findById(id)
				.orElseThrow(() -> new RecursoNoEncontradoException("Método de Pago", "id", id));
		mpRep.delete(mp);
	}
}
