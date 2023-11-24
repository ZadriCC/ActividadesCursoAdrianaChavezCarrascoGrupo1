package com.adriana.tienda.servicios;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.adriana.tienda.datos.Categoria;
import com.adriana.tienda.datos.Subcategoria;
import com.adriana.tienda.dto.SubcategoriaDTO;
import com.adriana.tienda.excepciones.CampoDuplicadoException;
import com.adriana.tienda.excepciones.ErrorAppException;
import com.adriana.tienda.excepciones.RecursoNoEncontradoException;
import com.adriana.tienda.reposiorio.CRepositorio;
import com.adriana.tienda.reposiorio.SRepositorio;
import com.adriana.tienda.servicio.SServicio;

@ExtendWith(MockitoExtension.class)
public class SServicioTest {

    @Mock
    private SRepositorio sRep;

    @Mock
    private CRepositorio cRep;

    @InjectMocks
    private SServicio sServicio;

    private Subcategoria subcategoria;
    private SubcategoriaDTO subcategoriaDTO;

    @BeforeEach
    public void setup() {
        subcategoria = new Subcategoria();
        subcategoria.setIdSubcategoria(1);
        subcategoria.setScTitulo("Ropa Interior");

        subcategoriaDTO = new SubcategoriaDTO();
        subcategoriaDTO.setIdSubcategoria(1);
        subcategoriaDTO.setScTitulo("Ropa Interior");
    }

    @Test
    public void testAddSubcategoria() {
        // Configurar el mock del repositorio para que no haya duplicados
        when(sRep.existsByScTitulo(anyString())).thenReturn(false);

        // Configurar el mock del repositorio para simular el guardado exitoso
        when(cRep.findById(anyInt())).thenReturn(Optional.of(new Categoria()));
        when(sRep.save(any(Subcategoria.class))).thenReturn(subcategoria);

        // Agregar una subcategoría
        SubcategoriaDTO resultado = sServicio.addSubcategoria(1, subcategoriaDTO);

        assertNotNull(resultado);
        assertNotNull(resultado.getIdSubcategoria());
        assertEquals(subcategoriaDTO.getScTitulo(), resultado.getScTitulo());

        // Verificar la interacción con el mock
        verify(sRep).save(any(Subcategoria.class));
    }
}
