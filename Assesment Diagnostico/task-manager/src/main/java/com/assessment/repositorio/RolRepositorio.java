package com.assessment.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assessment.entidades.Rol;
import com.assessment.entidades.Usuario;
import java.util.List;


public interface RolRepositorio extends JpaRepository<Usuario, Long>{
	
	public Optional<Rol> findByNombre(String nombre);

}
