package es.uv.pr.trabajoFinal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TestEntidades {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// Configuramos el EMF a través de la unidad de persistencia
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("trabajoFinal");
		// Generamos un EntityManager
		EntityManager em = emf.createEntityManager();
		// Iniciamos una transacción
		em.getTransaction().begin();

		// Construimos un objeto de Productor
//		Productor newProductor = new Productor(1, "María", "Mas Moran", "222222222F", "masmoma10@gamil.com", "12345",
//				Productor.Tipo.F, Productor.Estado.P, 8000.12);
//		Validador newValidador = new Validador(1, "David", "Núñez", "davidnumar@gamil.com", "12345");
//		Trabajo newTrabajo = new Trabajo(1, "0000finw", newProductor, newValidador, 0, 0);

		// Persistimos los objetos
//		 em.persist(newProductor);
//		 em.persist(newValidador);
//		 em.persist(newTrabajo);

		// Recuperamos las entidades
		// Actualizamos las entidades

		Validador v = em.find(Validador.class, 0);
		//v.setPassword("8888888");
		Productor p = em.find(Productor.class, 0);
		//p.setApellidos("Lliso");
		//Trabajo t = em.find(Trabajo.class, 0);
		//t.setNum_prev(100);

		// Eliminamos el objeto

		em.remove(p);
		em.remove(v);
		//Borrado en cascada
		//em.remove(t);

		// Commiteamos la transacción
		em.getTransaction().commit();
		// Cerramos el EntityManager
		em.close();
		return;
	}

}
