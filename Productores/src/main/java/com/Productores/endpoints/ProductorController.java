package com.Productores.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.uv.pr.trabajoFinal.Productor;
import es.uv.pr.trabajoFinal.ProductorService;

@RestController
@RequestMapping(value="/api/productores", produces=MediaType.APPLICATION_JSON_VALUE)
public class ProductorController {
	
	//ProductorService productorService = new ProductorService();

	@PostMapping
	public ResponseEntity<Productor> registerProductor(@RequestBody Productor productor) {
		return new ResponseEntity<>(productor, HttpStatus.OK);
	}
}
