package es.uv.pr.spring.trabajoFinal.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
		ps.registrarProductor(p);
		return p;
	}

	@GetMapping
	public List<Productor> getProductores() {

		System.out.println("Aquí llegue");
		return this.ps.getProductores();

	}

}
