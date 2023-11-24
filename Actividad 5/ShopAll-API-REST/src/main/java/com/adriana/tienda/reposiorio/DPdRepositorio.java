package com.adriana.tienda.reposiorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adriana.tienda.datos.DetallePedido;
import com.adriana.tienda.datos.Pedido;
import com.adriana.tienda.datos.Producto;

public interface DPdRepositorio extends JpaRepository<DetallePedido, Integer> {

	DetallePedido findByPedidoAndProducto(Pedido pedido, Producto producto);

}
