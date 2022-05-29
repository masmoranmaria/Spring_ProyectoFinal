package com.Productores.repositories;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.Productores.domain.Fichero;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface FicherosRepository extends ReactiveMongoRepository<Fichero, String> {
	
	@Query("{'estado' : 'activo', 'titulo' : ?0}")
	Mono<Fichero> findByTitulo(String titulo);
	
	@Query("{'estado' : 'activo'}")
	Flux<Fichero> findAllActive();
	
}