package com.assessment.configuracion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import com.assessment.jwt.JWTFilter;
import com.assessment.seguridad.CustomUserDetailsService;

import jakarta.security.auth.message.callback.PrivateKeyCallback.Request;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private JWTFilter jwtFilter;

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	protected SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues()).and()
				.csrf().disable().authorizeHttpRequests()
				.requestMatchers("/user/login", "/user/signup", "/user/forgotPassword").permitAll().anyRequest()
				.authenticated().and().exceptionHandling().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		return httpSecurity.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

}
