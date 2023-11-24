package com.adriana.tienda.servicio;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adriana.tienda.datos.DetallePedido;
import com.adriana.tienda.datos.Pedidos;
import com.adriana.tienda.datos.Usuarios;
import com.adriana.tienda.datos.dto.PedidosDTO;
import com.adriana.tienda.repositorio.PdRepositorio;
import com.adriana.tienda.repositorio.URepositorio;

@Service
public class PdServicioImpl implements PdServicio {
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PdRepositorio pdRep;

	@Autowired
	private URepositorio uRep;

	// convertir entidad a dto
	private PedidosDTO mapearDTO(Pedidos pedido) {
	    PedidosDTO pedidoDTO = modelMapper.map(pedido, PedidosDTO.class);
	    return pedidoDTO;
	}

	// convertir de DTO a Entidad
	private Pedidos mapearEntidad(PedidosDTO pdDTO) {
		Pedidos pd = modelMapper.map(pdDTO, Pedidos.class);
		return pd;
	}

	@Override
	public PedidosDTO addPedidos(int idUser, PedidosDTO pdDTO) {
	    Pedidos pd = mapearEntidad(pdDTO);
	    Usuarios user = uRep.findById(idUser).orElseThrow();
	    pd.setUsuarios(user);

	    pd.setPdEstado("pendiente");
	    pd.setPdNoSeguimiento("00000000000");
	    
	    int cantidadTotal = 0;
	    double precioTotal = 0.0;

	    for (DetallePedido detalle : pdDTO.getDetallePedido()) {
	        cantidadTotal += detalle.getDtpCantidad();
	        precioTotal += detalle.getDtpTotal();
	    }

	    pd.setPdSubtotal(cantidadTotal);
	    pd.setPdTotal(precioTotal);

	    Pedidos nuevoPedido = pdRep.save(pd);
	    return mapearDTO(nuevoPedido);
	}

	@Override
	public List<PedidosDTO> getAll() {
		List<Pedidos> lPedidos = pdRep.findAll();
		return lPedidos.stream().map(pd -> mapearDTO(pd)).collect(Collectors.toList());

	}

}
