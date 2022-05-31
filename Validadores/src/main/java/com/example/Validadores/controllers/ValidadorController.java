package com.example.Validadores.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/api/validadores", produces = MediaType.APPLICATION_JSON_VALUE)
public class ValidadorController {
	
	@GetMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Object[] getProductores() {
		//hacer la petición
		String uri = "http://localhost:8080/repo/validadores";
		RestTemplate rt = new RestTemplate();
		Object[] result = rt.getForObject(uri, Object[].class);
		return result;
		
	}
}