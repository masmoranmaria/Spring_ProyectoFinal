package com.Productores.endpoints;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.Productores.domain.Fichero;
import com.Productores.domain.Productor;
import com.Productores.domain.Trabajo;
import com.Productores.security.TokenProvider;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductorController {

	@Autowired
	TokenProvider tk;

//	Solicitud de registro de un nuevo productor (PF1). Se indicará NIF/CIF, nombre
//	completo o razón social, tipo (persona física o jurídica), e-mail y contraseña. El
//	usuario se creará con estado pendiente de aprobación (por un validador) y sin cuota
//	anual asignada. No se requerirá autenticación.
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Productor createProductor(@RequestBody Productor p) {

		//System.out.println("Entrando post");
		String uri = "http://localhost:8083/repo/productores";
		RestTemplate rt = new RestTemplate();
		p.setPassword(new BCryptPasswordEncoder().encode(p.getPassword()));
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

		String uri = "http://localhost:8083/repo/productores/" + id;
		RestTemplate rt = new RestTemplate();
		if (p.getPassword() != null) {
			p.setPassword(new BCryptPasswordEncoder().encode(p.getPassword()));
		}
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
	public ResponseEntity<Fichero> publicar(// @RequestParam("file") MultipartFile f,
			@RequestHeader(value = "Authorization") String cabecera, @RequestParam("tamanyo") int tamanyo,
			@RequestParam("palabrasClave") String palabrasClave, @RequestParam("titulo") String titulo,
			@RequestParam("descripcion") String descripcion)

	{

		Fichero fichero = new Fichero();
		fichero.setFechaCreacion(LocalDate.now().toString());
		fichero.setDescripcion(descripcion);
		fichero.setPalabrasClave(palabrasClave);
		fichero.setTamanyo(tamanyo);
		fichero.setTitulo(titulo);
		fichero.setNumDesc(0);
		fichero.setNumPrev(0);
		fichero.setEstado("pendiente");

		// fichero.setContenido();
		// COMO ALMACENAR CONTENIDO FICHERO

		Trabajo t = new Trabajo();
		t.setNum_desc(0);
		t.setNum_prev(0);

		Productor p = this.tk.getProdByToken(cabecera);
		if (p == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

		if (p.getCuota() - tamanyo > 0) {
			p.setCuota(p.getCuota() - tamanyo);
			Productor n = new Productor();
			n.setCuota(p.getCuota() - tamanyo);
			updateProductor(n, p.getId());

		} else {
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}

		RestTemplate rt = new RestTemplate();
		// Enviar fichero a mongo
		String uriMongo = "http://localhost:8080/api/ficheros";
		HttpEntity<Fichero> request = new HttpEntity<>(fichero);
		Fichero fich = rt.postForObject(uriMongo, request, Fichero.class);

		// Devolver el id del fichero que se creo en mongo
		t.setId_mongo(fich.getId());
		t.setProductor(p);

		// Guardar el trabajo
		String uriJPA = "http://localhost:8083/repo/trabajos/";
		HttpEntity<Trabajo> request1 = new HttpEntity<>(t);
		Trabajo result2 = rt.postForObject(uriJPA, request1, Trabajo.class);

		return new ResponseEntity<>(fich, HttpStatus.OK);

	}

//	Consultar el listado de ficheros de datos del productor (PF4). Requerirá
//	autenticación y que su estado sea activo.
	
	@GetMapping("/ficheros")
	public ResponseEntity<List<Fichero>> getFicherosByProdID( @RequestHeader(value = "Authorization") String cabecera) {
		System.out.println(cabecera);
		Productor p = this.tk.getProdByToken(cabecera);
		
		if (p == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		
		RestTemplate rt = new RestTemplate();
		
		//BUSCAR TRABAJOS CON ESE ID PRODUCTOR
		String uriJPA = "http://localhost:8080/api/ficheros/";
		HttpEntity<Productor> request = new HttpEntity<>(p);
		ResponseEntity <List<Trabajo>> result =  rt.postForObject(uriJPA, request, ResponseEntity.class );
		
		List<Trabajo> trabajos = result.getBody();
		List<Fichero> res = new ArrayList<Fichero>();
 		for(Trabajo t :trabajos) {
		
		String uriMongo = "http://localhost:8080/api/ficheros/"+ t.getId();
		ResponseEntity <Fichero> f =  rt.getForObject(uriMongo, ResponseEntity.class );
		res.add(f.getBody());
		
		}
 		
 		return new ResponseEntity<>(res, HttpStatus.OK);
	}

//	 Modificar la información de un fichero de datos del productor (PF5). Se podrán
//	actualizar el título, descripción y palabras clave. Requerirá autenticación y que su
//	estado sea activo.
	
	@PostMapping("/ficheros/{id}")
	public ResponseEntity<Fichero> updateFichero( @RequestHeader(value="Authorization") String cabecera, @PathVariable("id")
	  String id , @RequestBody Fichero f ) {
		
		ResponseEntity<List<Fichero>> result = getFicherosByProdID(cabecera);
		List<Fichero> ficheros = result.getBody();
		
		Fichero nuevo = new Fichero();
		for(Fichero fich : ficheros) {
			if(fich.getId() == id) {
				nuevo = fich;
			}
		}
		
		if(nuevo == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		RestTemplate rt = new RestTemplate();
		String uriMongo = "http://localhost:8080/api/ficheros/"+ f.getId();
		HttpEntity<Fichero> request = new HttpEntity<>(f);
		ResponseEntity <Fichero> res =  rt.postForObject(uriMongo, f,  ResponseEntity.class );
		return res;
		
	}



//	Eliminar un fichero de datos del productor (PF6). Requerirá autenticación y que su
//	estado sea activo.
//	
	@DeleteMapping("/ficheros/{id}")
	public ResponseEntity<Fichero> deleteFichero ( @RequestHeader(value="Authorization") String cabecera, @RequestParam("id")
	  String id  ) {
		
		ResponseEntity<List<Fichero>> result = getFicherosByProdID(cabecera);
		List<Fichero> ficheros = result.getBody();
		
		Fichero nuevo = new Fichero();
		for(Fichero fich : ficheros) {
			if(fich.getId() == id) {
				nuevo = fich;
			}
		}
		
		if(nuevo == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		RestTemplate rt = new RestTemplate();
		String uriMongo = "http://localhost:8080/api/ficheros/delete"+ nuevo.getId();
		
		ResponseEntity <Fichero> res =  rt.getForObject(uriMongo,  ResponseEntity.class );
		return res;
		
		
	}

}
