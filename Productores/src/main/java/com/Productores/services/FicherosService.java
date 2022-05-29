package com.Productores.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.Productores.domain.Fichero;
import com.Productores.repositories.FicherosRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FicherosService {
	
	@Autowired
	private FicherosRepository ficheroRepository;
	
	public Mono<Fichero> saveFichero(Fichero fichero) {
		Mono<Fichero> entity = ficheroRepository.save(fichero);
		entity.subscribe();
		return entity;
	}
	
	public Flux<Fichero> getAllActive() {
		return ficheroRepository.findAllActive();
	}
	
	public Mono<Fichero> getByTitulo(String titulo) {
		return ficheroRepository.findByTitulo(titulo);
	}
	
	public ResponseEntity<Fichero> updateFichero(String titulo, Fichero fichero){
		Mono<Fichero> mono = this.getByTitulo(titulo);
		
		Fichero ficheroActual = mono.block();
		
		if(!ficheroActual.getEstado().equals("activo"))
			return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
		
		ficheroActual.setTitulo(fichero.getTitulo());
		ficheroActual.setDescripcion(fichero.getDescripcion());
		ficheroActual.setPalabrasClave(fichero.getPalabrasClave());
		
		Mono<Fichero> ficheroNuevo = ficheroRepository.save(ficheroActual);
		ficheroNuevo.subscribe();
		
		return new ResponseEntity<>(ficheroActual, HttpStatus.OK);
		
	}
	
}
