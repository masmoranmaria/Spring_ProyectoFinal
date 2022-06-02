package es.uv.pr.spring.trabajoFinal.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uv.pr.spring.trabajoFinal.repositories.ProductoresRepository;
import es.uv.pr.spring.trabajoFinal.repositories.ValidadoresRepository;
import es.uv.pr.trabajoFinal.Productor;
import es.uv.pr.trabajoFinal.Productor.Estado;

@Service
public class ValidadorService {
	
	@Autowired
	private ProductoresRepository pr;
	
	
	public List<Productor> getProductores() {
		return this.pr.findAll();
	}
	
	public Productor getProductor(Integer id) {

		Optional<Productor> p = this.pr.findById(id);
		if (p.isEmpty()) {
			// lanzar excepciones
			return null;
		} else
			return p.get();
	}
	
	public Productor updateProductor(Integer id , Productor p) {

		Optional<Productor> update = this.pr.findById(id);
		if (update.isEmpty()) {
			return null;
		}

		update.get().setNombre(p.getNombre());
		update.get().setApellidos(p.getApellidos());
		update.get().setEmail(p.getEmail());
		update.get().setPassword(p.getPassword());
		update.get().setNIF(p.getNIF());
		update.get().setCuota(p.getCuota());
		update.get().setEstado(p.getEstado());
		update.get().setTipo(p.getTipo());

		this.pr.save(update.get());
		return update.get();
	}
	
	public Productor validarProductor(Integer id, Double cuota) {
		Productor p = getProductor(id);
		p.setEstado(Estado.A);
		p.setCuota(cuota);
		this.pr.save(p);
		return p;
	}
	
	public Productor deleteProductor (Integer id) {
		Productor deleted = this.getProductor(id);
		if(deleted != null) {
			this.pr.deleteById(id);
			return deleted;
		} else {
			return null;
		}
	}
	

}
