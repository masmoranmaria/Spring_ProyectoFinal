package com.Productores.endpoints;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Productores.domain.Fichero;
import com.Productores.services.FicherosService;
import com.google.gson.Gson;

@RestController
@RequestMapping(value="/api/ficheros", produces=MediaType.APPLICATION_JSON_VALUE)
public class FicherosController {
	
	@Autowired
	private FicherosService ficherosService;
	

	
}
