package com.adriana.tienda.servicio;

import java.util.List;

import com.adriana.tienda.datos.dto.PerfilVendedorDTO;

public interface PVServicio {
	public PerfilVendedorDTO addPerfilVendedor(int idUser, PerfilVendedorDTO perfilVendedorDTO);

	public List<PerfilVendedorDTO> getAll();

}
