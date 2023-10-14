package com.adriana.tienda.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adriana.tienda.datos.Pedidos;

public interface PdRepositorio extends JpaRepository<Pedidos, Integer> {
	
}
