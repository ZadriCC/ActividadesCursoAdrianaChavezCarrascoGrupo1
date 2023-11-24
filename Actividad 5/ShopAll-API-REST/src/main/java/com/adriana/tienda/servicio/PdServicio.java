package com.adriana.tienda.servicio;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adriana.tienda.datos.Pedido;
import com.adriana.tienda.datos.Usuario;
import com.adriana.tienda.dto.PedidoDTO;
import com.adriana.tienda.excepciones.RecursoNoEncontradoException;
import com.adriana.tienda.reposiorio.PdRepositorio;
import com.adriana.tienda.reposiorio.URepositorio;

@Service
public class PdServicio {

	@Autowired
	private PdRepositorio pdRep;

	@Autowired
	private URepositorio uRep;

	// convertir entidad a dto
	private PedidoDTO mapearDTO(Pedido pd) {
		PedidoDTO pdDTO = new PedidoDTO();
		pdDTO.setIdPedido(pd.getIdPedido());
		pdDTO.setPdEstado(pd.getPdEstado());
		pdDTO.setPdSubtotal(pd.getPdSubtotal());
		return pdDTO;
	}

	// convertir de DTO a Entidad
	private Pedido mapearEntidad(PedidoDTO pdDTO) {
		Pedido pd = new Pedido();
		pd.setIdPedido(pdDTO.getIdPedido());
		pd.setPdEstado(pdDTO.getPdEstado());
		pd.setPdSubtotal(pdDTO.getPdSubtotal());
		return pd;
	}

	public PedidoDTO addPedido(int idUser, PedidoDTO pdDTO) {
		Usuario user = uRep.findById(idUser).orElseThrow();

		// Crear y guardar el pedido
		Pedido pd = mapearEntidad(pdDTO);
		pd.setUsuario(user);
		pd.setPdEstado("pendiente");

		int cantidadTotal = 0;

		// Actualizar el pedido con los totales calculados
		pd.setPdSubtotal(cantidadTotal);

		Pedido nuevoPedido = pdRep.save(pd);
		return mapearDTO(nuevoPedido);
	}

	public List<PedidoDTO> getAll() {
		List<Pedido> lPedidos = pdRep.findAll();
		return lPedidos.stream().map(pd -> mapearDTO(pd)).collect(Collectors.toList());
	}

	public PedidoDTO getById(int id) {
		Pedido pd = pdRep.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("Pedido", "id", id));
		return mapearDTO(pd);
	}

	public void deletePedido(int id) {
		Pedido pd = pdRep.findById(id).orElseThrow(() -> new RecursoNoEncontradoException("Pedido", "id", id));
		pdRep.delete(pd);
	}
}
