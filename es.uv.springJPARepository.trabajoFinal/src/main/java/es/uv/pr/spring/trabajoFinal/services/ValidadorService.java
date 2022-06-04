package es.uv.pr.spring.trabajoFinal.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uv.pr.spring.trabajoFinal.domain.Productor;
import es.uv.pr.spring.trabajoFinal.domain.Validador;
import es.uv.pr.spring.trabajoFinal.domain.Productor.Estado;
import es.uv.pr.spring.trabajoFinal.repositories.ProductoresRepository;
import es.uv.pr.spring.trabajoFinal.repositories.ValidadoresRepository;

@Service
public class ValidadorService {

	@Autowired
	private ProductoresRepository pr;

	@Autowired
	ValidadoresRepository vr;

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

	public Productor updateProductor(Integer id, Productor p) {

		Optional<Productor> update = this.pr.findById(id);
		if (update.isEmpty()) {
			return null;
		}

		// COMPROBAR CAMPOS NOT NULL
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

		if (p.getTipo() != null) {
			update.get().setTipo(p.getTipo());
		}
		
		if (p.getCuota() != null) {
			update.get().setCuota(p.getCuota());
		}

		//update.get().setEstado(p.getEstado());

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

	public Productor deleteProductor(Integer id) {
		Productor deleted = this.getProductor(id);
		if (deleted != null) {
			this.pr.deleteById(id);
			return deleted;
		} else {
			return null;
		}
	}

	public Validador getByEmail(String email) {

		Optional<Validador> v = this.vr.findByEmail(email);
		if (v.isEmpty()) {
			return null;
		} else
			return v.get();

	}

}
