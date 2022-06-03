package es.uv.pr.spring.trabajoFinal.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.uv.pr.spring.trabajoFinal.domain.Validador;



public interface ValidadoresRepository  extends JpaRepository<Validador, Integer>{
	
	Optional<Validador> findByEmail(String email);
	Optional<Validador> findByEmailAndPassword(String email, String password);

}
