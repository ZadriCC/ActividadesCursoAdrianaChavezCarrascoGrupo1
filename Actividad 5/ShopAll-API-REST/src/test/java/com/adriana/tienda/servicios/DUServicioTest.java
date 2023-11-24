package com.adriana.tienda.servicios;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.adriana.tienda.datos.DetalleUsuario;
import com.adriana.tienda.datos.Usuario;
import com.adriana.tienda.dto.DetalleUsuarioDTO;
import com.adriana.tienda.reposiorio.DURepositorio;
import com.adriana.tienda.reposiorio.URepositorio;
import com.adriana.tienda.servicio.DUServicio;

@ExtendWith(MockitoExtension.class)
public class DUServicioTest {

    @Mock
    private DURepositorio dtuRep;

    @Mock
    private URepositorio uRep;

    @InjectMocks
    private DUServicio duServicio;

    private DetalleUsuario detalleUsuario;
    private DetalleUsuarioDTO detalleUsuarioDTO;
    private Usuario usuario;

    @BeforeEach
    public void setup() {
        detalleUsuario = new DetalleUsuario();
        detalleUsuario.setIdDUsuario(1);
        detalleUsuario.setDuDireccion("Francisco I. Madero #245");
        detalleUsuario.setDuTelefono("1234567890");

        detalleUsuarioDTO = new DetalleUsuarioDTO();
        detalleUsuarioDTO.setIdDUsuario(1);
        detalleUsuarioDTO.setDuDireccion("Francisco I. Madero #245");
        detalleUsuarioDTO.setDuTelefono("1234567890");

        usuario = new Usuario();
        usuario.setIdUsuario(1);
    }

    @Test
    public void testAddDTUsuario() {
        // Configurar el mock del repositorio para simular el guardado exitoso
        when(uRep.findById(anyInt())).thenReturn(Optional.of(usuario));
        when(dtuRep.save(any(DetalleUsuario.class))).thenReturn(detalleUsuario);

        // Llamar al método a probar
        DetalleUsuarioDTO resultado = duServicio.addDTUsuario(1, detalleUsuarioDTO);

        // Verificar los resultados
        assertNotNull(resultado);
        assertEquals(detalleUsuarioDTO.getDuDireccion(), resultado.getDuDireccion());
        assertEquals(detalleUsuarioDTO.getDuTelefono(), resultado.getDuTelefono());

        // Verificar la interacción con el mock
        verify(uRep).findById(anyInt());
        verify(dtuRep).save(any(DetalleUsuario.class));
    }
}
