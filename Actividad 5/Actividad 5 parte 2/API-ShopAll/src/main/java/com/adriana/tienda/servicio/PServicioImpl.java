package com.adriana.tienda.servicio;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adriana.tienda.datos.Productos;
import com.adriana.tienda.datos.Subcategoria;
import com.adriana.tienda.datos.dto.ProductosDTO;
import com.adriana.tienda.repositorio.PRepositorio;

@Service
public class PServicioImpl implements PServicio {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PRepositorio pRep;

	// convertir entidad a dto
	private ProductosDTO mapearDTO(Productos p) {
		ProductosDTO pDTO = modelMapper.map(p, ProductosDTO.class);
		return pDTO;
	}

	// convertir de DTO a Entidad
	private Productos mapearEntidad(ProductosDTO pDTO) {
		Productos p = modelMapper.map(pDTO, Productos.class);
		return p;
	}

	@Override
	public ProductosDTO addProductos(ProductosDTO pDTO) {
		Productos p = mapearEntidad(pDTO);
		Subcategoria subcategoria = pDTO.getSubcategoria();
		p.setSubcategoria(subcategoria);
		Productos nuevoP = pRep.save(p);
		ProductosDTO pRespuesta = mapearDTO(nuevoP);
		return pRespuesta;
	}

	@Override
	public List<ProductosDTO> getAll() {
		List<Productos> lProductos = pRep.findAll();
		return lProductos.stream().map(p -> mapearDTO(p)).collect(Collectors.toList());
	}

}
