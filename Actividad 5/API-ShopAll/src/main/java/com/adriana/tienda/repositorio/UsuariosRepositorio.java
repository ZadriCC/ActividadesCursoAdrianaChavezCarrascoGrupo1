package com.adriana.tienda.repositorio;


import org.springframework.data.jpa.repository.JpaRepository;

import com.adriana.tienda.datos.Usuarios;

public interface UsuariosRepositorio extends JpaRepository<Usuarios, Integer> {
	
}
