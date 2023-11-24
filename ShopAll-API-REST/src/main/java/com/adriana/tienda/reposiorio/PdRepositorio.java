package com.adriana.tienda.reposiorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adriana.tienda.datos.Pedido;

public interface PdRepositorio extends JpaRepository<Pedido, Integer> {

}
