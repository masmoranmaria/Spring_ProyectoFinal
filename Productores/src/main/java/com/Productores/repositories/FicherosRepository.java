package com.Productores.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.Productores.domain.Fichero;

public interface FicherosRepository extends ReactiveMongoRepository<Fichero, String> {
	
}