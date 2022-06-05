package es.uv.pr.spring.trabajoFinal.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.uv.pr.spring.trabajoFinal.domain.Productor;
import es.uv.pr.spring.trabajoFinal.domain.Trabajo;
import es.uv.pr.spring.trabajoFinal.domain.Validador;



public interface TrabajosRepository extends JpaRepository<Trabajo, Integer> {
	
	Optional<List<Trabajo>> findByProductor( Productor p);

}
