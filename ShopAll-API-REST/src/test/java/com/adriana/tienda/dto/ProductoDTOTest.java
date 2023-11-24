package com.adriana.tienda.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Set;

public class ProductoDTOTest {

	private Validator validator;

	@BeforeEach
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void testGetterAndSetter() {
		ProductoDTO pDTO = new ProductoDTO();

		pDTO.setIdProducto(1);
		assertThat(pDTO.getIdProducto()).isEqualTo(1);

		pDTO.setpNombre("Jabon liquido");
		assertThat(pDTO.getpNombre()).isEqualTo("Jabon liquido");

		pDTO.setpDescripcion("Jabon liquido para manos");
		assertThat(pDTO.getpDescripcion()).isEqualTo("Jabon liquido para manos");

		pDTO.setpPrecio(29.99);
		assertThat(pDTO.getpPrecio()).isEqualTo(29.99);

		pDTO.setpStock(10);
		assertThat(pDTO.getpStock()).isEqualTo(10);
	}

	@Test
	public void testEmptyConstructor() {
		ProductoDTO pDTO = new ProductoDTO();
		assertNotNull(pDTO);
	}

	@Test
	public void testValidations() {
		ProductoDTO pDTO = new ProductoDTO();
		// Asignar valores que violen las restricciones
		pDTO.setpNombre("");
		pDTO.setpDescripcion("");
		pDTO.setpPrecio(-2); 
		pDTO.setpStock(-2);

		Set<ConstraintViolation<ProductoDTO>> violations = validator.validate(pDTO);
		assertFalse(violations.isEmpty());
	}

	@Test
	public void testValidProductoDTO() {
		ProductoDTO pDTO = new ProductoDTO();
		pDTO.setpNombre("Jabon liquido");
		pDTO.setpDescripcion("Jabon liquido para manos");
		pDTO.setpPrecio(29.99);
		pDTO.setpStock(10);

		Set<ConstraintViolation<ProductoDTO>> violations = validator.validate(pDTO);
		assertFalse(violations.isEmpty());
	}

	@Test
	public void testInvalidProductoDTO() {
		ProductoDTO pDTO = new ProductoDTO();
		Set<ConstraintViolation<ProductoDTO>> violations = validator.validate(pDTO);
		assertFalse(violations.isEmpty());
	}
}
