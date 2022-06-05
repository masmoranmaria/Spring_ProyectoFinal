package es.uv.pr.spring.trabajoFinal.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uv.pr.spring.trabajoFinal.domain.Productor;
import es.uv.pr.spring.trabajoFinal.domain.Trabajo;
import es.uv.pr.spring.trabajoFinal.repositories.TrabajosRepository;
import es.uv.pr.spring.trabajoFinal.repositories.ValidadoresRepository;


@Service
public class TrabajoService {

	@Autowired
	private TrabajosRepository tr;
	@Autowired
	private ValidadoresRepository vs;
	
	public Trabajo createTrabajo (Trabajo t ) {
		//No sabemos el validador que lo va a validar 
		int size = this.tr.findAll().size();
		t.setId(size);
		t.setValidador(vs.getById(0));
		tr.save(t);
		return t; 
	}
	
	public List<Trabajo> getByProdId(Productor p){
		Optional<List<Trabajo>> t = tr.findByProductor(p);
		if (t != null) {
			return t.get();
		}else return null;
		
	}
}
