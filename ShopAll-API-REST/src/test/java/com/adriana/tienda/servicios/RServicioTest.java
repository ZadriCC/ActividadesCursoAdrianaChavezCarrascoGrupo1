package com.adriana.tienda.servicios;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
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
import com.adriana.tienda.datos.Reseña;
import com.adriana.tienda.datos.Usuario;
import com.adriana.tienda.dto.ReseñaDTO;
import com.adriana.tienda.reposiorio.PRepositorio;
import com.adriana.tienda.reposiorio.RRepositorio;
import com.adriana.tienda.reposiorio.URepositorio;
import com.adriana.tienda.servicio.RServicio;

@ExtendWith(MockitoExtension.class)
public class RServicioTest {

	@Mock
	private RRepositorio rRepositorio;

	@Mock
	private URepositorio uRepositorio;

	@Mock
	private PRepositorio pRepositorio;

	@InjectMocks
	private RServicio rServicio;

	private Reseña reseña;
	private ReseñaDTO reseñaDTO;
	private Usuario usuario;
	private Producto producto;

	@BeforeEach
	public void setup() {
		// Crear un usuario
		usuario = new Usuario();
		usuario.setIdUsuario(1);

		// Crear un producto
		producto = new Producto();
		producto.setIdProducto(1);

		// Crear una reseña y su DTO
		reseña = new Reseña();
		reseña.setIdReseña(1);
		reseña.setrComentario("Buena reseña");
		reseña.setrPuntuacion(5);
		reseña.setrFecha(LocalDate.now());
		reseña.setUsuario(usuario);
		reseña.setProducto(producto);

		reseñaDTO = new ReseñaDTO();
		reseñaDTO.setIdReseña(1);
		reseñaDTO.setrComentario("Buena reseña");
		reseñaDTO.setrPuntuacion(5);
		reseñaDTO.setrFecha(LocalDate.now());
	}

	@Test
    public void testAddReseña() {
        when(uRepositorio.findById(anyInt())).thenReturn(Optional.of(usuario));
        when(pRepositorio.findById(anyInt())).thenReturn(Optional.of(producto));
        when(rRepositorio.save(any(Reseña.class))).thenReturn(reseña);

        ReseñaDTO resultado = rServicio.addReseña(1, 1, reseñaDTO);

        assertNotNull(resultado);
        assertEquals(reseñaDTO.getIdReseña(), resultado.getIdReseña());
        assertEquals(reseñaDTO.getrComentario(), resultado.getrComentario());
        assertEquals(reseñaDTO.getrPuntuacion(), resultado.getrPuntuacion());
    }

	@Test
	public void testGetAll() {
		List<Reseña> reseñas = Arrays.asList(reseña);
		when(rRepositorio.findAll()).thenReturn(reseñas);

		List<ReseñaDTO> resultados = rServicio.getAll();

		assertNotNull(resultados);
		assertFalse(resultados.isEmpty());
		assertEquals(reseñas.size(), resultados.size());
	}

	@Test
	    public void testGetById() {
	        when(rRepositorio.findById(anyInt())).thenReturn(Optional.of(reseña));

	        ReseñaDTO resultado = rServicio.getById(1);

	        assertNotNull(resultado);
	        assertEquals(reseña.getIdReseña(), resultado.getIdReseña());
	        assertEquals(reseña.getrComentario(), resultado.getrComentario());
	        assertEquals(reseña.getrPuntuacion(), resultado.getrPuntuacion());

	    }

	@Test
	    public void testUpdateReseña() {
	        when(rRepositorio.findById(anyInt())).thenReturn(Optional.of(reseña));
	        when(rRepositorio.save(any(Reseña.class))).thenReturn(reseña);

	        ReseñaDTO reseñaActualizada = rServicio.updateReseña(1, reseñaDTO);

	        assertNotNull(reseñaActualizada);
	        assertEquals(reseñaDTO.getIdReseña(), reseñaActualizada.getIdReseña());
	        assertEquals(reseñaDTO.getrComentario(), reseñaActualizada.getrComentario());
	        assertEquals(reseñaDTO.getrPuntuacion(), reseñaActualizada.getrPuntuacion());
	
	    }

	@Test
	    public void testDeleteReseña() {
	        when(rRepositorio.findById(anyInt())).thenReturn(Optional.of(reseña));

	        assertDoesNotThrow(() -> rServicio.deleteReseña(1));
	        verify(rRepositorio).delete(reseña);
	    }
}
