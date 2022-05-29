package com.Productores.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
