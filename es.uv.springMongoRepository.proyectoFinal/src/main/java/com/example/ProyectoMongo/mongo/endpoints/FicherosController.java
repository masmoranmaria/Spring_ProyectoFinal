package com.example.ProyectoMongo.mongo.endpoints;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.client.RestTemplate;

import com.example.ProyectoMongo.mongo.domain.Fichero;
import com.example.ProyectoMongo.mongo.domain.Trabajo;
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
	
	@GetMapping()
	public ResponseEntity<List<Fichero>> getAll() {
		return ficheroService.getAll();
	}
	
	@PostMapping("/update/fichero/{id}")
	public ResponseEntity<Fichero> updateFichero(@RequestBody Fichero fichero, @RequestParam("id") String id) {
		return ficheroService.updateFichero(fichero, id);
	}
	
	@GetMapping("/delete/{id}")
	public ResponseEntity<Fichero> deleteFichero(String id ) {
		return ficheroService.deleteFichero(id);
	}
	
	@GetMapping("/pendientes")
	public ResponseEntity<List<Fichero>> getFicherosPendientes() {
		return ficheroService.getFicherosPendientes();
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
		String uri = "http://localhost:8080/api/CONSULTAPRODUCTORPORID" + id; // TODO
		RestTemplate rt = new RestTemplate();
		Trabajo[] trabajos = rt.getForObject(uri, Trabajo[].class);
		
		return ficheroService.getByTrabajos(trabajos);
	}
	
}














