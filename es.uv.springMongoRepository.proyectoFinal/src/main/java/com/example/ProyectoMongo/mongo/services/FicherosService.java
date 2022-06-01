package com.example.ProyectoMongo.mongo.services;

import com.example.ProyectoMongo.mongo.domain.Fichero;
import com.example.ProyectoMongo.mongo.repositories.FicherosRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FicherosService {
	
	@Autowired
	private FicherosRepository ficheroRepository;
	
	public ResponseEntity<Fichero> saveFichero(Fichero fichero) {
		return new ResponseEntity<>(ficheroRepository.save(fichero), HttpStatus.OK);
	}
	
	public ResponseEntity<List<Fichero>> getAllActive() {
		return new ResponseEntity<>(ficheroRepository.findAllActive(), HttpStatus.OK);
	}
	
	public ResponseEntity<Fichero> getByTitulo(String titulo) {
		return new ResponseEntity<>(ficheroRepository.findByTitulo(titulo), HttpStatus.OK);
	}
	
	public ResponseEntity<Fichero> updateFichero(String titulo, Fichero fichero){
		Fichero ficheroActual = ficheroRepository.findByTitulo(titulo);
		
		if(!ficheroActual.getEstado().equals("activo"))
			return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
		
		ficheroActual.setTitulo(fichero.getTitulo());
		ficheroActual.setDescripcion(fichero.getDescripcion());
		ficheroActual.setPalabrasClave(fichero.getPalabrasClave());
		
		return new ResponseEntity<>(ficheroRepository.save(ficheroActual), HttpStatus.OK);
	}
	
	public ResponseEntity<Fichero> getFicheroById(String id) {
		Fichero fichero = ficheroRepository.findById(id).get();
		
		if(fichero == null)
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		
		fichero.setNumDesc(fichero.getNumDesc() + 1);
		
		return new ResponseEntity<>(ficheroRepository.save(fichero), HttpStatus.OK);
	};
	

	public ResponseEntity<Fichero> getPreviewById(String id) {
		Fichero fichero = ficheroRepository.findById(id).get();
		
		if(fichero == null)
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		
		fichero.setContenido("");
		
		return new ResponseEntity<>(fichero, HttpStatus.OK);
	};
	
	public ResponseEntity<List<Fichero>> getByPalabrasClave(String palabrasClave, String orderBy) {
		Sort sortedBy = Sort.by("fechaCreacion").descending();
		
		if(orderBy.equals("tamanyo"))
			sortedBy = Sort.by("tamanyo").descending();
		
		if(orderBy.equals("numdesc"))
			sortedBy = Sort.by("numDesc").descending();
		
		
		return new ResponseEntity<>(ficheroRepository.findByPalabrasClave(palabrasClave, sortedBy), HttpStatus.OK);
	}
	
}















