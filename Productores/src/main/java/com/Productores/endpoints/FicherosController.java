package com.Productores.endpoints;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Productores.domain.Fichero;
import com.Productores.services.FicherosService;
import com.google.gson.Gson;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value="/api/ficheros", produces=MediaType.APPLICATION_JSON_VALUE)
public class FicherosController {
	
	@Autowired
	private FicherosService ficherosService;
	
	@PostMapping
    public ResponseEntity<Fichero> saveFichero(@RequestParam("file") MultipartFile file, @RequestParam("fichero") String ficheroJSON) {
		Gson gson = new Gson();
		Fichero fichero = gson.fromJson(ficheroJSON, Fichero.class);
				
        if (file.getOriginalFilename().contains(".doc")) {
            return new ResponseEntity<>(fichero, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        }

        try {
            String content = new String(file.getBytes());
            fichero.setContenido(content);
            
            ficherosService.saveFichero(fichero);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(fichero, HttpStatus.OK);
    }
	
	@GetMapping
	public ResponseEntity<Flux<Fichero>> getAllActive() {
		return new ResponseEntity<>(ficherosService.getAllActive(), HttpStatus.OK);
	}
	
	@PostMapping("/{titulo}")
	public ResponseEntity<Fichero> updateFichero(@PathVariable String titulo, @RequestBody Fichero fichero){
		return ficherosService.updateFichero(titulo, fichero);
		
	}
	 
}
