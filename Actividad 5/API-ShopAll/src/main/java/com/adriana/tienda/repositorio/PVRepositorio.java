package com.adriana.tienda.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adriana.tienda.datos.PerfilVendedor;
import com.adriana.tienda.datos.Usuarios;

public interface PVRepositorio extends JpaRepository<PerfilVendedor, Integer> {
	
	public PerfilVendedor findByVendedor(Usuarios vendedor);
	
}
