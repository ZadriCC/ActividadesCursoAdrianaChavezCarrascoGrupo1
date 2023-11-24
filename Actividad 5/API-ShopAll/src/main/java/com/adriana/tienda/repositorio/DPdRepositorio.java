package com.adriana.tienda.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adriana.tienda.datos.DetallePedido;
import com.adriana.tienda.datos.Pedidos;
import com.adriana.tienda.datos.Productos;

public interface DPdRepositorio extends JpaRepository<DetallePedido, Integer> {
	DetallePedido findByPedidoAndProducto(Pedidos pedido, Productos producto);

}
