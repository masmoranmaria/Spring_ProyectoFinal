package es.uv.pr.spring.trabajoFinal.controllers;

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
import es.uv.pr.spring.trabajoFinal.domain.Productor;
import es.uv.pr.spring.trabajoFinal.domain.Validador;
import es.uv.pr.spring.trabajoFinal.services.ValidadorService;


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
	public Productor updateProductor(@PathVariable("id") Integer id, @RequestBody Productor p) {
		 
		return this.vs.updateProductor(id, p);
		
	}
	
	@GetMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Productor deleteProductor(@PathVariable("id") Integer id) {
		
		return this.vs.deleteProductor(id);
		
	}

	@GetMapping("/{email}")
	@ResponseStatus(HttpStatus.CREATED)
	public Validador getValidadorByEmail(@PathVariable("email") String email) {
		
		return this.vs.getByEmail(email);
		
	}
}
