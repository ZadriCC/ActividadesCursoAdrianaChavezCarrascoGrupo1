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

public class VendedorDTOTest {

	private Validator validator;

	@BeforeEach
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void testGetterAndSetter() {

		VendedorDTO vDTO = new VendedorDTO();

		vDTO.setIdVendedor(1);
		assertThat(vDTO.getIdVendedor()).isEqualTo(1);

		vDTO.setvNombre("Soriana");
		assertThat(vDTO.getvNombre()).isEqualTo("Soriana");

		vDTO.setvGiro("Tienda de Autoservicio");
		assertThat(vDTO.getvGiro()).isEqualTo("Tienda de Autoservicio");

		vDTO.setvDescripcion("Tienda de autoservicio con los mejores precios");
		assertThat(vDTO.getvDescripcion()).isEqualTo("Tienda de autoservicio con los mejores precios");

		vDTO.setvSitioWeb("www.soriana.com.mx");
		assertThat(vDTO.getvSitioWeb()).isEqualTo("www.soriana.com.mx");
	}

	@Test
	public void testEmptyConstructor() {
		VendedorDTO vDTO = new VendedorDTO();
		assertNotNull(vDTO);
	}

	@Test
	public void testValidations() {
		// verificar las restricciones de validación
		VendedorDTO vDTO = new VendedorDTO();
		vDTO.setvNombre("LG");
		vDTO.setvDescripcion("hola");
		vDTO.setvGiro("Tienda");
		vDTO.setvSitioWeb("wwwholacom");
	}

	@Test
	public void testValidVendedorDTO() {
		// probar que un objeto válido pasa todas las restricciones de validación
		VendedorDTO vDTO = new VendedorDTO();
		vDTO.setvNombre("Soriana");
		vDTO.setvDescripcion("Tienda de Autoservicio");
		vDTO.setvGiro("Tienda de autoservicio con los mejores precios");
		vDTO.setvSitioWeb("http://www.soriana.com");

		Set<ConstraintViolation<VendedorDTO>> violations = validator.validate(vDTO);
		assertTrue(violations.isEmpty());
	}

	@Test
	public void testInvalidVendedorDTO() {
		// para verificar que un objeto sin valores válidos no pasa ninguna restricción
		// de validación
		VendedorDTO vDTO = new VendedorDTO();
		Set<ConstraintViolation<VendedorDTO>> violations = validator.validate(vDTO);
		assertEquals(3, violations.size());
	}
}
