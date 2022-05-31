package es.uv.pr.spring.trabajoFinal.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;



import es.uv.pr.spring.trabajoFinal.repositories.ProductoresRepository;
import es.uv.pr.spring.trabajoFinal.services.ProductorService;
import es.uv.pr.trabajoFinal.Productor;

@RestController
@RequestMapping(value = "/api/validadores", produces = MediaType.APPLICATION_JSON_VALUE)
public class ValidadoresController {
	
	@Autowired
	ProductorService pr ; 
	
	@GetMapping
	@ResponseStatus(HttpStatus.CREATED)
	public List<Productor> getProductores() {
		
		System.out.println("Hola aquí llego");
		return pr.getProductores();
		
	}

//	@PostMapping
//	@ResponseStatus(HttpStatus.CREATED)
//	public Productor createProductor(@RequestBody Productor p) {
//		return this.ps.registrarProductor(p);
//	}

}
