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

public class PedidoDTOTest {

	private Validator validator;

	@BeforeEach
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void testGetterAndSetter() {
		PedidoDTO pedidoDTO = new PedidoDTO();

		pedidoDTO.setIdPedido(1);
		assertThat(pedidoDTO.getIdPedido()).isEqualTo(1);

		pedidoDTO.setPdEstado("procesando");
		assertThat(pedidoDTO.getPdEstado()).isEqualTo("procesando");

		pedidoDTO.setPdSubtotal(100.50);
		assertThat(pedidoDTO.getPdSubtotal()).isEqualTo(100.50);
	}

	@Test
	public void testEmptyConstructor() {
		PedidoDTO pedidoDTO = new PedidoDTO();
		assertNotNull(pedidoDTO);
	}

	@Test
	public void testValidations() {
		// Verificar las restricciones de validación
		PedidoDTO pedidoDTO = new PedidoDTO();
		pedidoDTO.setPdEstado("listo"); // Estado no válido para la validación
		pedidoDTO.setPdSubtotal(0); // Subtotal no puede ser nulo

		// Validar el objeto usando el validador
		Set<ConstraintViolation<PedidoDTO>> violations = validator.validate(pedidoDTO);

		// Verificar que haya violaciones de restricción
		assertEquals(1, violations.size());
	}

	@Test
	public void testValidPedidoDTO() {
		// Probar que un objeto válido pasa todas las restricciones de validación
		PedidoDTO pedidoDTO = new PedidoDTO();
		pedidoDTO.setPdEstado("procesando");
		pedidoDTO.setPdSubtotal(100.50);

		// Validar el objeto usando el validador
		Set<ConstraintViolation<PedidoDTO>> violations = validator.validate(pedidoDTO);
		assertTrue(violations.isEmpty());
	}

	@Test
	public void testInvalidPedidoDTO() {
		// Verificar que un objeto sin valores válidos no pasa ninguna restricción de
		// validación
		PedidoDTO pedidoDTO = new PedidoDTO();
		Set<ConstraintViolation<PedidoDTO>> violations = validator.validate(pedidoDTO);
		assertEquals(1, violations.size());
	}
}
