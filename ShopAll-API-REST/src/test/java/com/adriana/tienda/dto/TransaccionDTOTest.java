package com.adriana.tienda.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class TransaccionDTOTest {

	private Validator validator;

	@BeforeEach
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void testGetterAndSetter() {
		TransaccionDTO tDTO = new TransaccionDTO();

		tDTO.setIdTransaccion(1);
		assertThat(tDTO.getIdTransaccion()).isEqualTo(1);

		LocalDate fecha = LocalDate.now();
		tDTO.settFecha(fecha);
		assertThat(tDTO.gettFecha()).isEqualTo(fecha);

		tDTO.settNotasCliente("Entregar en casa Azul");
		assertThat(tDTO.gettNotasCliente()).isEqualTo("Entregar en casa Azul");

		tDTO.settNoSeguimiento("1234567890123456789012345");
		assertThat(tDTO.gettNoSeguimiento()).isEqualTo("1234567890123456789012345");

		tDTO.settDireccionEnvio("Francosco I. Madero #234");
		assertThat(tDTO.gettDireccionEnvio()).isEqualTo("Francosco I. Madero #234");

		tDTO.settTotal(100.0);
		assertThat(tDTO.gettTotal()).isEqualTo(100.0);
	}

	@Test
	public void testEmptyConstructor() {
		TransaccionDTO tDTO = new TransaccionDTO();
		assertNotNull(tDTO);
	}

	@Test
	public void testValidations() {
		TransaccionDTO tDTO = new TransaccionDTO();
		LocalDate fecha = LocalDate.now();
		tDTO.settFecha(fecha);
		tDTO.settNotasCliente("Entregar en casa Azul");
		tDTO.settNoSeguimiento("1234567890123456789012345");
		tDTO.settDireccionEnvio("Francosco I. Madero #234");
		tDTO.settTotal(100.0);

		Set<ConstraintViolation<TransaccionDTO>> violations = validator.validate(tDTO);
		assertTrue(violations.isEmpty());
	}

	@Test
	public void testInvalidTransaccionDTO() {
		TransaccionDTO tDTO = new TransaccionDTO();
		Set<ConstraintViolation<TransaccionDTO>> violations = validator.validate(tDTO);
		assertFalse(violations.isEmpty());
	}
}
