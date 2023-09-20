package com.assessment.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.assessment.seguridad.CustomUserDetailsService;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTFilter extends OncePerRequestFilter {

	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	Claims claims = null;
	private String username = null;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if (request.getServletPath().matches("/user/login|user/forgotPassword|/user/signup")) {
			filterChain.doFilter(request, response);
		} else {
			String authorizationHeader = request.getHeader("Authorization");
			String token = null;
			if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
				token = authorizationHeader.substring(7);
				username = jwtUtil.extractUsername(token);
				claims = jwtUtil.extractClaims(token);
			}
			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
				if (jwtUtil.validateToken(token, userDetails)) {
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					new WebAuthenticationDetailsSource().buildDetails(request);
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
			}
			filterChain.doFilter(request, response);
		}
		;
	}

	public Boolean isAdmin() {
		return "admin".equalsIgnoreCase((String) claims.get("role"));
	}

	public Boolean isUser() {
		return "user".equalsIgnoreCase((String) claims.get("role"));
	}

	private String getCurrentUser() {
		return username;
	}
}
