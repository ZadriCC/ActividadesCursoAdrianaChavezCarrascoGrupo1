package com.adriana.tienda.controladores;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.adriana.tienda.controlador.UControlador;
import com.adriana.tienda.datos.Usuario;
import com.adriana.tienda.dto.UsuarioDTO;
import com.adriana.tienda.servicio.UServicio;

@ExtendWith(MockitoExtension.class)
public class UControladorTest {

	@Mock
	private UServicio uSer;

	@InjectMocks
	private UControlador uCon;

	private Usuario user1;
	private Usuario user2;
	private UsuarioDTO uDTO1;
	private UsuarioDTO uDTO2;

	@BeforeEach
	public void setup() {

		user1 = new Usuario();
		user1.setIdUsuario(1);
		user1.setuNombre("Juan");
		user1.setuApellidos("Lopez Perez");
		user1.setuEmail("lo.perez@gmail.com");
		user1.setuPassword("Contraseña123.");
		user1.setuRol("comprador");
		user1.setuFecha(LocalDate.now());

		uDTO1 = new UsuarioDTO();
		uDTO1.setuNombre("Juan");
		uDTO1.setuApellidos("Lopez Perez");
		uDTO1.setuEmail("lo.perez@gmail.com");
		uDTO1.setuPassword("Contraseña123.");

		user2 = new Usuario();
		user2.setIdUsuario(2);
		user2.setuNombre("Maria");
		user2.setuApellidos("Perez Lopez");
		user2.setuEmail("plm123@gmail.com");
		user2.setuPassword("P4Ss.0rd");
		user2.setuRol("comprador");
		user2.setuFecha(LocalDate.now());

		uDTO2 = new UsuarioDTO();
		uDTO2.setuNombre("Maria");
		uDTO2.setuApellidos("Perez Lopez");
		uDTO2.setuEmail("plm123@gmail.com");
		uDTO2.setuPassword("P4Ss.0rd");
	}

	@Test
	public void testGuardarUsuario() {

		when(uSer.addUsuario(any(UsuarioDTO.class))).thenReturn(uDTO1);

		ResponseEntity<UsuarioDTO> responseEntity = uCon.guardarUsuario(uDTO1);

		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		assertEquals(uDTO1, responseEntity.getBody());
		verify(uSer).addUsuario(any(UsuarioDTO.class));
	}

	@Test
	public void testListarUsuarios() {

		List<UsuarioDTO> usuariosDTO = Arrays.asList(uDTO1, uDTO2);

		when(uSer.getAll()).thenReturn(usuariosDTO);

		List<UsuarioDTO> response = uCon.listarUsuarios();

		assertEquals(2, response.size());
		assertEquals(usuariosDTO, response);
		verify(uSer).getAll();
	}

	@Test
	public void testUsuarioById() {
		int idUsuario = 1;

		when(uSer.getById(idUsuario)).thenReturn(uDTO1);

		ResponseEntity<UsuarioDTO> responseEntity = uCon.obtenerUsuariosPorId(idUsuario);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(uDTO1, responseEntity.getBody());
		verify(uSer).getById(idUsuario);
	}

	@Test
	public void testActualizarUsuario() {
		int idUsuario = 1;

		when(uSer.updateUsuario(any(UsuarioDTO.class), eq(idUsuario))).thenReturn(uDTO1);

		ResponseEntity<UsuarioDTO> responseEntity = uCon.actualizarUsuario(uDTO1, idUsuario);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(uDTO1, responseEntity.getBody());
		verify(uSer).updateUsuario(any(UsuarioDTO.class), eq(idUsuario));
	}

	@Test
	public void testEliminarUsuario() {
		int idUsuario = 1;

		ResponseEntity<String> responseEntity = uCon.eliminarUsuario(idUsuario);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals("Usuario eliminado exitosamente", responseEntity.getBody());
		verify(uSer).deleteUsuario(idUsuario);
	}
}