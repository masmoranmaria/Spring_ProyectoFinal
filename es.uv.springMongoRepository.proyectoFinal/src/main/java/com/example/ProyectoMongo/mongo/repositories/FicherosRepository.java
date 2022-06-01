package com.example.ProyectoMongo.mongo.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.ProyectoMongo.mongo.domain.Fichero;

@Repository
public interface FicherosRepository extends MongoRepository<Fichero, String> {
	
	@Query("{'estado' : 'activo', 'titulo' : ?0}")
	Fichero findByTitulo(String titulo);
	
	@Query("{'estado' : 'activo'}")
	List<Fichero> findAllActive();
	
}