package com.adriana.tienda.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class MetodoEnvioDTOTest {

	private Validator validator;

	@BeforeEach
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void testGetterAndSetter() {
		MetodoEnvioDTO metodoEnvioDTO = new MetodoEnvioDTO();

		metodoEnvioDTO.setIdMetodoEnvio(1);
		assertThat(metodoEnvioDTO.getIdMetodoEnvio()).isEqualTo(1);

		metodoEnvioDTO.setMeTipo("Envío express");
		assertThat(metodoEnvioDTO.getMeTipo()).isEqualTo("Envío express");

		metodoEnvioDTO.setMePrecio(15.99);
		assertThat(metodoEnvioDTO.getMePrecio()).isEqualTo(15.99);
	}

	@Test
	public void testEmptyConstructor() {
		MetodoEnvioDTO metodoEnvioDTO = new MetodoEnvioDTO();
		assertNotNull(metodoEnvioDTO);
	}

	@Test
	public void testValidations() {
		// Verificar las restricciones de validación
		MetodoEnvioDTO metodoEnvioDTO = new MetodoEnvioDTO();
		metodoEnvioDTO.setMeTipo("Enví");
		metodoEnvioDTO.setMePrecio(0);
		Set<ConstraintViolation<MetodoEnvioDTO>> violations = validator.validate(metodoEnvioDTO);
		assertEquals(1, violations.size());
	}

	@Test
	public void testValidMetodoEnvioDTO() {
		// Probar que un objeto válido pasa todas las restricciones de validación
		MetodoEnvioDTO metodoEnvioDTO = new MetodoEnvioDTO();
		metodoEnvioDTO.setMeTipo("Envío express");
		metodoEnvioDTO.setMePrecio(15.99);

		// Validar el objeto usando el validador
		Set<ConstraintViolation<MetodoEnvioDTO>> violations = validator.validate(metodoEnvioDTO);
		assertTrue(violations.isEmpty());
	}

	@Test
	public void testInvalidMetodoEnvioDTO() {
		// Verificar que un objeto sin valores válidos no pasa ninguna restricción de
		// validación
		MetodoEnvioDTO metodoEnvioDTO = new MetodoEnvioDTO();
		Set<ConstraintViolation<MetodoEnvioDTO>> violations = validator.validate(metodoEnvioDTO);
		assertEquals(1, violations.size());
	}
}
