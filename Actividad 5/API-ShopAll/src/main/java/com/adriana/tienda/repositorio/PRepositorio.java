package com.adriana.tienda.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adriana.tienda.datos.Productos;

public interface PRepositorio extends JpaRepository<Productos, Integer> {

}
