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

import com.adriana.tienda.datos.Pedido;
import com.adriana.tienda.datos.Usuario;
import com.adriana.tienda.dto.PedidoDTO;
import com.adriana.tienda.reposiorio.PdRepositorio;
import com.adriana.tienda.reposiorio.URepositorio;
import com.adriana.tienda.servicio.PdServicio;

@ExtendWith(MockitoExtension.class)
public class PdServicioTest {

    @Mock
    private PdRepositorio pdRepositorio;

    @Mock
    private URepositorio uRepositorio;

    @InjectMocks
    private PdServicio pdServicio;

    private Pedido pedido;
    private PedidoDTO pedidoDTO;
    private Usuario usuario;

    @BeforeEach
    public void setup() {
        usuario = new Usuario();
        usuario.setIdUsuario(1);

        pedido = new Pedido();
        pedido.setIdPedido(1);
        pedido.setPdEstado("pendiente");
        pedido.setPdSubtotal(0);
        pedido.setUsuario(usuario);

        pedidoDTO = new PedidoDTO();
        pedidoDTO.setIdPedido(1);
        pedidoDTO.setPdEstado("pendiente");
        pedidoDTO.setPdSubtotal(0);
    }

    @Test
    public void testAddPedido() {
        int idUsuario = 1;

        when(uRepositorio.findById(anyInt())).thenReturn(Optional.of(usuario));
        when(pdRepositorio.save(any(Pedido.class))).thenReturn(pedido);

        PedidoDTO resultado = pdServicio.addPedido(idUsuario, pedidoDTO);

        assertNotNull(resultado);
        assertEquals(pedidoDTO.getIdPedido(), resultado.getIdPedido());
        assertEquals(pedidoDTO.getPdEstado(), resultado.getPdEstado());
        assertEquals(pedidoDTO.getPdSubtotal(), resultado.getPdSubtotal());

        verify(uRepositorio).findById(idUsuario);
        verify(pdRepositorio).save(any(Pedido.class));
    }

    @Test
    public void testGetAll() {
        List<Pedido> pedidos = Arrays.asList(pedido);
        when(pdRepositorio.findAll()).thenReturn(pedidos);

        List<PedidoDTO> resultados = pdServicio.getAll();

        assertNotNull(resultados);
        assertFalse(resultados.isEmpty());
        assertEquals(pedidos.size(), resultados.size());

        verify(pdRepositorio).findAll();
    }

    @Test
    public void testGetById() {
        when(pdRepositorio.findById(anyInt())).thenReturn(Optional.of(pedido));

        PedidoDTO resultado = pdServicio.getById(1);

        assertNotNull(resultado);
        assertEquals(pedidoDTO.getIdPedido(), resultado.getIdPedido());
        assertEquals(pedidoDTO.getPdEstado(), resultado.getPdEstado());
        assertEquals(pedidoDTO.getPdSubtotal(), resultado.getPdSubtotal());

        verify(pdRepositorio).findById(1);
    }

    @Test
    public void testDeletePedido() {
        when(pdRepositorio.findById(anyInt())).thenReturn(Optional.of(pedido));
        doNothing().when(pdRepositorio).delete(any(Pedido.class));

        assertDoesNotThrow(() -> pdServicio.deletePedido(1));

        verify(pdRepositorio).findById(1);
        verify(pdRepositorio).delete(any(Pedido.class));
    }
}