package com.adriana.tienda.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.adriana.tienda.datos.Usuario;
import com.adriana.tienda.dto.CredencialesDto;
import com.adriana.tienda.dto.RespuestGenerica;
import com.adriana.tienda.excepciones.RecursoNoEncontradoException;
import com.adriana.tienda.reposiorio.URepositorio;
import com.adriana.tienda.utilidades.Constantes;

@Service
public class AutenticacionServicio {

	@Autowired
	private URepositorio uRep;

	@Autowired
	private PasswordEncoder passEnco;

	@Autowired
	private JwtService jwtService;

	public RespuestGenerica getTokenUsuario(CredencialesDto credencialesdto) {
		RespuestGenerica respuesta = new RespuestGenerica();
		Usuario user = uRep.findByuEmail(credencialesdto.getUsuario())
				.orElseThrow(() -> new RecursoNoEncontradoException("Email", "uEmail", credencialesdto.getUsuario()));
		boolean contraseñasIguales = passEnco.matches(credencialesdto.getContraseña(), user.getuPassword());
		if (contraseñasIguales) {
			String token = jwtService.generateToken(credencialesdto.getUsuario());
			respuesta.setMensaje(Constantes.MENSAJE_TOKEN_EXITO);
			respuesta.getDatos().add(token);
			respuesta.setExito(true);
		} else {
			respuesta.setExito(false);
			respuesta.setMensaje(Constantes.USUARIO_CONTRASEÑA_INCORRECTA);
		}
		return respuesta;
	}
}
