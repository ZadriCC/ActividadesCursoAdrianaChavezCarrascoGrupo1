package com.adriana.tienda.servicio;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adriana.tienda.datos.PerfilVendedor;
import com.adriana.tienda.datos.Usuarios;
import com.adriana.tienda.datos.dto.PerfilVendedorDTO;
import com.adriana.tienda.repositorio.PVRepositorio;
import com.adriana.tienda.repositorio.URepositorio;

@Service
public class PVServicioImpl implements PVServicio {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PVRepositorio pvRep;

	@Autowired
	private URepositorio uRep;

	// convertir entidad a dto
	private PerfilVendedorDTO mapearDTO(PerfilVendedor pv) {
		PerfilVendedorDTO pvDTO = modelMapper.map(pv, PerfilVendedorDTO.class);
		return pvDTO;
	}

	// convertir de DTO a Entidad
	private PerfilVendedor mapearEntidad(PerfilVendedorDTO pvDTO) {
		PerfilVendedor pv = modelMapper.map(pvDTO, PerfilVendedor.class);
		return pv;
	}

	@Override
	public PerfilVendedorDTO addPerfilVendedor(int idUser, PerfilVendedorDTO perfilVendedorDTO) {
		PerfilVendedor pv = mapearEntidad(perfilVendedorDTO);
		Usuarios user = uRep.findById(idUser).orElseThrow();
		pv.setVendedor(user);
		PerfilVendedor nuevoPV = pvRep.save(pv);
		PerfilVendedorDTO pvRespuesta = mapearDTO(nuevoPV);
		return pvRespuesta;
	}

	@Override
	public List<PerfilVendedorDTO> getAll() {
		List<PerfilVendedor> lPVendedor = pvRep.findAll();
		return lPVendedor.stream().map(pv -> mapearDTO(pv)).collect(Collectors.toList());
	}

}
