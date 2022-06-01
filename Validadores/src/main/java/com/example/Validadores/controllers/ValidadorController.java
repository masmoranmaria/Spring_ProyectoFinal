package com.example.Validadores.controllers;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/api/validadores", produces = MediaType.APPLICATION_JSON_VALUE)
public class ValidadorController {

	@GetMapping("/productores")
	@ResponseStatus(HttpStatus.CREATED)
	public Object[] getProductores() {
		// hacer la petición
		String uri = "http://localhost:8080/repo/validadores";
		RestTemplate rt = new RestTemplate();
		Object[] result = rt.getForObject(uri, Object[].class);
		return result;

	}

	@PostMapping("/validar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> approbarProductor(@PathVariable("id") Long id, @RequestBody Double c) {
		// hacer la petición
		System.out.println("Hola");
		try {
			String uri = "http://localhost:8080/repo/validadores/validar/"+id;
			RestTemplate rt = new RestTemplate();
			HttpEntity<Double> request = new HttpEntity<>(c);
			Double result = rt.postForObject(uri, request, Double.class);
			if (result != null) {
				return new ResponseEntity<>("Validado con éxito", HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>("Productor no disponible", HttpStatus.NOT_FOUND);
			}

		} catch (Error e) {
			return new ResponseEntity<>("Ha habido un problema", HttpStatus.BAD_REQUEST);
		}

	}
}