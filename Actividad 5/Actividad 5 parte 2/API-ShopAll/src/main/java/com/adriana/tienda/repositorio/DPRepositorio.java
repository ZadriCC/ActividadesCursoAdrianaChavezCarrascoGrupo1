package com.adriana.tienda.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adriana.tienda.datos.DetalleProducto;

public interface DPRepositorio extends JpaRepository<DetalleProducto, Integer> {

}
