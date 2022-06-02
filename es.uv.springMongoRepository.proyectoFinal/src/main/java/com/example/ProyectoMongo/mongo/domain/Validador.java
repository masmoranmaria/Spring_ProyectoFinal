package com.example.ProyectoMongo.mongo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Validador {
	
	private int id;
	
	private String nombre;
	
	private String apellidos;
	
	private String email;
	
	private String password;
	
}