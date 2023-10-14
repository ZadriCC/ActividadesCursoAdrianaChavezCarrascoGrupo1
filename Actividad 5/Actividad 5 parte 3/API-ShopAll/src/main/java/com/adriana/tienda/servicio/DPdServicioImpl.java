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
import com.adriana.tienda.excepciones.StockInsuficienteException;
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
		
		Pedidos pd = pdRep.findById(idPedido).orElseThrow();
		PerfilVendedor pv = pvRep.findById(idVendedor).orElseThrow();
		Productos p = pRep.findById(idProducto).orElseThrow();

		DetallePedido dPedido = null;

		// Verificar stock disponible
		int cantidadArestar = dpdDTO.getDtpCantidad();
		if (cantidadArestar <= p.getpStock()) {
			// Buscar si ya existe un detalle de pedido con un el mismo producto en el pedido
			DetallePedido dpExistente = dpdRep.findByPedidoAndProducto(pd, p);

			if (dpExistente != null) {
				// Si existe, actualiza la cantidad y el precio total y el detalle pedido existente
				dpExistente.setDtpCantidad(dpExistente.getDtpCantidad() + dpdDTO.getDtpCantidad());
				dpExistente.setDtpTotal(dpExistente.getDtpTotal() + (p.getpPrecio() * dpdDTO.getDtpCantidad()));
				dpdRep.save(dpExistente);
			} else {
				// Si no existe, crea un nuevo detalle de pedido
				dPedido = mapearEntidad(dpdDTO);
				dPedido.setPedido(pd);
				dPedido.setProducto(p);
				dPedido.setVendedor(pv);
				
				dPedido.setDtpTotal(p.getpPrecio() * dpdDTO.getDtpCantidad());
				dpdRep.save(dPedido);
			}
			// se resta el stock y actualiza
			p.setpStock(p.getpStock() - cantidadArestar);
			pRep.save(p);

			return mapearDTO(dpExistente != null ? dpExistente : dPedido);
		} else {
			throw new StockInsuficienteException("Stock insuficiente para realizar la operación.");
		}
	}

	@Override
	public List<DetallePedidoDTO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
