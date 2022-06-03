package es.uv.pr.spring.trabajoFinal.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.uv.pr.spring.trabajoFinal.domain.Productor;




public interface ProductoresRepository extends JpaRepository<Productor, Integer> {
	// DECLARAR (SOLO PONER NOMBRE) METODOS Y CONSULTAS MAS COMPLEJAS
	
	Optional<Productor> findByEmail(String email);
	Optional<Productor> findByEmailAndPassword(String email, String password);


}
