package com.Productores.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.Productores.domain.Productor;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

@Component
public class TokenProvider {

	@Value("${sys.token.key}")
	private String key;

	@Value("${sys.token.issuer}")
	private String issuer;

	@Value("${sys.token.duration}")
	private Integer duration;

	private Algorithm algorithm;
	private JWTVerifier verifier;

	@PostConstruct
	public void init() {
		this.algorithm = Algorithm.HMAC256(this.key.getBytes());
		this.verifier = JWT.require(this.algorithm).build();
	}

	public String generateAccessToken(String username, List<String> claims) {
		return JWT.create().withSubject(username).withExpiresAt(new Date(System.currentTimeMillis() + this.duration))
				.withIssuer(this.issuer).withClaim("estado", claims).sign(this.algorithm);
	}

	public String generateRefreshToken(String username, List<String> claims) {
		return JWT.create().withSubject(username)
				.withExpiresAt(new Date(System.currentTimeMillis() + (this.duration * 2))).withIssuer(this.issuer)
				.withClaim("estado", claims).sign(this.algorithm);
	}

	public String getUsernameFromToken(String token) {
		DecodedJWT decoded = this.verifier.verify(token);
		return decoded.getSubject();
	}

	public Collection<SimpleGrantedAuthority> getAuthoritiesFromToken(String token) {
		DecodedJWT decoded = this.verifier.verify(token);
		String[] roles = decoded.getClaim("roles").asArray(String.class);
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for (String r : roles) {
			authorities.add(new SimpleGrantedAuthority(r));
		}
		return authorities;
	}

	public String[] getRolesFromToken(String token) {
		DecodedJWT decoded = this.verifier.verify(token);
		String[] roles = decoded.getClaim("roles").asArray(String.class);
		return roles;
	}

	public Boolean isTokenExpired(String token) {
		DecodedJWT decoded = this.verifier.verify(token);
		final Date expiration = decoded.getExpiresAt();
		return expiration.before(new Date());
	}

	public String getTokenFromHeader(String header) {
		return header.substring(this.getTokenHeaderPrefix().length());
	}

	public String getTokenHeaderPrefix() {
		return "Bearer ";
	}

	public Productor getProdByToken(String cabecera) {

		// SACAMOS EL USERNAME DEL TOKEN
		String token = getTokenFromHeader(cabecera);
		String email = getUsernameFromToken(token);

		// BUSCAMOS EL PRODUTOR
		String uri = "http://localhost:8083/repo/productores/" + email;
		RestTemplate rt = new RestTemplate();
		Productor p = rt.getForObject(uri, Productor.class);
		return p;
	}

}
