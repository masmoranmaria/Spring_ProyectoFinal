package com.Productores.endpoints;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.Productores.domain.Productor;

@RestController
@RequestMapping(value = "/api/productores", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductorController {

//	Solicitud de registro de un nuevo productor (PF1). Se indicará NIF/CIF, nombre
//	completo o razón social, tipo (persona física o jurídica), e-mail y contraseña. El
//	usuario se creará con estado pendiente de aprobación (por un validador) y sin cuota
//	anual asignada. No se requerirá autenticación.
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Productor createProductor(@RequestBody Productor p) {
		
		String uri = "http://localhost:8080/repo/productores";
		RestTemplate rt = new RestTemplate();
		HttpEntity<Productor> request = new HttpEntity<>(p);
		Productor result = rt.postForObject(uri, request,  Productor.class);
		return result;
	}
	
	
//	Modificación de la información del productor (PF2). Se podrán actualizar los campos
//	especificados en la solicitud de registro. Requerirá autenticación y que su estado sea
//	activo.	

	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Productor updateProductor(@RequestBody Productor p, @PathVariable("id") Integer id) {
		
		String uri = "http://localhost:8080/repo/productores/"+id;
		RestTemplate rt = new RestTemplate();
		HttpEntity<Productor> request = new HttpEntity<>(p);
		Productor result = rt.postForObject(uri, request,  Productor.class);
		return result;
	}


}
