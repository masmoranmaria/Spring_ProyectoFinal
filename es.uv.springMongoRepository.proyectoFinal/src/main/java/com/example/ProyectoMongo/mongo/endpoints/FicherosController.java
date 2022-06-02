package com.example.ProyectoMongo.mongo.endpoints;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProyectoMongo.mongo.domain.Fichero;
import com.example.ProyectoMongo.mongo.domain.Productor;
import com.example.ProyectoMongo.mongo.services.FicherosService;

@RestController
@RequestMapping(value="/api/ficheros", produces=MediaType.APPLICATION_JSON_VALUE)
public class FicherosController {

	@Autowired
	private FicherosService ficheroService;
	
	@PostMapping
	public ResponseEntity<Fichero> saveFichero(@RequestBody Fichero fichero) {
		return ficheroService.saveFichero(fichero);
	}
	
	@GetMapping("/active")
	public ResponseEntity<List<Fichero>> getAllActive() {
		return ficheroService.getAllActive();
	}
	
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<Fichero> getByTitulo(@PathVariable("titulo") String titulo){
		return ficheroService.getByTitulo(titulo);
	}
	
	@PutMapping("/titulo/{titulo}")
	public ResponseEntity<Fichero> updateFichero(@PathVariable("titulo") String titulo, @RequestBody Fichero fichero) {
		return ficheroService.updateFichero(titulo, fichero);
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<Fichero> getFicheroById(@PathVariable("id") String id) {
		return ficheroService.getFicheroById(id);
	}
	
	@GetMapping("/preview/{id}")
	public ResponseEntity<Fichero> getPreviewById(@PathVariable("id") String id) {
		return ficheroService.getPreviewById(id);
	}
	
	@GetMapping("/listadoclaves")
	public ResponseEntity<List<Fichero>> getByPalabrasClave(@RequestParam String palabrasClave, @RequestParam String orderBy) {
		return ficheroService.getByPalabrasClave(palabrasClave, orderBy);
	}
	
	@GetMapping("/listadoproductor/{id}")
	public ResponseEntity<List<Fichero>> getByProductor(@PathVariable("id") String id) {
		String uri = "http://localhost:8080/api/CONSULTAPRODUCTORPORID" + titulo; // TODO
		RestTemplate rt = new RestTemplate();
		Trabajos[] trabajos = rt.getForObject(uri, Trabajos[].class);
		
		return ficheroService.getFicheroByTrabajos(trabajos);
	}
	
}














