package com.Productores.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.Productores.domain.Fichero;

import reactor.core.publisher.Mono;

@Repository
public interface FicherosRepository extends ReactiveMongoRepository<Fichero, String> {
	
	Mono<Fichero> findByTitulo(String titulo);
}