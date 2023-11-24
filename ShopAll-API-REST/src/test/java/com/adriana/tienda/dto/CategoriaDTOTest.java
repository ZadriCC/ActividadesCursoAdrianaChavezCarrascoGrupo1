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

public class CategoriaDTOTest {

	private Validator validator;

	@BeforeEach
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void testGetterAndSetter() {
		CategoriaDTO categoriaDTO = new CategoriaDTO();

		categoriaDTO.setIdCategoria(1);
		assertThat(categoriaDTO.getIdCategoria()).isEqualTo(1);

		categoriaDTO.setCtTitulo("Ropa");
		assertThat(categoriaDTO.getCtTitulo()).isEqualTo("Ropa");
	}

	@Test
	public void testEmptyConstructor() {
		CategoriaDTO categoriaDTO = new CategoriaDTO();
		assertNotNull(categoriaDTO);
	}

	@Test
	public void testValidations() {
		// Verificar las restricciones de validación
		CategoriaDTO categoriaDTO = new CategoriaDTO();
		categoriaDTO.setCtTitulo("cx"); // Un título demasiado corto para la validación

		// Validar el objeto usando el validador
		Set<ConstraintViolation<CategoriaDTO>> violations = validator.validate(categoriaDTO);

		// Verificar que haya una violación de restricción
		assertEquals(1, violations.size());
	}

	@Test
	public void testValidCategoriaDTO() {
		// Probar que un objeto válido pasa todas las restricciones de validación
		CategoriaDTO categoriaDTO = new CategoriaDTO();
		categoriaDTO.setCtTitulo("Ropa");

		// Validar el objeto usando el validador
		Set<ConstraintViolation<CategoriaDTO>> violations = validator.validate(categoriaDTO);
		assertTrue(violations.isEmpty());
	}

	@Test
	public void testInvalidCategoriaDTO() {
		// Verificar que un objeto sin valores válidos no pasa ninguna restricción de
		// validación
		CategoriaDTO categoriaDTO = new CategoriaDTO();
		Set<ConstraintViolation<CategoriaDTO>> violations = validator.validate(categoriaDTO);
		assertEquals(1, violations.size());
	}
}