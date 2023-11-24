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

import com.adriana.tienda.datos.MetodoPago;
import com.adriana.tienda.dto.MetodoPagoDTO;
import com.adriana.tienda.reposiorio.MPRepositorio;
import com.adriana.tienda.servicio.MPServicio;

@ExtendWith(MockitoExtension.class)
public class MPServicioTest {

    @Mock
    private MPRepositorio mpRep;

    @InjectMocks
    private MPServicio mpServicio;

    private MetodoPago metodoPago;
    private MetodoPagoDTO metodoPagoDTO;

    @BeforeEach
    public void setup() {
        metodoPago = new MetodoPago();
        metodoPago.setIdMetodoPago(1);
        metodoPago.setMpTipo("Tarjeta");

        metodoPagoDTO = new MetodoPagoDTO();
        metodoPagoDTO.setIdMetodoPago(1);
        metodoPagoDTO.setMpTipo("Tarjeta");
    }

    @Test
    public void testAddMPago() {
        // Configurar el mock del repositorio para simular el guardado exitoso
        when(mpRep.save(any(MetodoPago.class))).thenReturn(metodoPago);

        // Llamar al método a probar
        MetodoPagoDTO resultado = mpServicio.addMPago(metodoPagoDTO);

        // Verificar los resultados
        assertNotNull(resultado);
        assertEquals(metodoPagoDTO.getMpTipo(), resultado.getMpTipo());

        // Verificar la interacción con el mock
        verify(mpRep).save(any(MetodoPago.class));
    }

    @Test
    public void testGetAll() {
        // Configurar el mock del repositorio para devolver una lista de métodos de pago
        List<MetodoPago> metodosPago = Arrays.asList(metodoPago);
        when(mpRep.findAll()).thenReturn(metodosPago);

        // Llamar al método a probar
        List<MetodoPagoDTO> resultados = mpServicio.getAll();

        // Verificar los resultados
        assertNotNull(resultados);
        assertFalse(resultados.isEmpty());
        assertEquals(metodosPago.size(), resultados.size());

        // Verificar la interacción con el mock
        verify(mpRep).findAll();
    }

    @Test
    public void testGetMPagoById() {
        // Configurar el mock del repositorio para devolver un método de pago cuando se llame por ID
        when(mpRep.findById(anyInt())).thenReturn(Optional.of(metodoPago));

        // Llamar al método a probar
        MetodoPagoDTO resultado = mpServicio.getMPagoById(1);

        // Verificar los resultados
        assertNotNull(resultado);
        assertEquals(metodoPagoDTO.getMpTipo(), resultado.getMpTipo());

        // Verificar la interacción con el mock
        verify(mpRep).findById(1);
    }

    @Test
    public void testUpdateMPago() {
        // Configurar el mock del repositorio para devolver un método de pago cuando se llame por ID
        when(mpRep.findById(anyInt())).thenReturn(Optional.of(metodoPago));
        when(mpRep.save(any(MetodoPago.class))).thenReturn(metodoPago);

        // Crear un nuevo DTO con datos actualizados
        MetodoPagoDTO updatedDTO = new MetodoPagoDTO();
        updatedDTO.setMpTipo("Tarjeta");

        // Llamar al método a probar
        MetodoPagoDTO resultado = mpServicio.updateMPago(1, updatedDTO);

        // Verificar los resultados
        assertNotNull(resultado);
        assertEquals(updatedDTO.getMpTipo(), resultado.getMpTipo());

        // Verificar la interacción con el mock
        verify(mpRep).findById(1);
        verify(mpRep).save(any(MetodoPago.class));
    }

    @Test
    public void testDeleteMPago() {
        // Configurar el mock para devolver un método de pago cuando se llame por ID
        when(mpRep.findById(anyInt())).thenReturn(Optional.of(metodoPago));

        // Llamar al método a probar
        assertDoesNotThrow(() -> mpServicio.deleteMPago(1));

        // Verificar la interacción con el mock
        verify(mpRep).findById(1);
        verify(mpRep).delete(any(MetodoPago.class));
    }
}
