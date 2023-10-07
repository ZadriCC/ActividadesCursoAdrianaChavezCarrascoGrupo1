package com.adriana.tienda.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adriana.tienda.datos.Subcategoria;

public interface SRepositorio extends JpaRepository<Subcategoria, Integer> {

}
