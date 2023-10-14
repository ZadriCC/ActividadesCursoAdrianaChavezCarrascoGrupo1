package com.adriana.tienda.servicio;

import java.util.List;

import com.adriana.tienda.datos.dto.MetodoEnvioDTO;

public interface MEServicio {

	public MetodoEnvioDTO addMEnvio(MetodoEnvioDTO meDTO);

	public List<MetodoEnvioDTO> getAll();

}
