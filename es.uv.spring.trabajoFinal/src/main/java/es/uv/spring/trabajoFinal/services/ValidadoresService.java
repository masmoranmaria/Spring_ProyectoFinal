package es.uv.spring.trabajoFinal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uv.pr.spring.trabajoFinal.repositories.ProductoresRepository;
import es.uv.pr.spring.trabajoFinal.repositories.ValidadoresRepository;

@Service
public class ValidadoresService {
	
	@Autowired
	private ValidadoresRepository vr;
	
	
	

}
