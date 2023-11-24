package com.adriana.tienda.servicio;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adriana.tienda.datos.MetodoPago;
import com.adriana.tienda.datos.dto.MetodoPagoDTO;
import com.adriana.tienda.repositorio.MPRepositorio;

@Service
public class MPServicioImpl implements MPServicio {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private MPRepositorio mpRep;

	// convertir entidad a dto
	private MetodoPagoDTO mapearDTO(MetodoPago mp) {
		MetodoPagoDTO mpDTO = modelMapper.map(mp, MetodoPagoDTO.class);
		return mpDTO;
	}

	// convertir de DTO a Entidad
	private MetodoPago mapearEntidad(MetodoPagoDTO mpDTO) {
		MetodoPago mp = modelMapper.map(mpDTO, MetodoPago.class);
		return mp;
	}

	@Override
	public MetodoPagoDTO addMPago(MetodoPagoDTO mpDTO) {
		MetodoPago mp = mapearEntidad(mpDTO);
		MetodoPago nuevoMP = mpRep.save(mp);
		MetodoPagoDTO mpRespuesta = mapearDTO(nuevoMP);
		return mpRespuesta;
	}

	@Override
	public List<MetodoPagoDTO> getAll() {
		List<MetodoPago> lMPago = mpRep.findAll();
		return lMPago.stream().map(mp -> mapearDTO(mp)).collect(Collectors.toList());
	}

}
