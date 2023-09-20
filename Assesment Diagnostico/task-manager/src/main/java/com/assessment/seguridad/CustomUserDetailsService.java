package com.assessment.seguridad;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.assessment.entidades.Rol;
import com.assessment.entidades.Usuario;
import com.assessment.repositorio.UsuarioRepositorio;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepositorio.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con " + usernameOrEmail));
		return new User(usuario.getEmail(), usuario.getPassword(), mapearRoles(usuario.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapearRoles(Set<Rol> roles) {
		return roles.stream().map(rol -> new SimpleGrantedAuthority(rol.getNombre())).collect(Collectors.toList());
	}

}
