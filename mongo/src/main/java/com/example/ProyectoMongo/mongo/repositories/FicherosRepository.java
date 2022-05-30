package com.example.ProyectoMongo.mongo.repositories;

import com.example.ProyectoMongo.mongo.domain.Fichero;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface FicherosRepository extends ReactiveMongoRepository<Fichero, String> {
	
	@Query("{'estado' : 'activo', 'titulo' : ?0}")
	Mono<Fichero> findByTitulo(String titulo);
	
	@Query("{'estado' : 'activo'}")
	Flux<Fichero> findAllActive();
	
}