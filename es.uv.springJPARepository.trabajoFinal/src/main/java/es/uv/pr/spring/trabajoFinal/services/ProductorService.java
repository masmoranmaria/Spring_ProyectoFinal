package es.uv.pr.spring.trabajoFinal.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.uv.pr.spring.trabajoFinal.domain.Productor;
import es.uv.pr.spring.trabajoFinal.repositories.ProductoresRepository;


@Service
public class ProductorService {

	@Autowired
	private ProductoresRepository pr;

	public Productor registrarProductor(Productor p) {
		return this.pr.save(p);

	}

	public Productor getByEmail(String email) {
		Optional<Productor> p = this.pr.findByEmail(email);
		if (p.isEmpty()) {
			return null;
		} else {
			return p.get();
		}

	}

	public Productor getProductor(Integer id) {

		Optional<Productor> p = this.pr.findById(id);
		if (p.isEmpty()) {
			// lanzar excepciones
			return null;
		} else
			return p.get();
	}

	// List<Productor> getProductoresPendientes(){
	//
	//
	// }
	//
	// List<Productor> getProductoresSinCuota(){
	//
	//
	// }
	//
	// List<Productor> getProductoresFicheroError(){
	//
	//
	// }
	//

	public Productor modifyProductor(Integer id, Productor p) {

		Optional<Productor> update = this.pr.findById(id);
		if (update.isEmpty()) {
			return null;
		}

		update.get().setNombre(p.getNombre());
		update.get().setApellidos(p.getApellidos());
		update.get().setEmail(p.getEmail());
		update.get().setPassword(p.getPassword());
		update.get().setNIF(p.getNIF());
		update.get().setTipo(p.getTipo());

		//this.pr.save(update.get());
		return update.get();
	}

}
