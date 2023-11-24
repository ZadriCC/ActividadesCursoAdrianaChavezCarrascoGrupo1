package com.adriana.tienda.reposiorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adriana.tienda.datos.MetodoEnvio;

public interface MERepositorio extends JpaRepository<MetodoEnvio, Integer> {

}
