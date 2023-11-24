package com.adriana.tienda.reposiorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adriana.tienda.datos.Reseña;

public interface RRepositorio extends JpaRepository<Reseña, Integer> {

}
