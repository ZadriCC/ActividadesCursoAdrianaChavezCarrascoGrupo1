package com.adriana.tienda.servicio;

import java.util.List;

import com.adriana.tienda.datos.dto.UsuariosDTO;

public interface UsuariosServicio {
	
	public UsuariosDTO addUsuario(UsuariosDTO uDTO);
	
	public List<UsuariosDTO> getAll();

}
