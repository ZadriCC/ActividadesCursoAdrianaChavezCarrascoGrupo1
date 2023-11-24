package com.adriana.tienda.servicios;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.adriana.tienda.datos.Notificacion;
import com.adriana.tienda.datos.Usuario;
import com.adriana.tienda.dto.NotificacionDTO;
import com.adriana.tienda.excepciones.CampoNuloOVacioException;
import com.adriana.tienda.excepciones.ErrorAppException;
import com.adriana.tienda.excepciones.RecursoNoEncontradoException;
import com.adriana.tienda.reposiorio.NRepositorio;
import com.adriana.tienda.reposiorio.URepositorio;
import com.adriana.tienda.servicio.NServicio;

@SpringBootTest
public class NServicioTest {

	@Mock
	private NRepositorio nRep;

	@Mock
	private URepositorio uRep;

	@InjectMocks
	private NServicio nSer;

	private Usuario user;
	private Notificacion noti;
	private NotificacionDTO nDTO;

	@BeforeEach
	public void setup() {

		user = new Usuario();
		user.setIdUsuario(1);

		noti = new Notificacion();
		noti.setIdNotificacion(1);
		noti.setnDescripcion("Hola, Bienvenido a ShopAll");
		noti.setnFecha(LocalDate.now());
		noti.setUsuario(user);

		nDTO = new NotificacionDTO();
		nDTO.setnDescripcion("Hola, Bienvenido a ShopAll");
	}

	@Test
    public void testAddNotificacion() {
    	
        when(uRep.findById(anyInt())).thenReturn(Optional.of(user));
        
        when(nRep.save(any(Notificacion.class))).thenAnswer(invocation -> {
            Notificacion noti = invocation.getArgument(0);
            noti.setIdNotificacion(1); // Simulate generated ID
            return noti;
        });
     NotificacionDTO resultado = nSer.addNotificacion(user.getIdUsuario(), nDTO);
     assertNotNull(resultado.getIdNotificacion());
     assertEquals(noti.getnDescripcion(), resultado.getnDescripcion());
     assertEquals(noti.getnFecha(), resultado.getnFecha());
     assertEquals(noti.getUsuario(), resultado.getUsuario());
     verify(nRep).save(any(Notificacion.class));
    }

	@Test
	public void testGetAllNotificaciones() {
		List<Notificacion> notificaciones = new ArrayList<>();
		notificaciones.add(new Notificacion());
		when(nRep.findAll()).thenReturn(notificaciones);

		List<NotificacionDTO> result = nSer.getAll();
		assertEquals(notificaciones.size(), result.size());
	}

	// Tests for other methods (getAllByUsuario, getById, updateNotificacion,
	// deleteNotificacion) can be added similarly
}
