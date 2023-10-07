package com.adriana.tienda.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.adriana.tienda.datos.DetallePedido;

public interface DPdRepositorio extends JpaRepository<DetallePedido, Integer> {
}
