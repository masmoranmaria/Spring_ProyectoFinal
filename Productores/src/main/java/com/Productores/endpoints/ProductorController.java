package com.Productores.endpoints;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.Productores.domain.Productor;

@RestController
@RequestMapping(value = "/api/productores", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductorController {
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Productor createProductor(@RequestBody Productor p) {
		
		String uri = "http://localhost:8080/repo/productores";
		RestTemplate rt = new RestTemplate();
		HttpEntity<Productor> request = new HttpEntity<>(p);
		Productor result = rt.postForObject(uri, request,  Productor.class);
		return result;
	}
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Productor updateProductor(@RequestBody Productor p) {
		
		String uri = "http://localhost:8080/repo/productores/"+p.getId();
		RestTemplate rt = new RestTemplate();
		HttpEntity<Productor> request = new HttpEntity<>(p);
		Productor result = rt.postForObject(uri, request,  Productor.class);
		return result;
	}


}
