package com.Productores.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Productores.domain.Fichero;
import com.Productores.repositories.FicherosRepository;

@Service
public class FicherosService {
	
	private FicherosRepository ficheroRepository;
	
	public Fichero saveFichero(Fichero fichero) {
		ficheroRepository.save(fichero);
		
		return fichero;
	}
}
