package com.adriana.tienda.servicio;

import java.util.List;

import com.adriana.tienda.datos.dto.UsuariosDTO;

public interface UServicio {

	public UsuariosDTO addUsuario(UsuariosDTO uDTO);

	public List<UsuariosDTO> getAll();

	public UsuariosDTO getById(int idUsuario);

	public UsuariosDTO updateUsuario(UsuariosDTO uDTO, int idUsuario);

	public void deleteUsuario(int idUsuario);

}
