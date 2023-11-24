package com.adriana.tienda.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adriana.tienda.datos.DetalleUsuario;
import com.adriana.tienda.datos.Usuarios;

public interface DetalleUsuarioRepositorio extends JpaRepository<DetalleUsuario, Integer> {
	
	public List<DetalleUsuario> findByUsuarios(Usuarios usuarios);

}
