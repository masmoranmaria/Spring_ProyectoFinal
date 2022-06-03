package com.example.Validadores.security;

import java.util.Collection;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


import com.example.Validadores.domain.Validador;

@Component
public class CustomUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// Hacer peticion al repositorio
		String uri = "http://localhost:8080/repo/validadores/" + email;
		RestTemplate rt = new RestTemplate();
		Validador result = rt.getForObject(uri, Validador.class);
		if (result != null) {
			//El validador no tiene authorities
			return new org.springframework.security.core.userdetails.User(result.getEmail(), result.getPassword(), null);
		} else { throw new UsernameNotFoundException("El productor no existe");}

	}

}
