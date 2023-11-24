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

public class DetalleProductoDTOTest {

	private Validator validator;

	@BeforeEach
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void testGetterAndSetter() {
		DetalleProductoDTO detalleProductoDTO = new DetalleProductoDTO();

		detalleProductoDTO.setIdDProducto(1);
		assertThat(detalleProductoDTO.getIdDProducto()).isEqualTo(1);

		detalleProductoDTO.setDpAtributo("Tamaño");
		assertThat(detalleProductoDTO.getDpAtributo()).isEqualTo("Tamaño");

		detalleProductoDTO.setDpValor("Grande");
		assertThat(detalleProductoDTO.getDpValor()).isEqualTo("Grande");
	}

	@Test
	public void testEmptyConstructor() {
		DetalleProductoDTO detalleProductoDTO = new DetalleProductoDTO();
		assertNotNull(detalleProductoDTO);
	}

	@Test
	public void testValidations() {
		// Verificar las restricciones de validación
		DetalleProductoDTO detalleProductoDTO = new DetalleProductoDTO();
		detalleProductoDTO.setDpAtributo("Tamaño");
		detalleProductoDTO.setDpValor("Grande");

		// Validar el objeto usando el validador
		Set<ConstraintViolation<DetalleProductoDTO>> violations = validator.validate(detalleProductoDTO);

		// Verificar que no haya violaciones de restricción
		assertEquals(2, violations.size());
	}

	@Test
	public void testInvalidDetalleProductoDTO() {
		// Verificar que un objeto con valores inválidos no pasa las restricciones de
		// validación
		DetalleProductoDTO detalleProductoDTO = new DetalleProductoDTO();
		detalleProductoDTO.setDpAtributo("Tamaño");
		detalleProductoDTO.setDpValor("");

		// Validar el objeto usando el validador
		Set<ConstraintViolation<DetalleProductoDTO>> violations = validator.validate(detalleProductoDTO);

		// Verificar que haya violaciones de restricción
		assertEquals(2, violations.size());
	}
}
