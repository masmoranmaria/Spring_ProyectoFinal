package com.example.ProyectoMongo.mongo.services;

import com.example.ProyectoMongo.mongo.domain.Fichero;
import com.example.ProyectoMongo.mongo.domain.Trabajo;
import com.example.ProyectoMongo.mongo.repositories.FicherosRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FicherosService {

	@Autowired
	private FicherosRepository ficheroRepository;

	public ResponseEntity<List<Fichero>> getAll() {
		return new ResponseEntity<>(ficheroRepository.findAll(), HttpStatus.OK);
	}

	public ResponseEntity<List<Fichero>> getFicherosPendientes() {
		return new ResponseEntity<>(ficheroRepository.findByEstado(), HttpStatus.OK);
	}

	public ResponseEntity<Fichero> saveFichero(Fichero fichero) {
		return new ResponseEntity<>(ficheroRepository.save(fichero), HttpStatus.OK);
	}
	
	

	public ResponseEntity<Fichero> deleteFichero(String id) {
		Optional<Fichero> f = this.ficheroRepository.findById(id);

		if (f.get() != null) {
			Fichero fichero = f.get();
			this.ficheroRepository.deleteById(id);
			return new ResponseEntity<>(fichero, HttpStatus.OK);
		} else
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

	}

	public ResponseEntity<Fichero> updateFichero(Fichero fichero, String id) {

		Optional<Fichero> f = this.ficheroRepository.findById(id);
		if (f == null)
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

		if (fichero.getTitulo() != null) {
			f.get().setTitulo(fichero.getTitulo());
		}
		if (fichero.getDescripcion() != null) {
			f.get().setDescripcion(fichero.getDescripcion());
		}
		if (fichero.getPalabrasClave() != null) {
			f.get().setPalabrasClave(fichero.getPalabrasClave());
		}

		this.ficheroRepository.save(f.get());

		return new ResponseEntity<>(f.get(), HttpStatus.OK);

	}

	public ResponseEntity<List<Fichero>> getAllActive() {
		return new ResponseEntity<>(ficheroRepository.findAllActive(), HttpStatus.OK);
	}

	public ResponseEntity<Fichero> getByTitulo(String titulo) {
		Optional<Fichero> fichero = ficheroRepository.findByTitulo(titulo);

		if (fichero.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		System.out.println("Busco");
		return new ResponseEntity<>(fichero.get(), HttpStatus.OK);
	}

	public ResponseEntity<Fichero> updateFichero(String titulo, Fichero fichero) {
		Optional<Fichero> optional = ficheroRepository.findByTitulo(titulo);

		if (optional.isEmpty())
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

		Fichero ficheroActual = optional.get();

		if (!ficheroActual.getEstado().equals("activo"))
			return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);

		ficheroActual.setTitulo(fichero.getTitulo());
		ficheroActual.setDescripcion(fichero.getDescripcion());
		ficheroActual.setPalabrasClave(fichero.getPalabrasClave());

		return new ResponseEntity<>(ficheroRepository.save(ficheroActual), HttpStatus.OK);
	}

	public ResponseEntity<Fichero> getFicheroById(String id) {
		Fichero fichero = ficheroRepository.findById(id).get();

		if (fichero == null)
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

		fichero.setNumDesc(fichero.getNumDesc() + 1);

		return new ResponseEntity<>(ficheroRepository.save(fichero), HttpStatus.OK);
	};

	public ResponseEntity<Fichero> getPreviewById(String id) {
		Fichero fichero = ficheroRepository.findById(id).get();

		if (fichero == null)
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

		fichero.setContenido("");

		return new ResponseEntity<>(fichero, HttpStatus.OK);
	};

	public ResponseEntity<List<Fichero>> getByPalabrasClave(String palabrasClave, String orderBy) {
		Sort sortedBy = Sort.by("fechaCreacion").descending();

		if (orderBy.equals("tamanyo"))
			sortedBy = Sort.by("tamanyo").descending();

		if (orderBy.equals("numdesc"))
			sortedBy = Sort.by("numDesc").descending();

		return new ResponseEntity<>(ficheroRepository.findByPalabrasClave(palabrasClave, sortedBy), HttpStatus.OK);
	}

	public ResponseEntity<List<Fichero>> getByTrabajos(Trabajo[] trabajos) {
		ArrayList<Fichero> ficheros = new ArrayList<>();

		for (Trabajo trabajo : trabajos) {
			Optional<Fichero> fichero = ficheroRepository.findPublishedById(trabajo.getId_mongo());
			if (!fichero.isEmpty())
				ficheros.add(fichero.get());
		}

		return new ResponseEntity<>(ficheros, HttpStatus.OK);
	}
}
