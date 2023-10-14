package com.adriana.tienda.servicio;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adriana.tienda.datos.DetalleProducto;
import com.adriana.tienda.datos.Productos;
import com.adriana.tienda.datos.dto.DetalleProductoDTO;
import com.adriana.tienda.repositorio.DPRepositorio;
import com.adriana.tienda.repositorio.PRepositorio;

@Service
public class DPServicioImpl implements DPServicio {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private DPRepositorio dpRep;

	@Autowired
	private PRepositorio pRep;

	// convertir entidad a dto
	private DetalleProductoDTO mapearDTO(DetalleProducto dtProducto) {
		DetalleProductoDTO dpDTO = modelMapper.map(dtProducto, DetalleProductoDTO.class);
		return dpDTO;
	}

	// convertir de DTO a Entidad
	private DetalleProducto mapearEntidad(DetalleProductoDTO dpDTO) {
		DetalleProducto dtProducto = modelMapper.map(dpDTO, DetalleProducto.class);
		return dtProducto;
	}

	@Override
	public DetalleProductoDTO addDProducto(int producto, DetalleProductoDTO dpDTO) {
		DetalleProducto dtProducto = mapearEntidad(dpDTO);
		Productos p = pRep.findById(producto).orElseThrow();
		dtProducto.setProductos(p);
		DetalleProducto nuevoDTProducto = dpRep.save(dtProducto);
		return mapearDTO(nuevoDTProducto);
	}

	@Override
	public List<DetalleProductoDTO> getAll() {
		List<DetalleProducto> lDProducto = dpRep.findAll();
		return lDProducto.stream().map(dtProducto -> mapearDTO(dtProducto)).collect(Collectors.toList());
	}
}
