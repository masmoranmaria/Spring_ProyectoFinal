package com.example.ProyectoMongo.mongo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trabajo {
	
	private int id;
	
	private String id_mongo;
	
	private Productor productor;
	
	private Validador validador;
	
	private int num_prev;
	
	private int num_desc;

}
