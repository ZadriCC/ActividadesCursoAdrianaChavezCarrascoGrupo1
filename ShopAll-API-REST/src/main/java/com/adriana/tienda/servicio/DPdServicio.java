package com.adriana.tienda.servicio;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adriana.tienda.datos.DetallePedido;
import com.adriana.tienda.datos.Pedido;
import com.adriana.tienda.datos.Producto;
import com.adriana.tienda.datos.Vendedor;
import com.adriana.tienda.dto.DetallePedidoDTO;
import com.adriana.tienda.dto.NotificacionDTO;
import com.adriana.tienda.excepciones.RecursoNoEncontradoException;
import com.adriana.tienda.excepciones.StockInsuficienteException;
import com.adriana.tienda.reposiorio.DPdRepositorio;
import com.adriana.tienda.reposiorio.PRepositorio;
import com.adriana.tienda.reposiorio.PdRepositorio;
import com.adriana.tienda.reposiorio.VRepositorio;
import com.adriana.tienda.utilidades.Validaciones;

@Service
public class DPdServicio {

	@Autowired
	private DPdRepositorio dpdRep;

	@Autowired
	private PdRepositorio pdRep;

	@Autowired
	private VRepositorio pvRep;

	@Autowired
	private PRepositorio pRep;

	@Autowired
	private NServicio nSer;

	// convertir entidad a dto
	private DetallePedidoDTO mapearDTO(DetallePedido dpd) {
		DetallePedidoDTO dpDTO = new DetallePedidoDTO();
		dpDTO.setIdDPedido(dpd.getIdDPedido());
		dpDTO.setDtpCantidad(dpd.getDtpCantidad());
		dpDTO.setDtpTotal(dpd.getDtpTotal());
		return dpDTO;
	}

	// convertir de DTO a Entidad
	private DetallePedido mapearEntidad(DetallePedidoDTO dtuDTO) {
		DetallePedido dpd = new DetallePedido();
		dpd.setIdDPedido(dtuDTO.getIdDPedido());
		dpd.setDtpCantidad(dtuDTO.getDtpCantidad());
		dpd.setDtpTotal(dtuDTO.getDtpTotal());
		return dpd;
	}

	public DetallePedidoDTO addDPedido(Integer idPedido, Integer idVendedor, Integer idProducto,
			DetallePedidoDTO dpdDTO) {

		Pedido pd = pdRep.findById(idPedido).orElseThrow();
		Vendedor v = pvRep.findById(idVendedor).orElseThrow();
		Producto p = pRep.findById(idProducto).orElseThrow();

		DetallePedido dpd = null;
		Validaciones.compararIDs(p.getVendedor().getIdVendedor(), idVendedor);

		// Verificar stock disponible
		int cantidadArestar = dpdDTO.getDtpCantidad();
		if (cantidadArestar <= p.getpStock()) {
			// Buscar si ya existe un detalle de pedido con un el mismo producto en el
			// pedido
			DetallePedido dpExistente = dpdRep.findByPedidoAndProducto(pd, p);

			if (dpExistente != null) {
				// Si existe, actualiza la cantidad y el precio total y el detalle pedido
				// existente
				dpExistente.setDtpCantidad(dpExistente.getDtpCantidad() + dpdDTO.getDtpCantidad());
				dpExistente.setDtpTotal(dpExistente.getDtpTotal() + (p.getpPrecio() * dpdDTO.getDtpCantidad()));
				dpdRep.save(dpExistente);
			} else {
				// Si no existe, crea un nuevo detalle de pedido
				dpd = mapearEntidad(dpdDTO);
				dpd.setPedido(pd);
				dpd.setProducto(p);
				dpd.setVendedor(v);
				pd.setPdEstado("procesando");

				dpd.setDtpTotal(p.getpPrecio() * dpdDTO.getDtpCantidad());
				dpdRep.save(dpd);
			}
			// se resta el stock y actualiza
			p.setpStock(p.getpStock() - cantidadArestar);
			pRep.save(p);
			// Calcular el precio total de los productos en los detalles de pedido asociados
			// a este pedido
			List<DetallePedido> detallesPedido = pd.getDetallePedido();
			double precioTotalProductos = 0.0;
			for (DetallePedido detallePedido : detallesPedido) {
				Producto producto = detallePedido.getProducto();
				precioTotalProductos += (producto.getpPrecio() * detallePedido.getDtpCantidad());
			}

			// Asignar el precio total calculado al campo pdSubtotal del pedido
			pd.setPdSubtotal(precioTotalProductos);
			pdRep.save(pd);
			return mapearDTO(dpExistente != null ? dpExistente : dpd);
		} else {
			throw new StockInsuficienteException("Stock insuficiente para realizar la operación.");
		}
	}

	public List<DetallePedidoDTO> getAll() {
		List<DetallePedido> lDtPedidos = dpdRep.findAll();
		return lDtPedidos.stream().map(pd -> mapearDTO(pd)).collect(Collectors.toList());
	}

	public void deleteDetallePedido(int idDetallePedido, int cantidadAEliminar) {
		DetallePedido dtp = dpdRep.findById(idDetallePedido)
				.orElseThrow(() -> new RecursoNoEncontradoException("DetallePedido", "id", idDetallePedido));

		Producto producto = dtp.getProducto();
		int cantidadEnDetalle = dtp.getDtpCantidad();

		if (cantidadAEliminar > cantidadEnDetalle || cantidadAEliminar <= 0) {
			// Manejo de excepciones o validaciones adicionales si es necesario
		}

		// Calcular el precio eliminado proporcionalmente al total original del detalle
		double precioUnitario = dtp.getDtpTotal() / cantidadEnDetalle;
		double precioEliminado = cantidadAEliminar * precioUnitario;

		// Actualizar el precio total del detalle de pedido
		dtp.setDtpCantidad(cantidadEnDetalle - cantidadAEliminar);
		dtp.setDtpTotal(dtp.getDtpTotal() - precioEliminado);
		dpdRep.save(dtp);

		// Ajustar el stock del producto sumando la cantidad eliminada
		producto.setpStock(producto.getpStock() + cantidadAEliminar);
		pRep.save(producto);
		// Actualizar el precio total del pedido si la cantidad llega a cero
		if (dtp.getDtpCantidad() == 0) {
			dpdRep.delete(dtp); // Elimina el registro del DetallePedido
			Pedido pd = dtp.getPedido();
			double nuevoPrecioPedido = calcularPrecioTotalPedido(pd);
			pd.setPdSubtotal(nuevoPrecioPedido);
			pd.setPdEstado("cancelado");
			pdRep.save(pd);
			NotificacionDTO nuevaNDTO = new NotificacionDTO();
			int idPd = pd.getIdPedido();
			nuevaNDTO.setnDescripcion("El pedido con id '" + idPd + "' se ha cancelado.");
			nSer.addNotificacion(pd.getUsuario().getIdUsuario(), nuevaNDTO);
		} else {
			Pedido pedido = dtp.getPedido();
			double nuevoPrecioPedido = calcularPrecioTotalPedido(pedido);
			pedido.setPdSubtotal(nuevoPrecioPedido);
			pdRep.save(pedido);
		}
	}

	private double calcularPrecioTotalPedido(Pedido pedido) {
		double precioTotalPedido = 0.0;
		List<DetallePedido> detallesPedido = pedido.getDetallePedido();

		for (DetallePedido detalle : detallesPedido) {
			precioTotalPedido += detalle.getDtpTotal();
		}

		return precioTotalPedido;
	}
}
