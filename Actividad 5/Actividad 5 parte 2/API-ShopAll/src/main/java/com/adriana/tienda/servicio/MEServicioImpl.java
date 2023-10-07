package com.adriana.tienda.servicio;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adriana.tienda.datos.MetodoEnvio;
import com.adriana.tienda.datos.dto.MetodoEnvioDTO;
import com.adriana.tienda.repositorio.MERepositorio;

@Service
public class MEServicioImpl implements MEServicio {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private MERepositorio meRep;

	// convertir entidad a dto
	private MetodoEnvioDTO mapearDTO(MetodoEnvio me) {
		MetodoEnvioDTO meDTO = modelMapper.map(me, MetodoEnvioDTO.class);
		return meDTO;
	}

	// convertir de DTO a Entidad
	private MetodoEnvio mapearEntidad(MetodoEnvioDTO meDTO) {
		MetodoEnvio me = modelMapper.map(meDTO, MetodoEnvio.class);
		return me;
	}

	@Override
	public MetodoEnvioDTO addMEnvio(MetodoEnvioDTO meDTO) {
		MetodoEnvio me = mapearEntidad(meDTO);
		MetodoEnvio nuevoME = meRep.save(me);
		MetodoEnvioDTO meRespuesta = mapearDTO(nuevoME);
		return meRespuesta;
	}

	@Override
	public List<MetodoEnvioDTO> getAll() {
		List<MetodoEnvio> lMEnvio = meRep.findAll();
		return lMEnvio.stream().map(me -> mapearDTO(me)).collect(Collectors.toList());
	}

}
