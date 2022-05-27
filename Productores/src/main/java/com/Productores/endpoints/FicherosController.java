package com.Productores.endpoints;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Productores.domain.Fichero;
import com.Productores.domain.Productor;

@RestController
@RequestMapping(value="/api/ficheros", produces=MediaType.APPLICATION_JSON_VALUE)
public class FicherosController {
	
	

	@PostMapping
	public ResponseEntity<Fichero> saveFichero(@RequestBody Fichero fichero) {
		
		
		
		return new ResponseEntity<>(fichero, HttpStatus.OK);
	}
	
}
