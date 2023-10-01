package com.assessment.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "Tareas", uniqueConstraints = { @UniqueConstraint(columnNames = "descripcion") })
public class Tarea {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "descripcion", nullable = false)
	private String descripcion;

	@Column(name = "status", nullable = false)
	private String status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Tarea() {
		super();
	}

	public Tarea(long id, String descripcion, String status) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.status = status;
	}

}
