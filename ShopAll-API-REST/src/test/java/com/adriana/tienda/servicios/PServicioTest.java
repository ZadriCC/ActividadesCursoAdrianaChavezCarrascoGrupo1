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

import com.adriana.tienda.datos.Producto;
import com.adriana.tienda.datos.Subcategoria;
import com.adriana.tienda.datos.Vendedor;
import com.adriana.tienda.dto.ProductoDTO;
import com.adriana.tienda.reposiorio.PRepositorio;
import com.adriana.tienda.reposiorio.SRepositorio;
import com.adriana.tienda.reposiorio.VRepositorio;
import com.adriana.tienda.servicio.PServicio;

@ExtendWith(MockitoExtension.class)
public class PServicioTest {

    @Mock
    private PRepositorio pRepositorio;

    @Mock
    private VRepositorio vRepositorio;

    @Mock
    private SRepositorio sRepositorio;

    @InjectMocks
    private PServicio pServicio;

    private Producto producto;
    private ProductoDTO productoDTO;

    @BeforeEach
    public void setUp() {
        producto = new Producto();
        producto.setIdProducto(1);
        producto.setpNombre("Jabon liquido");
        producto.setpDescripcion("Jabon liquido para manos");
        producto.setpPrecio(30);
        producto.setpStock(10);

        productoDTO = new ProductoDTO();
        productoDTO.setpNombre("Jabon liquido");
        productoDTO.setpDescripcion("Jabon liquido para manos");
        productoDTO.setpPrecio(30);
        productoDTO.setpStock(10);
    }

    @Test
    public void testAddProductos() {
    	
        when(sRepositorio.findById(anyInt())).thenReturn(Optional.of(new Subcategoria()));
        when(vRepositorio.findById(anyInt())).thenReturn(Optional.of(new Vendedor()));
        when(pRepositorio.save(any(Producto.class))).thenReturn(producto);

        // Llama al método del servicio que agrega un producto
        ProductoDTO resultado = pServicio.addProductos(1, 1, productoDTO);

        // Verifica que se haya creado un ProductoDTO como resultado
        assertNotNull(resultado);
        // Verifica la interacción con el repositorio
        verify(sRepositorio).findById(anyInt());
        verify(vRepositorio).findById(anyInt());
        verify(pRepositorio).save(any(Producto.class));
    }

    @Test
    public void testGetAll() {
        when(pRepositorio.findAll()).thenReturn(Arrays.asList(producto));

        // Llama al método del servicio para obtener todos los productos
        List<ProductoDTO> resultados = pServicio.getAll();

        // Verifica que la lista de DTOs no esté vacía y tenga el tamaño esperado
        assertNotNull(resultados);
        assertFalse(resultados.isEmpty());
        assertEquals(1, resultados.size());

        // Verifica la interacción con el repositorio
        verify(pRepositorio).findAll();
    }
    @Test
    public void testGetById() {
        // Configura el comportamiento esperado del repositorio para obtener un producto por ID
        when(pRepositorio.findById(anyInt())).thenReturn(Optional.of(producto));

        // Llama al método del servicio para obtener un producto por ID
        ProductoDTO resultado = pServicio.getById(1);

        // Verifica que el DTO devuelto no sea nulo y tenga los valores esperados
        assertNotNull(resultado);
        // Verifica la interacción con el repositorio
        verify(pRepositorio).findById(anyInt());
    }

    @Test
    public void testUpdateProductos() {
        // Configura el comportamiento esperado del repositorio para actualizar un producto
        when(pRepositorio.findById(anyInt())).thenReturn(Optional.of(producto));
        when(pRepositorio.save(any(Producto.class))).thenReturn(producto);

        // Crea un DTO con datos actualizados
        ProductoDTO productoActualizadoDTO = new ProductoDTO();
        productoActualizadoDTO.setpNombre("Producto Actualizado");
        productoActualizadoDTO.setpDescripcion("Descripción Actualizada");
        productoActualizadoDTO.setpPrecio(199.99);
        productoActualizadoDTO.setpStock(20);

        // Llama al método del servicio para actualizar un producto
        ProductoDTO resultado = pServicio.updateProductos(1, productoActualizadoDTO);

        // Verifica que el DTO devuelto no sea nulo y tenga los valores esperados
        assertNotNull(resultado);
        // Verifica la interacción con el repositorio
        verify(pRepositorio).findById(anyInt());
        verify(pRepositorio).save(any(Producto.class));
    }

    @Test
    public void testDeleteProductos() {
        // Configura el comportamiento esperado del repositorio para eliminar un producto
        when(pRepositorio.findById(anyInt())).thenReturn(Optional.of(producto));

        // Llama al método del servicio para eliminar un producto
        assertDoesNotThrow(() -> pServicio.deleteProductos(1));

        // Verifica la interacción con el repositorio
        verify(pRepositorio).findById(anyInt());
        verify(pRepositorio).delete(any(Producto.class));
    }

}
