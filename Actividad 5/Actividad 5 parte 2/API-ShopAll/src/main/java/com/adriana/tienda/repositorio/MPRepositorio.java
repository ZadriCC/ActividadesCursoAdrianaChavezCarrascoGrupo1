package com.adriana.tienda.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adriana.tienda.datos.MetodoPago;

public interface MPRepositorio extends JpaRepository<MetodoPago, Integer> {

}
