package com.adriana.tienda.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

public class DetallePedidoDTOTest {

	private Validator validator;

	@BeforeEach
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void testGetterAndSetter() {
		DetallePedidoDTO dtpDTO = new DetallePedidoDTO();

		dtpDTO.setIdDPedido(1);
		assertThat(dtpDTO.getIdDPedido()).isEqualTo(1);

		dtpDTO.setDtpCantidad(5);
		assertThat(dtpDTO.getDtpCantidad()).isEqualTo(5);

		dtpDTO.setDtpTotal(100.0);
		assertThat(dtpDTO.getDtpTotal()).isEqualTo(100.0);
	}

	@Test
	public void testEmptyConstructor() {
		DetallePedidoDTO dtpDTO = new DetallePedidoDTO();
		assertNotNull(dtpDTO);
	}

	@Test
	public void testValidations() {
		DetallePedidoDTO dtpDTO = new DetallePedidoDTO();
		dtpDTO.setDtpCantidad(0);
		dtpDTO.setDtpTotal(0);

		Set<ConstraintViolation<DetallePedidoDTO>> violations = validator.validate(dtpDTO);
		assertTrue(violations.isEmpty());
	}
}
