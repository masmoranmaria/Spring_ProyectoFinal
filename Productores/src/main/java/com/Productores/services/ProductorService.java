//package com.Productores.services;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//
//import es.uv.pr.spring.trabajoFinal.repositories.ProductoresRepository;
//import es.uv.pr.trabajoFinal.Productor;
//
//@Service
//public class ProductorService {
//	
//	@Autowired
//	private ProductoresRepository pr; 
//	
//	/*
//	 * Solicitud de registro de un nuevo productor (PF1). Se indicará NIF/CIF,
//	 * nombre completo o razón social, tipo (persona física o jurídica), e-mail y
//	 * contraseña. El usuario se creará con estado pendiente de aprobación (por un
//	 * validador) y sin cuota anual asignada. No se requerirá autenticación.
//	 */
//
//	public Productor registrarProductor(Productor p) {
//		
//		return this.pr.save(p);
//		
//	}
//	
////	● Obtener el listado de productores (VF1). Si no se indica ningún filtro se devolverá
////	todo el listado de productores. Opcionalmente se pueden indicar los siguientes
////	filtros: solo pendientes de aprobación, solo los que haya consumido su cuota anual o
////	solo los que tengan algún fichero erróneo. Requerirá autenticación.
//	
//	List<Productor> getProductores() {
//		return this.pr.findAll();
//	}
//	
//	
////	List<Productor> getProductoresPendientes(){
////		
////		
////	}
////	
////	List<Productor> getProductoresSinCuota(){
////		
////		
////	}
////	
////	List<Productor> getProductoresFicheroError(){
////		
////		
////	}
////	
//	
//
//	/*
//	 * Modificación de la información del productor (PF2). Se podrán actualizar los
//	 * campos especificados en la solicitud de registro. Requerirá autenticación y
//	 * que su estado sea activo.
//	 */
//
//	Productor updateProductor(Productor p) {
//		
//		Optional<Productor> update = this.pr.findById(p.getId());
//		if(update.isEmpty()) {
//			return null;
//		}
//		
//		update.get().setNombre(p.getNombre());
//		update.get().setApellidos(p.getApellidos());
//		update.get().setCuota(p.getCuota());
//		update.get().setEmail(p.getEmail());
//		update.get().setCuota(p.getCuota());
//		update.get().setEstado(p.getEstado());
//		update.get().setTipo(p.getTipo());
//		
//		return update.get();
//	}
//	
//}
//
