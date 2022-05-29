package es.uv.pr.trabajoFinal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ProductorService {

	EntityManagerFactory emf;
	EntityManager em;

	public ProductorService() {
		this.emf = Persistence.createEntityManagerFactory("trabajoFinal");
		this.em = emf.createEntityManager();
	}

	//INSERTAR UN PRODUCTOR
	public void  createProductor(Productor p) {
		// Iniciamos una transacci�n
		em.getTransaction().begin();
		em.persist(p);
		// Commiteamos la transacci�n
		em.getTransaction().commit();
	}
	
	//ELIMINAR UN PRODUCTOR
		public void  deleteProductor(Productor p) {
			// Iniciamos una transacci�n
			em.getTransaction().begin();
			em.remove(p);
			// Commiteamos la transacci�n
			em.getTransaction().commit();
		}
		
	//CAMBIAR CONSTRASE�A
		
		public void updatePassword(Productor p, String password) {
			em.getTransaction().begin();
			Productor newProductor = em.find(Productor.class, p.getId());
			newProductor.setPassword(password);
			em.getTransaction().commit();
			
		}
		
	//CAMBIAR LA CUOTA
		public void updateCuota(Productor p, Double cuota) {
			em.getTransaction().begin();
			Productor newProductor = em.find(Productor.class, p.getId());
			newProductor.setCuota(cuota);
			em.getTransaction().commit();
			
		}

}
