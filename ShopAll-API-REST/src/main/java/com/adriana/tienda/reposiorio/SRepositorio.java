package com.adriana.tienda.reposiorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adriana.tienda.datos.Subcategoria;

public interface SRepositorio extends JpaRepository<Subcategoria, Integer> {

	boolean existsByScTitulo(String scTitulo);
}
