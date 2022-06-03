package com.Productores.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.Productores.domain.Productor;

@Component
public class CustomUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// Hacer peticion al repositorio
		System.out.println(email);
		String uri = "http://localhost:8080/repo/productores/" + email;
		RestTemplate rt = new RestTemplate();
		System.out.println("Hola");
		Productor result = rt.getForObject(uri, Productor.class);
		
		System.out.println(result.toString());
		if (result != null) {
			return new org.springframework.security.core.userdetails.User(result.getEmail(), result.getPassword(),
					getEstado(result));
		} else { throw new UsernameNotFoundException("El productor no existe");}

	}

	private static Collection<? extends GrantedAuthority> getEstado(Productor p) {
		//Crear un array de en vez con los roles con el estado (A , I , P), solo habrá uno
		String[] estado = new String[] { p.getEstado() };
		Collection<GrantedAuthority> estados = AuthorityUtils.createAuthorityList(estado);
		return estados;
	}

}
