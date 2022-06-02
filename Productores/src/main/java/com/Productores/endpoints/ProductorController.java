package com.Productores.endpoints;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.Productores.domain.Fichero;
import com.Productores.domain.Productor;
import com.Productores.domain.Trabajo;

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
		Productor result = rt.postForObject(uri, request, Productor.class);
		return result;
	}

//	Modificación de la información del productor (PF2). Se podrán actualizar los campos
//	especificados en la solicitud de registro. Requerirá autenticación y que su estado sea
//	activo.	

	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Productor updateProductor(@RequestBody Productor p, @PathVariable("id") Integer id) {

		String uri = "http://localhost:8080/repo/productores/" + id;
		RestTemplate rt = new RestTemplate();
		HttpEntity<Productor> request = new HttpEntity<>(p);
		Productor result = rt.postForObject(uri, request, Productor.class);
		return result;
	}

//	Subir un fichero de datos (PF3). Junto con el fichero de texto, se indicará el título,
//	descripción y palabras clave. Para emular la carga de ficheros grandes, se indicará
//	de manera manual un tamaño de fichero (en MB) que no tiene porqué coincidir con
//	el tamaño real del fichero. Se comprobará el formato y que no se exceda la cuota
//	anual. El fichero se creará en estado pendiente de revisión (por un validador).
//	Requerirá autenticación y que su estado sea activo.

	// SE NECESITA AUTENTICAR CON JWT PARA PODER IDENTIFCAR AL QUE LO SUBE

	@PostMapping("/publicar")
	public ResponseEntity<Fichero> publicar(@RequestBody Fichero f) {
		
		//sacar mediante token el id del productor que lo sube 
		Integer id = 0;
		//enviar el trabaj
		
		Trabajo t = new Trabajo();
		t.setNum_desc(0);
		t.setNum_prev(0);
		
		String uriMongo = "http://localhost:8080/api/ficheros/";
		String uriJPA = "http://localhost:8080/repo/trabajos/";
		
		RestTemplate rt = new RestTemplate();
		HttpEntity<Fichero> request = new HttpEntity<>(f);
		Fichero fich = rt.postForObject(uriMongo, request, Fichero.class);
		
		//devolver el id del fichero que se creo en mongo
		t.setId_mongo(fich.getId());
		HttpEntity<Trabajo> request1 = new HttpEntity<>(t);
		
		Trabajo result2 = rt.postForObject(uriJPA, request1, Trabajo.class);
		
		return new ResponseEntity<> (fich, HttpStatus.OK);

	}

}
