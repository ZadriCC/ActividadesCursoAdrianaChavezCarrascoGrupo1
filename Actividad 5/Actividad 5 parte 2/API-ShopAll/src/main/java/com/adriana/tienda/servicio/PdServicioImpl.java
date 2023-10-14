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
	private PedidosDTO mapearDTO(Pedidos pd) {
		PedidosDTO pdDTO = modelMapper.map(pd, PedidosDTO.class);
		return pdDTO;
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
		
		// Cálculo de cantidad y total de productos en el pedido
		int cantidadTotal = 0;
		double total = 0.0;

		for (DetallePedido detalle : pdDTO.getDetallePedido()) {
			cantidadTotal += detalle.getDtpCantidad();
			total += detalle.getDtpTotal();
		}

		pd.setPdSubtotal(cantidadTotal); // Suponiendo que existe un campo 'cantidadTotal' en la entidad Pedidos
		pd.setPdTotal(total); // Suponiendo que existe un campo 'total' en la entidad Pedidos

		Pedidos nuevoPedido = pdRep.save(pd);
		return mapearDTO(nuevoPedido);
	}

	@Override
	public List<PedidosDTO> getAll() {
		List<Pedidos> lPedidos = pdRep.findAll();
		return lPedidos.stream().map(pd -> mapearDTO(pd)).collect(Collectors.toList());

	}

}
