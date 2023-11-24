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

public class ReseñaDTOTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testGetterAndSetter() {
        ReseñaDTO reseñaDTO = new ReseñaDTO();

        reseñaDTO.setIdReseña(1);
        assertThat(reseñaDTO.getIdReseña()).isEqualTo(1);

        reseñaDTO.setrPuntuacion(5);
        assertThat(reseñaDTO.getrPuntuacion()).isEqualTo(5);

        reseñaDTO.setrComentario("¡Excelente producto!");
        assertThat(reseñaDTO.getrComentario()).isEqualTo("¡Excelente producto!");

        LocalDate fecha = LocalDate.now();
        reseñaDTO.setrFecha(fecha);
        assertThat(reseñaDTO.getrFecha()).isEqualTo(fecha);
    }

    @Test
    public void testEmptyConstructor() {
        ReseñaDTO reseñaDTO = new ReseñaDTO();
        assertNotNull(reseñaDTO);
    }

    @Test
    public void testValidations() {
        ReseñaDTO reseñaDTO = new ReseñaDTO();
        // Asignar valores que violen las restricciones
        reseñaDTO.setrPuntuacion(10);
        reseñaDTO.setrComentario("Este es un comentario que supera los 140 caracteres permitidos");

        Set<ConstraintViolation<ReseñaDTO>> violations = validator.validate(reseñaDTO);
        assertEquals(2, violations.size());
    }

    @Test
    public void testValidReseñaDTO() {
        ReseñaDTO reseñaDTO = new ReseñaDTO();
        reseñaDTO.setrPuntuacion(4);
        reseñaDTO.setrComentario("Buena calidad");
        reseñaDTO.setrFecha(LocalDate.now());

        Set<ConstraintViolation<ReseñaDTO>> violations = validator.validate(reseñaDTO);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void testInvalidReseñaDTO() {
        ReseñaDTO reseñaDTO = new ReseñaDTO();
        Set<ConstraintViolation<ReseñaDTO>> violations = validator.validate(reseñaDTO);
        assertEquals(2, violations.size());
    }
}
