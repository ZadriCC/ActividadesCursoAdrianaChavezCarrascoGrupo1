package com.adriana.tienda.reposiorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adriana.tienda.datos.Producto;

public interface PRepositorio extends JpaRepository<Producto, Integer> {

}
