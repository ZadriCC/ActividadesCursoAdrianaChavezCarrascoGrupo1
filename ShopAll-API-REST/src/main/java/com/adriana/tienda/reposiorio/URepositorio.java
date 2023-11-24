package com.adriana.tienda.reposiorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adriana.tienda.datos.Usuario;

public interface URepositorio extends JpaRepository<Usuario, Integer> {

	boolean existsByuEmail(String uEmail);
	public Optional<Usuario> findByuEmail(String uEmail);
	
	
}
