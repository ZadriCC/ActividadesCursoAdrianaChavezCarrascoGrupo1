package com.adriana.tienda.reposiorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adriana.tienda.datos.Transaccion;

public interface TRepositorio extends JpaRepository<Transaccion, Integer> {

	boolean existsBytNoSeguimiento(String tNoSeguimiento);
}
