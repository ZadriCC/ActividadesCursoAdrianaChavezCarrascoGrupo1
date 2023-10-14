package com.adriana.tienda.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adriana.tienda.datos.Notificaciones;

public interface NRepositorio extends JpaRepository<Notificaciones, Integer> {

}
