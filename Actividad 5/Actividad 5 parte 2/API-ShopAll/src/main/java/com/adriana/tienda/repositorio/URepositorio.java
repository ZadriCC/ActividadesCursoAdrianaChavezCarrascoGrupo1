package com.adriana.tienda.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adriana.tienda.datos.Usuarios;

public interface URepositorio extends JpaRepository<Usuarios, Integer> {

}
