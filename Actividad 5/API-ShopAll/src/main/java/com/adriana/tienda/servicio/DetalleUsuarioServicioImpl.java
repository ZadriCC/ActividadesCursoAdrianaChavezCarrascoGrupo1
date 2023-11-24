package com.adriana.tienda.servicio;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adriana.tienda.datos.DetalleUsuario;
import com.adriana.tienda.datos.Usuarios;
import com.adriana.tienda.datos.dto.DetalleUsuarioDTO;
import com.adriana.tienda.repositorio.DetalleUsuarioRepositorio;
import com.adriana.tienda.repositorio.UsuariosRepositorio;

@Service
public class DetalleUsuarioServicioImpl implements DetalleUsuarioServicio {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private DetalleUsuarioRepositorio dtuRep;

	@Autowired
	private UsuariosRepositorio uRep;

	@Override
	public DetalleUsuarioDTO addDTUsuario(int idUser, DetalleUsuarioDTO dtuDTO) {
		DetalleUsuario dtUser = mapearEntidad(dtuDTO);
		Usuarios user = uRep.findById(idUser).orElseThrow();
		dtUser.setUsuarios(user);
		DetalleUsuario nuevoDTUser = dtuRep.save(dtUser);
		return mapearDTO(nuevoDTUser);
	}
	
	@Override
	public List<DetalleUsuarioDTO> getAll() {
		List<DetalleUsuario> lUsuarios = dtuRep.findAll();
		return lUsuarios.stream().map(user -> mapearDTO(user)).collect(Collectors.toList());
	}
	
	// convertir entidad a dto
		private DetalleUsuarioDTO mapearDTO(DetalleUsuario dtUser) {
			DetalleUsuarioDTO dtuDTO = modelMapper.map(dtUser, DetalleUsuarioDTO.class);
			return dtuDTO;
		}

		// convertir de DTO a Entidad
		private DetalleUsuario mapearEntidad(DetalleUsuarioDTO dtuDTO) {
			DetalleUsuario dtUser = modelMapper.map(dtuDTO, DetalleUsuario.class);
			return dtUser;
		}
}
