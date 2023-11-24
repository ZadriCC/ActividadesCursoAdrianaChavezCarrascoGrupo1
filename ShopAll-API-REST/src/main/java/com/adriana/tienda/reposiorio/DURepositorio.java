package com.adriana.tienda.reposiorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adriana.tienda.datos.DetalleUsuario;

public interface DURepositorio extends JpaRepository<DetalleUsuario, Integer> {

}
