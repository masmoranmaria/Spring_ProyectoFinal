package es.uv.pr.spring.trabajoFinal.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import es.uv.pr.spring.trabajoFinal.domain.Productor;
import es.uv.pr.spring.trabajoFinal.domain.Trabajo;
import es.uv.pr.spring.trabajoFinal.domain.Validador;
import es.uv.pr.spring.trabajoFinal.services.ProductorService;
import es.uv.pr.spring.trabajoFinal.services.TrabajoService;
import es.uv.pr.spring.trabajoFinal.services.ValidadorService;

@RestController
@RequestMapping(value = "/repo/trabajos", produces = MediaType.APPLICATION_JSON_VALUE)
public class TrabajoController {

	@Autowired
	TrabajoService ts;

	@Autowired
	ValidadorService vs;

	@Autowired
	ProductorService ps;

	@PostMapping
	public ResponseEntity<Trabajo> createTrabajo(@RequestBody Trabajo t) {

		return new ResponseEntity<>(this.ts.createTrabajo(t), HttpStatus.CREATED);

	}

	@PostMapping("/productor")
	public ResponseEntity<List<Trabajo>> getByProductorId(@RequestBody Productor p) {

		return new ResponseEntity<>(this.ts.getByProdId(p), HttpStatus.CREATED);

	}

}
