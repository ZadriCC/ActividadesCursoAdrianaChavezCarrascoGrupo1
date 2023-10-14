package com.adriana.tienda.servicio;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adriana.tienda.datos.Usuarios;
import com.adriana.tienda.datos.dto.UsuariosDTO;
import com.adriana.tienda.repositorio.TRepositorio;

@Service
public class TServicioImpl implements TServicio {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private TRepositorio tRep;

	// convertir entidad a dto
	private UsuariosDTO mapearDTO(Usuarios user) {
		UsuariosDTO uDTO = modelMapper.map(user, UsuariosDTO.class);
		return uDTO;
	}

	// convertir de DTO a Entidad
	private Usuarios mapearEntidad(UsuariosDTO uDTO) {
		Usuarios user = modelMapper.map(uDTO, Usuarios.class);
		return user;
	}

}
