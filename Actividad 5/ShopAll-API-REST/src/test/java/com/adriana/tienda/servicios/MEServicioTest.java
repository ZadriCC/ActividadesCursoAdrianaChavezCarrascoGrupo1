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

import com.adriana.tienda.datos.MetodoEnvio;
import com.adriana.tienda.dto.MetodoEnvioDTO;
import com.adriana.tienda.excepciones.RecursoNoEncontradoException;
import com.adriana.tienda.reposiorio.MERepositorio;
import com.adriana.tienda.servicio.MEServicio;

@ExtendWith(MockitoExtension.class)
public class MEServicioTest {

    @Mock
    private MERepositorio meRep;

    @InjectMocks
    private MEServicio meServicio;

    private MetodoEnvio metodoEnvio;
    private MetodoEnvioDTO metodoEnvioDTO;

    @BeforeEach
    public void setup() {
        metodoEnvio = new MetodoEnvio();
        metodoEnvio.setIdMetodoEnvio(1);
        metodoEnvio.setMeTipo("Envio Express");
        metodoEnvio.setMePrecio(10.0);

        metodoEnvioDTO = new MetodoEnvioDTO();
        metodoEnvioDTO.setIdMetodoEnvio(1);
        metodoEnvioDTO.setMeTipo("Envio Express");
        metodoEnvioDTO.setMePrecio(10.0);
    }

    @Test
    public void testAddMEnvio() {
        // Configurar el mock del repositorio para simular el guardado exitoso
        when(meRep.save(any(MetodoEnvio.class))).thenReturn(metodoEnvio);

        // Llamar al método a probar
        MetodoEnvioDTO resultado = meServicio.addMEnvio(metodoEnvioDTO);

        // Verificar los resultados
        assertNotNull(resultado);
        assertEquals(metodoEnvioDTO.getMeTipo(), resultado.getMeTipo());
        assertEquals(metodoEnvioDTO.getMePrecio(), resultado.getMePrecio());

        // Verificar la interacción con el mock
        verify(meRep).save(any(MetodoEnvio.class));
    }

    @Test
    public void testGetAll() {
        // Configurar el mock del repositorio para devolver una lista de métodos de envío
        List<MetodoEnvio> metodosEnvio = Arrays.asList(metodoEnvio);
        when(meRep.findAll()).thenReturn(metodosEnvio);

        // Llamar al método a probar
        List<MetodoEnvioDTO> resultados = meServicio.getAll();

        // Verificar los resultados
        assertNotNull(resultados);
        assertFalse(resultados.isEmpty());
        assertEquals(metodosEnvio.size(), resultados.size());

        // Verificar la interacción con el mock
        verify(meRep).findAll();
    }

    @Test
    public void testGetMEnvioById() {
        // Configurar el mock del repositorio para devolver un método de envío cuando se llame por ID
        when(meRep.findById(anyInt())).thenReturn(Optional.of(metodoEnvio));

        // Llamar al método a probar
        MetodoEnvioDTO resultado = meServicio.getMEnvioById(1);

        // Verificar los resultados
        assertNotNull(resultado);
        assertEquals(metodoEnvioDTO.getMeTipo(), resultado.getMeTipo());
        assertEquals(metodoEnvioDTO.getMePrecio(), resultado.getMePrecio());

        // Verificar la interacción con el mock
        verify(meRep).findById(1);
    }

    @Test
    public void testUpdateMEnvio() {
        // Configurar el mock del repositorio para devolver un método de envío cuando se llame por ID
        when(meRep.findById(anyInt())).thenReturn(Optional.of(metodoEnvio));
        when(meRep.save(any(MetodoEnvio.class))).thenReturn(metodoEnvio);

        // Crear un nuevo DTO con datos actualizados
        MetodoEnvioDTO updatedDTO = new MetodoEnvioDTO();
        updatedDTO.setMeTipo("Envio a domicilio");
        updatedDTO.setMePrecio(20.0);

        // Llamar al método a probar
        MetodoEnvioDTO resultado = meServicio.updateMEnvio(1, updatedDTO);

        // Verificar los resultados
        assertNotNull(resultado);
        assertEquals(updatedDTO.getMeTipo(), resultado.getMeTipo());
        assertEquals(updatedDTO.getMePrecio(), resultado.getMePrecio());

        // Verificar la interacción con el mock
        verify(meRep).findById(1);
        verify(meRep).save(any(MetodoEnvio.class));
    }

    @Test
    public void testDeleteMEnvio() {
        // Configurar el mock para devolver un método de envío cuando se llame por ID
        when(meRep.findById(anyInt())).thenReturn(Optional.of(metodoEnvio));

        // Llamar al método a probar
        assertDoesNotThrow(() -> meServicio.deleteMEnvio(1));

        // Verificar la interacción con el mock
        verify(meRep).findById(1);
        verify(meRep).delete(any(MetodoEnvio.class));
    }
}
