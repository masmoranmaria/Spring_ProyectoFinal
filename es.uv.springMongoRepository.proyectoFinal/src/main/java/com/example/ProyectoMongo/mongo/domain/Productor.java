package com.example.ProyectoMongo.mongo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Productor {

	private int id;

	private String nombre;

	private String apellidos;

	private String NIF;

	private String email;

	private String password;

	private String  tipo;

	private String  estado;

	//Cantidad de MB que puede subir en un año
	private Double cuota;
	
}
