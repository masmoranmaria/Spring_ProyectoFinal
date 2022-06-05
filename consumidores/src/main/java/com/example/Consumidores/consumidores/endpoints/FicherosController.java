package com.example.Consumidores.consumidores.endpoints;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
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
import org.springframework.web.client.RestTemplate;

import com.example.Consumidores.consumidores.domain.Fichero;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class FicherosController {

	String mongoUri = "http://mongo-microservice:8080/api/ficheros/";
	
	@GetMapping("/pruebas")
	public ResponseEntity<String> prueba() {
		String uri = mongoUri + "pruebas";
		RestTemplate rt = new RestTemplate();
		String string = rt.getForObject(uri, String.class);

		return new ResponseEntity<>(string, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Fichero> saveFichero(@RequestBody Fichero fichero) {
		String uri = mongoUri;
		RestTemplate rt = new RestTemplate();
		HttpEntity<Fichero> request = new HttpEntity<>(fichero);

		Fichero res = rt.postForObject(uri, request, Fichero.class);
		
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@GetMapping("/active")
	public ResponseEntity<List<Fichero>> getAllActive() {
		String uri = mongoUri + "active";
		RestTemplate rt = new RestTemplate();

		ArrayList<Fichero> ficheros = rt.getForObject(uri, ArrayList.class);
		
		return new ResponseEntity<>(ficheros, HttpStatus.OK);
	}
	
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<Fichero> getByTitulo(@PathVariable("titulo") String titulo){
		String uri = mongoUri + "titulo/" + titulo;
		RestTemplate rt = new RestTemplate();
		Fichero fichero = rt.getForObject(uri, Fichero.class);
		
		return new ResponseEntity<>(fichero, HttpStatus.OK);
	}
	
	@PutMapping("/titulo/{titulo}")
	public ResponseEntity<Fichero> updateFichero(@PathVariable("titulo") String titulo, @RequestBody Fichero fichero) {
		String uri = mongoUri + "titulo/" + titulo;
		RestTemplate rt = new RestTemplate();
		rt.put(uri, fichero, ResponseEntity.class);
		
		return new ResponseEntity<>(fichero, HttpStatus.OK);
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<Fichero> getFicheroById(@PathVariable("id") String id) {
		String uri = mongoUri + "id/" + id;
		RestTemplate rt = new RestTemplate();
		Fichero fichero = rt.getForObject(uri, Fichero.class);
		
		return new ResponseEntity<>(fichero, HttpStatus.OK);
	}
	
	@GetMapping("/preview/{id}")
	public ResponseEntity<Fichero> getPreviewById(@PathVariable("id") String id) {
		String uri = mongoUri + "preview/" + id;
		RestTemplate rt = new RestTemplate();
		Fichero fichero = rt.getForObject(uri, Fichero.class);
		
		return new ResponseEntity<>(fichero, HttpStatus.OK);
	}
	
	@GetMapping("/listadoclaves/{palabrasClave}")
	public ResponseEntity<Fichero> getPalabrasclave(@RequestParam("palabrasClave") String palabrasClave, @RequestParam("orderby") String orderBy){
		String uri = mongoUri + "listadoclaves?palabrasClave=" + palabrasClave + "&orderby=" + orderBy;
		RestTemplate rt = new RestTemplate();
		Fichero fichero = rt.getForObject(uri, Fichero.class);
		
		return new ResponseEntity<>(fichero, HttpStatus.OK);
	}
	
}
