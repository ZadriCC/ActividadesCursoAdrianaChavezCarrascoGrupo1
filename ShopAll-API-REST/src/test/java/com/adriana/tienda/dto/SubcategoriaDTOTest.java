package com.adriana.tienda.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class SubcategoriaDTOTest {

	private Validator validator;

	@BeforeEach
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void testGetterAndSetter() {
		SubcategoriaDTO sDTO = new SubcategoriaDTO();

		sDTO.setIdSubcategoria(1);
		assertThat(sDTO.getIdSubcategoria()).isEqualTo(1);

		sDTO.setScTitulo("Electrónica");
		assertThat(sDTO.getScTitulo()).isEqualTo("Electrónica");
	}

	@Test
	public void testEmptyConstructor() {
		SubcategoriaDTO sDTO = new SubcategoriaDTO();
		assertNotNull(sDTO);
	}

	@Test
	public void testValidations() {
		SubcategoriaDTO sDTO = new SubcategoriaDTO();
		// Asignar valores que violen las restricciones
		sDTO.setScTitulo("E");

		Set<ConstraintViolation<SubcategoriaDTO>> violations = validator.validate(sDTO);
		assertEquals(1, violations.size());
	}

	@Test
	public void testValidSubcategoriaDTO() {
		SubcategoriaDTO sDTO = new SubcategoriaDTO();
		sDTO.setScTitulo("Ropa");

		Set<ConstraintViolation<SubcategoriaDTO>> violations = validator.validate(sDTO);
		assertFalse(violations.isEmpty());
	}

	@Test
	public void testInvalidSubcategoriaDTO() {
		SubcategoriaDTO sDTO = new SubcategoriaDTO();
		Set<ConstraintViolation<SubcategoriaDTO>> violations = validator.validate(sDTO);
		assertEquals(1, violations.size());
	}
}
