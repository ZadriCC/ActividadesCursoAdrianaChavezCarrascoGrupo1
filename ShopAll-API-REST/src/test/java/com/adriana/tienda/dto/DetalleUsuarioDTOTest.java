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

public class DetalleUsuarioDTOTest {

	private Validator validator;

	@BeforeEach
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void testGetterAndSetter() {
		DetalleUsuarioDTO dtuDTO = new DetalleUsuarioDTO();

		dtuDTO.setIdDUsuario(1);
		assertThat(dtuDTO.getIdDUsuario()).isEqualTo(1);

		dtuDTO.setDuDireccion("Calle Morelos #234");
		assertThat(dtuDTO.getDuDireccion()).isEqualTo("Calle Morelos #234");

		dtuDTO.setDuTelefono("1234567890");
		assertThat(dtuDTO.getDuTelefono()).isEqualTo("1234567890");
	}

	@Test
	public void testEmptyConstructor() {
		DetalleUsuarioDTO dtuDTO = new DetalleUsuarioDTO();
		assertNotNull(dtuDTO);
	}

	@Test
	public void testValidations() {
		// Verificar las restricciones de validación
		DetalleUsuarioDTO dtuDTO = new DetalleUsuarioDTO();
		dtuDTO.setDuDireccion("Centro");
		dtuDTO.setDuTelefono("1234567f890");

		// Validar el objeto usando el validador
		Set<ConstraintViolation<DetalleUsuarioDTO>> violations = validator.validate(dtuDTO);

		// Verificar que haya al menos dos violaciones de restricción
		assertEquals(2, violations.size());
	}

	@Test
	public void testValidDetalleUsuarioDTO() {
		// Probar que un objeto válido pasa todas las restricciones de validación
		DetalleUsuarioDTO dtuDTO = new DetalleUsuarioDTO();
		dtuDTO.setDuDireccion("Calle Morelos #234");
		dtuDTO.setDuTelefono("1234567890"); // Un número de teléfono válido

		// Validar el objeto usando el validador
		Set<ConstraintViolation<DetalleUsuarioDTO>> violations = validator.validate(dtuDTO);
		assertTrue(violations.isEmpty());
	}

	@Test
	public void testInvalidDetalleUsuarioDTO() {
		// Verificar que un objeto sin valores válidos no pasa ninguna restricción de
		// validación
		DetalleUsuarioDTO dtuDTO = new DetalleUsuarioDTO();
		Set<ConstraintViolation<DetalleUsuarioDTO>> violations = validator.validate(dtuDTO);
		assertEquals(2, violations.size());
	}
}
