package com.example.Consumidores.consumidores.endpoints;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.Consumidores.consumidores.domain.Fichero;

@RestController("/api/ficheros")
public class FicherosController {
	
	
	@PostMapping
	public ResponseEntity<Fichero> saveFichero(@RequestBody Fichero fichero) {
		String uri = "http://localhost:8080/repo/productores";
		RestTemplate rt = new RestTemplate();
		HttpEntity<Fichero> request = new HttpEntity<>(fichero);
		
		return rt.postForObject(uri, request, ResponseEntity.class);
	}
	
	@GetMapping("/active")
	public ResponseEntity<List<Fichero>> getAllActive() {
		String uri = "http://localhost:8080/api/ficheros";
		RestTemplate rt = new RestTemplate();
		
		return rt.getForObject(uri, ResponseEntity.class);
	}
	
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<Fichero> getByTitulo(@PathVariable("titulo") String titulo){
		String uri = "http://localhost:8080/api/ficheros/titulo/" + titulo;
		RestTemplate rt = new RestTemplate();
		
		return rt.getForObject(uri, ResponseEntity.class);
	}
	
	@PutMapping("/titulo/{titulo}")
	public ResponseEntity<Fichero> updateFichero(@PathVariable("titulo") String titulo, @RequestBody Fichero fichero) {
		String uri = "http://localhost:8080/api/ficheros/titulo/" + titulo;
		RestTemplate rt = new RestTemplate();
		rt.put(uri, fichero, ResponseEntity.class);
		
		return new ResponseEntity<>(fichero, HttpStatus.OK);
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<Fichero> getFicheroById(@PathVariable("id") String id) {
		String uri = "http://localhost:8080/api/ficheros/id/" + id;
		RestTemplate rt = new RestTemplate();
		
		return rt.getForObject(uri, ResponseEntity.class);
	}
	
	@GetMapping("/preview/{id}")
	public ResponseEntity<Fichero> getPreviewById(@PathVariable("id") String id) {
		String uri = "http://localhost:8080/api/ficheros/preview/" + id;
		RestTemplate rt = new RestTemplate();
		
		return rt.getForObject(uri, ResponseEntity.class);
	}
	
	@GetMapping("/listadoclaves/{palabrasClave}")
	public ResponseEntity<Fichero> getPalabrasclave(@RequestParam("palabrasClave") String palabrasClave, @RequestParam("orderby") String orderBy){
		String uri = "http://localhost:8080/api/ficheros/listadoclaves?palabrasClave=" + palabrasClave + "&orderby=" + orderBy;
		RestTemplate rt = new RestTemplate();
		
		return rt.getForObject(uri, ResponseEntity.class);
	}
	
}
