package com.Productores.endpoints;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import es.uv.pr.trabajoFinal.Productor;


@RestController
@RequestMapping(value = "/api/productores", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductorController {
	
	//WRAPPER PARA ENVIAR Y RECIBIR LISTAS 
	public class ProductorList {
		private List<Productor> productores;

		public ProductorList() {
			productores = new ArrayList<>();
		}

		// standard constructor and getter/setter
	}

//	@Autowired
//	private ProductorService ps;

	@GetMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProductorList getProductores() {
		//hacer la petición
		System.out.println("Hola aquí llego");
		String uri = "http://localhost:8080/repo/productores";
		RestTemplate rt = new RestTemplate();
		ProductorList result = rt.getForObject(uri, ProductorList.class);
		return result;
		
	}

//	@PostMapping
//	@ResponseStatus(HttpStatus.CREATED)
//	public Productor createProductor(@RequestBody Productor p) {
//		return this.ps.registrarProductor(p);
//	}

}
