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
		int size = this.pr.findAll().size();
		p.setId(size);
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
		
		System.out.println(update.get().toString());
		System.out.println(p.toString());
		
		//COMPROBAR CAMPOS NOT NULL
		if (p.getNombre() != null) {
			update.get().setNombre(p.getNombre());
		}
		if (p.getApellidos() != null) {

			update.get().setApellidos(p.getApellidos());
		}

		if (p.getEmail() != null) {

			update.get().setEmail(p.getEmail());
		}
		if (p.getPassword() != null) {

			update.get().setPassword(p.getPassword());
		}
		
		if (p.getNIF() != null) {

			update.get().setNIF(p.getNIF());
		}

		if(p.getTipo() != null) {
			update.get().setTipo(p.getTipo());
		}
		
		this.pr.save(update.get());
		return update.get();
	}

}
