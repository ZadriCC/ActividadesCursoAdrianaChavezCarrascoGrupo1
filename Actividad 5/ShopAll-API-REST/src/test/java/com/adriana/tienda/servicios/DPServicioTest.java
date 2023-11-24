package com.adriana.tienda.servicios;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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
import org.springframework.test.util.ReflectionTestUtils;

import com.adriana.tienda.datos.DetalleProducto;
import com.adriana.tienda.datos.Producto;
import com.adriana.tienda.dto.DetalleProductoDTO;
import com.adriana.tienda.excepciones.RecursoNoEncontradoException;
import com.adriana.tienda.reposiorio.DPRepositorio;
import com.adriana.tienda.reposiorio.PRepositorio;
import com.adriana.tienda.servicio.DPServicio;

@ExtendWith(MockitoExtension.class)
public class DPServicioTest {

	@Mock
	private DPRepositorio dpRep;

	@Mock
	private PRepositorio pRep;

	@InjectMocks
	private DPServicio dpServicio;

	private DetalleProducto detalleProducto;
	private DetalleProductoDTO detalleProductoDTO;

	@BeforeEach
	public void setup() {
		detalleProducto = new DetalleProducto();
		detalleProducto.setIdDProducto(1);
		detalleProducto.setDpAtributo("Color");
		detalleProducto.setDpValor("Rojo");

		detalleProductoDTO = new DetalleProductoDTO();
		detalleProductoDTO.setIdDProducto(1);
		detalleProductoDTO.setDpAtributo("Color");
		detalleProductoDTO.setDpValor("Rojo");
	}

	@Test
	public void testAddDProducto() {
		Producto producto = new Producto();
		producto.setIdProducto(1);

		when(pRep.findById(any(Integer.class))).thenReturn(Optional.of(producto));
		when(dpRep.save(any(DetalleProducto.class))).thenReturn(detalleProducto);

		DetalleProductoDTO resultado = dpServicio.addDProducto(1, detalleProductoDTO);
		assertNotNull(resultado);
		assertEquals(detalleProductoDTO.getIdDProducto(), resultado.getIdDProducto());
		assertEquals(detalleProductoDTO.getDpAtributo(), resultado.getDpAtributo());
		assertEquals(detalleProductoDTO.getDpValor(), resultado.getDpValor());

		verify(pRep).findById(any(Integer.class));
		verify(dpRep).save(any(DetalleProducto.class));
	}

	@Test
	public void testGetAllDProductos() {
		List<DetalleProducto> detallesProductos = Arrays.asList(detalleProducto);
		when(dpRep.findAll()).thenReturn(detallesProductos);

		List<DetalleProductoDTO> resultados = dpServicio.getAll();
		assertNotNull(resultados);
		assertFalse(resultados.isEmpty());
		assertEquals(detallesProductos.size(), resultados.size());

		verify(dpRep).findAll();
	}

	@Test
    public void testUpdateDetalleProducto() {
        when(dpRep.findById(any(Integer.class))).thenReturn(Optional.of(detalleProducto));
        when(dpRep.save(any(DetalleProducto.class))).thenReturn(detalleProducto);

        DetalleProductoDTO updatedDTO = new DetalleProductoDTO();
        updatedDTO.setDpAtributo("Tamaño");
        updatedDTO.setDpValor("Grande");

        DetalleProductoDTO resultado = dpServicio.updateDProducto(1, updatedDTO);
        assertNotNull(resultado);
        assertEquals(updatedDTO.getDpAtributo(), resultado.getDpAtributo());
        assertEquals(updatedDTO.getDpValor(), resultado.getDpValor());

        verify(dpRep).findById(any(Integer.class));
        verify(dpRep).save(any(DetalleProducto.class));
    }

	@Test
    public void testDeleteDetalleProducto() {
        when(dpRep.findById(any(Integer.class))).thenReturn(Optional.of(detalleProducto));

        assertDoesNotThrow(() -> dpServicio.deleteDProducto(1));

        verify(dpRep).findById(any(Integer.class));
        verify(dpRep).delete(any(DetalleProducto.class));
    }
}