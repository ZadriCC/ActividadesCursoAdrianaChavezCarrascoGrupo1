package com.adriana.tienda.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.time.LocalDate;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class UsuarioDTOTest {

	private Validator validator;

	@BeforeEach
	public void setUp() {

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	// Verifica si los métodos getters y setters de la clase funcionan correctamente
	@Test
	public void testGetterAndSetter() {

		UsuarioDTO uDTO = new UsuarioDTO();

		uDTO.setIdUsuario(1);
		assertThat(uDTO.getIdUsuario()).isEqualTo(1);

		uDTO.setuNombre("Juan");
		assertThat(uDTO.getuNombre()).isEqualTo("Juan");

		uDTO.setuNombre("Lopez Martinez");
		assertThat(uDTO.getuNombre()).isEqualTo("Lopez Martinez");

		uDTO.setuEmail("lm.juan@gmail.com");
		assertThat(uDTO.getuEmail()).isEqualTo("lm.juan@gmail.com");

		uDTO.setuPassword("P4Ss.ord");
		assertThat(uDTO.getuPassword()).isEqualTo("P4Ss.ord");

		uDTO.setuRol("comprador");
		assertThat(uDTO.getuRol()).isEqualTo("comprador");

		LocalDate fecha = LocalDate.now();
		uDTO.setuFecha(fecha);
		assertThat(uDTO.getuFecha()).isEqualTo(fecha);
	}

	// Asegura que el constructor vacío de la DTO funcione
	@Test
	public void testEmptyConstructor() {

		UsuarioDTO uDTO = new UsuarioDTO();
		assertNotNull(uDTO); // Verifica que el objeto se haya creado correctamente
	}

	// Verifica si las restricciones de validación funcionan correctamente.
	@Test
	public void testValidations() {

		UsuarioDTO uDTO = new UsuarioDTO();

		uDTO.setuNombre("Teodora Vicenta de la Purísima Concepción de la Inmaculada Trinidad Villavicencio");
		uDTO.setuApellidos("Teodora Vicenta de la Purísima Concepción de la Inmaculada Trinidad Villavicencio");
		uDTO.setuEmail("moxita.99.com");
		uDTO.setuPassword("pass");
		uDTO.setuRol("usuario");
		LocalDate fecha = LocalDate.now().plusDays(1);
		uDTO.setuFecha(fecha);
		
		Set<ConstraintViolation<UsuarioDTO>> violations = validator.validate(uDTO);

		// Verificar que haya al menos dos violaciones de restricción
		assertEquals(6, violations.size());
	}

	// Prueba que un objeto válido pasa todas las validaciones sin generar
	// violaciones de restricciones.
	@Test
	public void testValidUsuarioDTO() {

		UsuarioDTO uDTO = new UsuarioDTO();
		uDTO.setIdUsuario(1);
		uDTO.setuNombre("Juan");
		uDTO.setuApellidos("Lopez Perez");
		uDTO.setuEmail("lo.perez@gmail.com");
		uDTO.setuPassword("Contraseña123.");
		uDTO.setuRol("comprador");
		uDTO.setuFecha(LocalDate.now());

		Set<ConstraintViolation<UsuarioDTO>> violations = validator.validate(uDTO);
		assertTrue(violations.isEmpty());
	}

	// Comprueba que un objeto sin valores válidos no pasa ninguna restricción de
	// validación
	@Test
	public void testInvalidUsuarioDTO() {

		UsuarioDTO uDTO = new UsuarioDTO();
		Set<ConstraintViolation<UsuarioDTO>> violations = validator.validate(uDTO);
		assertEquals(6, violations.size());
	}
}