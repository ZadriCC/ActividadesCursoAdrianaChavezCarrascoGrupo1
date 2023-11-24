package com.adriana.tienda.servicios;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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
import org.springframework.test.util.ReflectionTestUtils;

import com.adriana.tienda.datos.Usuario;
import com.adriana.tienda.dto.UsuarioDTO;
import com.adriana.tienda.reposiorio.URepositorio;
import com.adriana.tienda.servicio.NServicio;
import com.adriana.tienda.servicio.UServicio;

@ExtendWith(MockitoExtension.class)
public class UServicioTest {

	@Mock
	private URepositorio uRep;

	@InjectMocks
	private UServicio uSer;

	@Mock
	private NServicio nSer;
	
	private Usuario user;
	private UsuarioDTO uDTO;

	@BeforeEach
	public void setup() {

		user = new Usuario();
		user.setIdUsuario(1);
		user.setuNombre("Juan");
		user.setuApellidos("Lopez Perez");
		user.setuEmail("lo.perez@gmail.com");
		user.setuPassword("Contraseña123.");
		user.setuRol("comprador");
		user.setuFecha(LocalDate.now());

		uDTO = new UsuarioDTO();
		uDTO.setuNombre("Juan");
		uDTO.setuApellidos("Lopez Perez");
		uDTO.setuEmail("lo.perez@gmail.com");
		uDTO.setuPassword("Contraseña123.");

		// Inicializa manualmente nSer en uSer
		ReflectionTestUtils.setField(uSer, "nSer", nSer);
	}

	@Test
	//cuando el Usuario es valido retorna UsuarioDTO
    public void testAddUsuario() {
        // Configurar el mock del repositorio para que no haya duplicados
        when(uRep.existsByuEmail(anyString())).thenReturn(false);

        // Configurar el mock del repositorio para simular el guardado exitoso
        when(uRep.save(any(Usuario.class))).thenAnswer(invocation -> {
            Usuario user = invocation.getArgument(0);
            user.setIdUsuario(1); // Simulate generated ID
            return user;
        });
        
        // agregar un usuario
        UsuarioDTO resultado = uSer.addUsuario(uDTO);
        assertNotNull(resultado);
        assertNotNull(resultado.getIdUsuario());
        assertEquals(user.getuNombre(), resultado.getuNombre());
        assertEquals(user.getuApellidos(), resultado.getuApellidos());
        assertEquals(user.getuEmail(), resultado.getuEmail());
        assertEquals(user.getuPassword(), resultado.getuPassword());
        assertEquals(user.getuRol(), resultado.getuRol());
        assertEquals(user.getuFecha(), resultado.getuFecha());

        // Verify the interaction with the mock
        verify(uRep).save(any(Usuario.class));
    }

	@Test
	public void testGetAllUsuarios() {
		// Configuramos el mock para devolver una lista de usuarios
		List<Usuario> usuarios = Arrays.asList(user);
		when(uRep.findAll()).thenReturn(usuarios);

		// Llamamos al método a probar
		List<UsuarioDTO> resultados = uSer.getAll();

		// Verificamos los resultados
		assertNotNull(resultados);
		assertFalse(resultados.isEmpty());
		assertEquals(usuarios.size(), resultados.size());

		// Verificamos la interacción con el mock
		verify(uRep).findAll();
	}

	@Test
	public void testGetUsuarioById() {
	    // Configuramos el mock para devolver un usuario cuando se llame por ID
	    when(uRep.findById(anyInt())).thenReturn(Optional.of(user));

	    // Llamamos al método a probar
	    UsuarioDTO resultado = uSer.getById(1);

	    // Verificamos los resultados
	    assertNotNull(resultado);
	    assertEquals(user.getuNombre(), resultado.getuNombre());
	    assertEquals(user.getuApellidos(), resultado.getuApellidos());
	    assertEquals(user.getuEmail(), resultado.getuEmail());
	    assertEquals(user.getuPassword(), resultado.getuPassword());
	    assertEquals(user.getuRol(), resultado.getuRol());
	    assertEquals(user.getuFecha(), resultado.getuFecha());

	    // Verificamos la interacción con el mock
	    verify(uRep).findById(1);
	}

	@Test
	public void testUpdateUsuario() {
	    // Configuramos el mock para devolver un usuario cuando se llame por ID
	    when(uRep.findById(anyInt())).thenReturn(Optional.of(user));

	    // Configuramos el mock del repositorio para simular el guardado exitoso
	    when(uRep.save(any(Usuario.class))).thenReturn(user);

	    // Creamos un nuevo DTO con datos actualizados
	    UsuarioDTO updatedDTO = new UsuarioDTO();
	    updatedDTO.setuNombre("Juan Manuel");
	    updatedDTO.setuApellidos("Lopez Perez");
	    updatedDTO.setuEmail("lo.perez@gmail.com");
	    updatedDTO.setuPassword("Contraseña123.");

	    // Llamamos al método a probar
	    UsuarioDTO resultado = uSer.updateUsuario(updatedDTO, 1);

	    // Verificamos los resultados
	    assertNotNull(resultado);
	    assertEquals(updatedDTO.getuNombre(), resultado.getuNombre());
	    assertEquals(updatedDTO.getuApellidos(), resultado.getuApellidos());
        assertEquals(updatedDTO.getuEmail(), resultado.getuEmail());
        assertEquals(updatedDTO.getuPassword(), resultado.getuPassword());

	    // Verificamos la interacción con el mock
	    verify(uRep).findById(1);
	    verify(uRep).save(any(Usuario.class));
	}

	@Test
	public void testDeleteUsuario() {
	    // Simulamos que al llamar al método findById(1) retorna un usuario existente
	    when(uRep.findById(1)).thenReturn(Optional.of(user));

	    // Llamamos al método a probar
	    assertDoesNotThrow(() -> uSer.deleteUsuario(1));

	    // Verificamos la interacción con el mock
	    verify(uRep).findById(1);
	    verify(uRep).delete(any(Usuario.class));
	}
}
