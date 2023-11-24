package com.adriana.tienda.servicio;

import java.util.List;

import com.adriana.tienda.datos.dto.PerfilVendedorDTO;

public interface PVServicio {
	
	public PerfilVendedorDTO addPerfilVendedor(int idUsuario, PerfilVendedorDTO pvDTO);

	public List<PerfilVendedorDTO> getAll();

	public PerfilVendedorDTO getById(Integer idUsuario, Integer idPVendedor);

	public PerfilVendedorDTO updatePVendedor(Integer idUsuario, Integer idPVendedor, PerfilVendedorDTO pvDTO);

	public void deletePVendedor(Integer idUsuario, Integer idVendedor);

}
