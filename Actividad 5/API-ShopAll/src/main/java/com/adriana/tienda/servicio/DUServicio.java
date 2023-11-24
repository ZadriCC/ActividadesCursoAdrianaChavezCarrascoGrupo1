package com.adriana.tienda.servicio;

import java.util.List;

import com.adriana.tienda.datos.dto.DetalleUsuarioDTO;

public interface DUServicio {

	public DetalleUsuarioDTO addDTUsuario(int idUser, DetalleUsuarioDTO dtuDTO);

	public List<DetalleUsuarioDTO> getAll();

	public DetalleUsuarioDTO getById(Integer idUser, Integer dtUser);

	public DetalleUsuarioDTO updateDTUsuario(Integer idUser, Integer dtUser, DetalleUsuarioDTO dtuDTO);

	public void deleteDTUsuario(Integer idUsuario, Integer idDUsuario);
}
