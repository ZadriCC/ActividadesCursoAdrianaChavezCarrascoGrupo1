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
import com.adriana.tienda.dto.CategoriaDTO;
import com.adriana.tienda.reposiorio.CRepositorio;
import com.adriana.tienda.servicio.CServicio;

@ExtendWith(MockitoExtension.class)
public class CServicioTest {

    @Mock
    private CRepositorio ctRep;

    @InjectMocks
    private CServicio cServicio;

    private Categoria categoria;
    private CategoriaDTO categoriaDTO;

    @BeforeEach
    public void setup() {
        categoria = new Categoria();
        categoria.setIdCategoria(1);
        categoria.setCtTitulo("Ropa");

        categoriaDTO = new CategoriaDTO();
        categoriaDTO.setIdCategoria(1);
        categoriaDTO.setCtTitulo("Ropa");
    }

    @Test
    public void testAddCategoria() {
        // Configurar el mock del repositorio para simular la no existencia de la categoría
        when(ctRep.existsByCtTitulo(anyString())).thenReturn(false);
        // Configurar el mock del repositorio para simular el guardado exitoso
        when(ctRep.save(any(Categoria.class))).thenReturn(categoria);

        // Llamar al método a probar
        CategoriaDTO resultado = cServicio.addCategoria(categoriaDTO);

        // Verificar los resultados
        assertNotNull(resultado);
        assertEquals(categoriaDTO.getCtTitulo(), resultado.getCtTitulo());

        // Verificar la interacción con el mock
        verify(ctRep).existsByCtTitulo(anyString());
        verify(ctRep).save(any(Categoria.class));
    }

    @Test
    public void testGetAll() {
        // Configurar el mock del repositorio para devolver una lista de categorías
        List<Categoria> categorias = Arrays.asList(categoria);
        when(ctRep.findAll()).thenReturn(categorias);

        // Llamar al método a probar
        List<CategoriaDTO> resultados = cServicio.getAll();

        // Verificar los resultados
        assertNotNull(resultados);
        assertFalse(resultados.isEmpty());
        assertEquals(categorias.size(), resultados.size());

        // Verificar la interacción con el mock
        verify(ctRep).findAll();
    }

    @Test
    public void testGetById() {
        // Configurar el mock del repositorio para devolver una categoría cuando se llame por ID
        when(ctRep.findById(anyInt())).thenReturn(Optional.of(categoria));

        // Llamar al método a probar
        CategoriaDTO resultado = cServicio.getById(1);

        // Verificar los resultados
        assertNotNull(resultado);
        assertEquals(categoriaDTO.getCtTitulo(), resultado.getCtTitulo());

        // Verificar la interacción con el mock
        verify(ctRep).findById(1);
    }

    @Test
    public void testUpdateCategoria() {
        // Configurar el mock del repositorio para devolver una categoría cuando se llame por ID
        when(ctRep.findById(anyInt())).thenReturn(Optional.of(categoria));
        // Configurar el mock del repositorio para simular la no existencia de la categoría por su título
        when(ctRep.existsByCtTitulo(anyString())).thenReturn(false);
        when(ctRep.save(any(Categoria.class))).thenReturn(categoria);

        // Llamar al método a probar
        CategoriaDTO resultado = cServicio.updateCategoria(1, categoriaDTO);

        // Verificar los resultados
        assertNotNull(resultado);
        assertEquals(categoriaDTO.getCtTitulo(), resultado.getCtTitulo());

        // Verificar la interacción con el mock
        verify(ctRep).findById(1);
        verify(ctRep).existsByCtTitulo(anyString());
        verify(ctRep).save(any(Categoria.class));
    }

    @Test
    public void testDeleteCategoria() {
        // Configurar el mock para devolver una categoría cuando se llame por ID
        when(ctRep.findById(anyInt())).thenReturn(Optional.of(categoria));

        // Llamar al método a probar
        assertDoesNotThrow(() -> cServicio.deleteCategoria(1));

        // Verificar la interacción con el mock
        verify(ctRep).findById(1);
        verify(ctRep).delete(any(Categoria.class));
    }
}
