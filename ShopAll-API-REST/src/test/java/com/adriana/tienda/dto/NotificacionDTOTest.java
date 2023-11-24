package com.adriana.tienda.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.adriana.tienda.datos.Usuario;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.time.LocalDate;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class NotificacionDTOTest {

	private Validator validator;

	@BeforeEach
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();

	}

	@Test
	public void testGetterAndSetter() {
		NotificacionDTO nDTO = new NotificacionDTO();

		nDTO.setIdNotificacion(1);
		assertThat(nDTO.getIdNotificacion()).isEqualTo(1);

		nDTO.setnDescripcion("Bienvenido a la tienda de ShopAll");
		assertThat(nDTO.getnDescripcion()).isEqualTo("Bienvenido a la tienda de ShopAll");

		LocalDate fecha = LocalDate.now();
		nDTO.setnFecha(fecha);
		assertThat(nDTO.getnFecha()).isEqualTo(fecha);

		Usuario user = new Usuario();
		user.setIdUsuario(1);
		nDTO.setUsuario(user); // Asignar el usuario a la notificación
		assertThat(nDTO.getUsuario()).isEqualTo(user);
	}

	@Test
	public void testEmptyConstructor() {
		NotificacionDTO nDTO = new NotificacionDTO();
		assertNotNull(nDTO);
	}

	@Test
	public void testValidations() {
		NotificacionDTO nDTO = new NotificacionDTO();

		nDTO.setnDescripcion("Hola");
		LocalDate fecha = LocalDate.now().plusDays(1);
		nDTO.setnFecha(fecha);
	}

	@Test
	public void testValidNotificacionDTO() {
		NotificacionDTO notificacionDTO = new NotificacionDTO();
		notificacionDTO.setIdNotificacion(1);
		notificacionDTO.setnDescripcion("Descripción válida");
		notificacionDTO.setnFecha(LocalDate.now());

		Set<ConstraintViolation<NotificacionDTO>> violations = validator.validate(notificacionDTO);
		assertTrue(violations.isEmpty());
	}

	@Test
	public void testInvalidNotificacionDTO() {
		NotificacionDTO notificacionDTO = new NotificacionDTO();
		Set<ConstraintViolation<NotificacionDTO>> violations = validator.validate(notificacionDTO);
		// Verifica el número esperado de violaciones para un objeto no válido
		assertEquals(2, violations.size()); // Esto puede variar dependiendo de tus restricciones
	}
}
