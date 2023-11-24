package com.adriana.tienda.reposiorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adriana.tienda.datos.Usuario;
import com.adriana.tienda.datos.Vendedor;

public interface VRepositorio extends JpaRepository<Vendedor, Integer> {

	public Vendedor findByUsuario(Usuario usuario);
}
