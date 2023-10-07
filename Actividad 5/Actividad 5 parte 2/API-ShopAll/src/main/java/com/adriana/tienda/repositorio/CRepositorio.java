package com.adriana.tienda.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adriana.tienda.datos.Categoria;

public interface CRepositorio extends JpaRepository<Categoria, Integer> {

}
