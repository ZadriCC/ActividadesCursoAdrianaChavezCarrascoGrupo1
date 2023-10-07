package com.adriana.tienda.servicio;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adriana.tienda.datos.DetallePedido;
import com.adriana.tienda.datos.Pedidos;
import com.adriana.tienda.datos.PerfilVendedor;
import com.adriana.tienda.datos.Productos;
import com.adriana.tienda.datos.dto.DetallePedidoDTO;
import com.adriana.tienda.repositorio.DPdRepositorio;
import com.adriana.tienda.repositorio.PRepositorio;
import com.adriana.tienda.repositorio.PVRepositorio;
import com.adriana.tienda.repositorio.PdRepositorio;

@Service
public class DPdServicioImpl implements DPdServicio {
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private DPdRepositorio dpdRep;

	@Autowired
	private PdRepositorio pdRep;

	@Autowired
	private PVRepositorio pvRep;

	@Autowired
	private PRepositorio pRep;

	// convertir entidad a dto
	private DetallePedidoDTO mapearDTO(DetallePedido dPedido) {
		DetallePedidoDTO dpDTO = modelMapper.map(dPedido, DetallePedidoDTO.class);
		return dpDTO;
	}

	// convertir de DTO a Entidad
	private DetallePedido mapearEntidad(DetallePedidoDTO dtuDTO) {
		DetallePedido dPedido = modelMapper.map(dtuDTO, DetallePedido.class);
		return dPedido;
	}

	@Override
	public DetallePedidoDTO addDPedido(int idPedido, int idVendedor, int idProducto, DetallePedidoDTO dpdDTO) {
		DetallePedido dPedido = mapearEntidad(dpdDTO);
		
		Pedidos pd = pdRep.findById(idPedido).orElseThrow();
		dPedido.setPedido(pd);
		PerfilVendedor pv = pvRep.findById(idVendedor).orElseThrow();
		dPedido.setVendedor(pv);
		Productos p = pRep.findById(idProducto).orElseThrow();
		dPedido.setProducto(p);
		
		DetallePedido nuevoDP = dpdRep.save(dPedido);
		return mapearDTO(nuevoDP);
	}

	@Override
	public List<DetallePedidoDTO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
