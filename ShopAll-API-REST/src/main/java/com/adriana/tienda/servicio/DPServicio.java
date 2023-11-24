package com.adriana.tienda.servicio;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adriana.tienda.datos.DetalleProducto;
import com.adriana.tienda.datos.Producto;
import com.adriana.tienda.dto.DetalleProductoDTO;
import com.adriana.tienda.excepciones.ErrorAppException;
import com.adriana.tienda.excepciones.RecursoNoEncontradoException;
import com.adriana.tienda.reposiorio.DPRepositorio;
import com.adriana.tienda.reposiorio.PRepositorio;

@Service
public class DPServicio {

	@Autowired
	private DPRepositorio dpRep;

	@Autowired
	private PRepositorio pRep;

	// convertir entidad a dto
	private DetalleProductoDTO mapearDTO(DetalleProducto dtProducto) {
		DetalleProductoDTO dpDTO = new DetalleProductoDTO();
		dpDTO.setIdDProducto(dtProducto.getIdDProducto());
		dpDTO.setDpAtributo(dtProducto.getDpAtributo());
		dpDTO.setDpValor(dtProducto.getDpValor());
		return dpDTO;
	}

	// convertir de DTO a Entidad
	private DetalleProducto mapearEntidad(DetalleProductoDTO dpDTO) {
		DetalleProducto dtProducto = new DetalleProducto();
		dtProducto.setIdDProducto(dpDTO.getIdDProducto());
		dtProducto.setDpAtributo(dpDTO.getDpAtributo());
		dtProducto.setDpValor(dpDTO.getDpValor());
		return dtProducto;
	}

	public DetalleProductoDTO addDProducto(int idProducto, DetalleProductoDTO dpDTO) {
		DetalleProducto dtProducto = mapearEntidad(dpDTO);
		Producto p = pRep.findById(idProducto)
				.orElseThrow(() -> new RecursoNoEncontradoException("Detalle producto", "id", idProducto));
		dtProducto.setProducto(p);
		DetalleProducto nuevoDTProducto = dpRep.save(dtProducto);
		return mapearDTO(nuevoDTProducto);
	}

	public List<DetalleProductoDTO> getAll() {
		List<DetalleProducto> lDProductos = dpRep.findAll();
		return lDProductos.stream().map(dtProducto -> mapearDTO(dtProducto)).collect(Collectors.toList());
	}

	public DetalleProductoDTO getById(Integer idProducto, Integer idDProducto) {
		Producto p = pRep.findById(idProducto)
				.orElseThrow(() -> new RecursoNoEncontradoException("Producto", "id", idProducto));
		DetalleProducto dp = dpRep.findById(idDProducto)
				.orElseThrow(() -> new RecursoNoEncontradoException("Detalle de producto", "id", idDProducto));
		if (dp.getProducto().getIdProducto() != p.getIdProducto()) {
			throw new ErrorAppException(
					"IDs incorrectos: " + dp.getProducto().getIdProducto() + " y " + p.getIdProducto());
		}
		return mapearDTO(dp);
	}

	public DetalleProductoDTO updateDProducto(Integer idDProducto, DetalleProductoDTO dpDTO) {
		DetalleProducto dp = dpRep.findById(idDProducto)
				.orElseThrow(() -> new RecursoNoEncontradoException("Detalle de producto", "id", idDProducto));
		dp.setDpAtributo(dpDTO.getDpAtributo());
		dp.setDpValor(dpDTO.getDpValor());
		DetalleProducto dpActualizado = dpRep.save(dp);
		return mapearDTO(dpActualizado);
	}

	public void deleteDProducto(Integer idDProducto) {
		DetalleProducto dp = dpRep.findById(idDProducto)
				.orElseThrow(() -> new RecursoNoEncontradoException("Detalle de producto", "id", idDProducto));
		dpRep.delete(dp);

	}
}
