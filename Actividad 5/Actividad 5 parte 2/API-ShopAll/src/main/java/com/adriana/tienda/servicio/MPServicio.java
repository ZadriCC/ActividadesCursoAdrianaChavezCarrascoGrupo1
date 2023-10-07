package com.adriana.tienda.servicio;

import java.util.List;

import com.adriana.tienda.datos.dto.MetodoPagoDTO;

public interface MPServicio {

	public MetodoPagoDTO addMPago(MetodoPagoDTO mpDTO);

	public List<MetodoPagoDTO> getAll();
}
