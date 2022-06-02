package es.uv.pr.spring.trabajoFinal.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import es.uv.pr.spring.trabajoFinal.repositories.ProductoresRepository;
import es.uv.pr.spring.trabajoFinal.services.ProductorService;
import es.uv.pr.spring.trabajoFinal.services.ValidadorService;
import es.uv.pr.trabajoFinal.Productor;

@RestController
@RequestMapping(value = "/repo/validadores", produces = MediaType.APPLICATION_JSON_VALUE)
public class ValidadorController {

	@Autowired
	ValidadorService vs;
	
	
	
	@GetMapping
	@ResponseStatus(HttpStatus.CREATED)
	public List<Productor> getProductores() {

		return this.vs.getProductores();

	}

	@PostMapping("/validar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Double validarProductor(@PathVariable("id") Integer id, @RequestBody Double c) {
		Productor p = this.vs.validarProductor(id, c);
		return p.getCuota();

	}
	
	@PostMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Productor updateProductor(@RequestBody Productor p) {
		 
		return this.vs.updateProductor(p);
		
	}
	
	@GetMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Productor deleteProductor(@PathVariable("id") Integer id) {
		
		return this.vs.deleteProductor(id);
		
	}

}
