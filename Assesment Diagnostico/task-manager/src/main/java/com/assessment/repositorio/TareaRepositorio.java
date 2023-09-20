package com.assessment.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assessment.entidades.Tarea;

public interface TareaRepositorio extends JpaRepository<Tarea, Long>{

}
