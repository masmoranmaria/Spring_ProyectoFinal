package com.example.ProyectoMongo.mongo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.ProyectoMongo.mongo.domain.Fichero;

@Repository
public interface FicherosRepository extends MongoRepository<Fichero, String> {
	
	@Query("{'estado' : 'activo', 'titulo' : ?0}")
	Optional<Fichero> findByTitulo(String titulo);
	
	@Query("{'estado' : 'activo'}")
	List<Fichero> findAllActive();
	
	@Query("{'estado': 'publicado', 'palabrasClave' : ?0}")
	List<Fichero> findByPalabrasClave(String palabrasClave, Sort sort);
	
	@Query("{'id' : ?0, 'estado': 'publicado'}")
	Optional<Fichero> findPublishedById(String id);
	
	
}