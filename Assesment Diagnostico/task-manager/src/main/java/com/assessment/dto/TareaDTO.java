package com.assessment.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class TareaDTO {
	
	private long id;
	
	@NotEmpty(message = "El campo descripción no debe estar vacio")
	@Size(min=3,message = "La descripción de la tarea deberia tener al menos 3 caracteres")
	private String descripcion;
	
	@NotEmpty(message = "El campo status no debe estar vacio")
	@Pattern(regexp = "^(pendiente|en progreso|completada$", message = "El estado debe ser pendiente, en progreso o completada")
	private String status;//pendiente, en progreso, completada

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

	public TareaDTO() {
		super();
	}

}
