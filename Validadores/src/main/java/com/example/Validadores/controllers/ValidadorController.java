package com.example.Validadores.controllers;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import com.example.Validadores.domain.Productor;

@RestController
@RequestMapping(value = "/api/validadores", produces = MediaType.APPLICATION_JSON_VALUE)
public class ValidadorController {

//  Obtener el listado de productores (VF1). Si no se indica ningún filtro se devolverá todo el listado de productores.
//	Opcionalmente se pueden indicar los siguientes filtros: solo pendientes de aprobación,
//	solo los que haya consumido su cuota anual o solo los que tengan algún fichero erróneo.
//	Requerirá autenticación.

	@GetMapping("/productores")
	@ResponseStatus(HttpStatus.CREATED)
	public Object[] getProductores() {
		// hacer la petición
		String uri = "http://localhost:8083/repo/validadores";
		RestTemplate rt = new RestTemplate();
		Object[] result = rt.getForObject(uri, Object[].class);
		return result;

	}

//	Aprobar un nuevo productor (VF2). Se indicará el identificador del productor y la cuota anual. 
//	Cambiará el estado a activo. 
//	Requerirá autenticación.

	@PostMapping("/validar/{id}")
	// @ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<String> approbarProductor(@PathVariable("id") Integer id, @RequestBody Double c) {
		// hacer la petición
		try {
			String uri = "http://localhost:8083/repo/validadores/validar/" + id;
			RestTemplate rt = new RestTemplate();
			HttpEntity<Double> request = new HttpEntity<>(c);
			Object result = rt.postForObject(uri, request, Object.class);
			System.out.println(result);
			if (result != null) {
				return new ResponseEntity<>("Validado_con_éxito", HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>("Productor_no disponible", HttpStatus.NOT_FOUND);
			}

		} catch (Error e) {
			return new ResponseEntity<>("Ha_habido_un_problema", HttpStatus.BAD_REQUEST);
		}

	}
	
	//Modificación de la información de un productor (VF3). 
	//Se podrá actualizar cualquier campo del productor a través de su identificador.
	//Requerirá autenticación.
	
	@PutMapping("/productor/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Productor updateProductor(@PathVariable("id") Integer id, @RequestBody Productor p) {
		
		String uri = "http://localhost:8083/repo/validadores/"+ id;
		RestTemplate rt = new RestTemplate();
		if(p.getPassword() != "") {
			p.setPassword(new BCryptPasswordEncoder().encode(p.getPassword()));
		}
		HttpEntity<Productor> request = new HttpEntity<>(p);
		Productor result = rt.postForObject(uri, request,  Productor.class);
		return result;
	}
	
//	Eliminar un productor (VF4). Se indicará el identificador del productor. Requerirá
//	autenticación.
	@DeleteMapping("/productor/delete/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Productor deleteProductor(@PathVariable("id") Integer id) {
		
		String uri = "http://localhost:8083/repo/validadores/delete/"+id;
		RestTemplate rt = new RestTemplate();
		Productor result = rt.getForObject(uri,  Productor.class);
		return result;
	}
	

}