package es.uv.pr.spring.trabajoFinal.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import es.uv.pr.spring.trabajoFinal.services.ProductorService;
import es.uv.pr.trabajoFinal.Productor;
import es.uv.pr.trabajoFinal.Productor.Estado;

@RestController
@RequestMapping(value = "/repo/productores", produces = MediaType.APPLICATION_JSON_VALUE)

public class ProductorController {

	@Autowired
	private ProductorService ps;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Productor createProductor(@RequestBody Productor p) {
		System.out.println(p.toString());
		//Crear un prodcutor a partir de los campos del object 
		p.setEstado(Estado.I);
		p.setCuota(0.0);
		ps.registrarProductor(p);
		return p;
	}

	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Productor updateProductor(@RequestBody Productor p) {
		 //Productor found = this.ps.getProductor(p.getId());
		return this.ps.updateProductor(p);
		
	}
	
	
	
	
	

}