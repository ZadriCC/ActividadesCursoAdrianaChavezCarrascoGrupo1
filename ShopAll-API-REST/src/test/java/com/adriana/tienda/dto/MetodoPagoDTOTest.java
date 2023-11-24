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

public class MetodoPagoDTOTest {

	private Validator validator;

	@BeforeEach
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void testGetterAndSetter() {
		MetodoPagoDTO metodoPagoDTO = new MetodoPagoDTO();

		metodoPagoDTO.setIdMetodoPago(1);
		assertThat(metodoPagoDTO.getIdMetodoPago()).isEqualTo(1);

		metodoPagoDTO.setMpTipo("Tarjeta");
		assertThat(metodoPagoDTO.getMpTipo()).isEqualTo("Tarjeta");
	}

	@Test
	public void testEmptyConstructor() {
		MetodoPagoDTO metodoPagoDTO = new MetodoPagoDTO();
		assertNotNull(metodoPagoDTO);
	}

	@Test
	public void testValidations() {
		// Verificar las restricciones de validación
		MetodoPagoDTO metodoPagoDTO = new MetodoPagoDTO();
		metodoPagoDTO.setMpTipo("Pago");

		// Validar el objeto usando el validador
		Set<ConstraintViolation<MetodoPagoDTO>> violations = validator.validate(metodoPagoDTO);

		// Verificar que haya violaciones de restricción
		assertEquals(1, violations.size());
	}

	@Test
	public void testValidMetodoPagoDTO() {
		// Probar que un objeto válido pasa todas las restricciones de validación
		MetodoPagoDTO metodoPagoDTO = new MetodoPagoDTO();
		metodoPagoDTO.setMpTipo("Tarjeta");

		// Validar el objeto usando el validador
		Set<ConstraintViolation<MetodoPagoDTO>> violations = validator.validate(metodoPagoDTO);
		assertTrue(violations.isEmpty());
	}

	@Test
	public void testInvalidMetodoPagoDTO() {
		// Verificar que un objeto sin valores válidos no pasa ninguna restricción de
		// validación
		MetodoPagoDTO metodoPagoDTO = new MetodoPagoDTO();
		Set<ConstraintViolation<MetodoPagoDTO>> violations = validator.validate(metodoPagoDTO);
		assertEquals(1, violations.size());
	}
}
