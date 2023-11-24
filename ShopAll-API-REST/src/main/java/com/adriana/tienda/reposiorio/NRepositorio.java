package com.adriana.tienda.reposiorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adriana.tienda.datos.Notificacion;
import com.adriana.tienda.datos.Usuario;

public interface NRepositorio extends JpaRepository<Notificacion, Integer> {
	  
	List<Notificacion> findByUsuario(Usuario usuario);
}
