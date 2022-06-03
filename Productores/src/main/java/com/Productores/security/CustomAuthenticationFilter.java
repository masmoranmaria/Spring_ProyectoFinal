package com.Productores.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import com.Productores.domain.Productor;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.Productores.security.TokenProvider;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;

	private String sysKey = "aSecureKeyGoesHere";

	@Autowired
	private TokenProvider tp;

	public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		// campos para autenticarse
		UsernamePasswordAuthenticationToken authtoken = new UsernamePasswordAuthenticationToken(
				request.getParameter("username"), request.getParameter("password"));
		System.out.println(authtoken);
		return this.authenticationManager.authenticate(authtoken);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication auth) throws IOException, ServletException {

		System.out.println("Hola desde succesfulAuth");
		User u = (User) auth.getPrincipal();
		System.out.println("Hola desde succesfulAuth" + u.getUsername());
		Algorithm alg = Algorithm.HMAC256(sysKey.getBytes());
		// EMPLEANDO CLASE TOKEN PROVIDER
		System.out.println(this.tp);

		String access_token = JWT.create().withSubject(u.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
				.withIssuer(request.getRequestURL().toString())
				.withClaim("estados",
						u.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.sign(alg);

		String refresh_token = JWT.create().withSubject(u.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
				.withIssuer(request.getRequestURL().toString())
				.withClaim("estados",
						u.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.sign(alg);
		// SERIALIZAMOS PARA REENVIAR LOS TOKENS
		response.setHeader("access_token", access_token);
		response.setHeader("refresh_token", refresh_token);
		Map<String, String> tokens = new HashMap<>();
		tokens.put("access_token", access_token);
		tokens.put("refresh_token", refresh_token);

		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		new ObjectMapper().writeValue(response.getOutputStream(), tokens);
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {

		super.unsuccessfulAuthentication(request, response, failed);

	}

}
