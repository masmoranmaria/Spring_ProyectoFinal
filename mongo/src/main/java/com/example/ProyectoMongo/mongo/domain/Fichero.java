package com.example.ProyectoMongo.mongo.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document(collection = "fichero")
public class Fichero {

	@Id
	private String id;
	private String fechaCreacion;
	private String titulo;
	private String descripcion;
	private String palabrasClave;
	private String estado;
	private String tamanyo;
	private String numPrev;
	private String numDesc;
	private String contenido;
	
	public Fichero(String FechaCreacion, String titulo, String descripcion, String palabrasClave, String estado, String tamanyo, String numPrev, String numDesc) {
		this.fechaCreacion = FechaCreacion;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.palabrasClave = palabrasClave;
		this.estado = estado;
		this.tamanyo = tamanyo;
		this.numPrev = numPrev;
		this.numDesc = numDesc;
	}
	
}
