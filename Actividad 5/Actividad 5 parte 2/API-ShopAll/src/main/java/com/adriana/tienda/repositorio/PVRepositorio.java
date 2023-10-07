package com.adriana.tienda.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adriana.tienda.datos.PerfilVendedor;

public interface PVRepositorio extends JpaRepository<PerfilVendedor, Integer> {

}
