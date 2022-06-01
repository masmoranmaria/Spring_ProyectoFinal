package com.example.Consumidores.consumidores.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fichero {

	private String id;
	private String fechaCreacion;
	private String titulo;
	private String descripcion;
	private String palabrasClave;
	private String estado;
	private int tamanyo;
	private int numPrev;
	private int numDesc;
	private String contenido;
	
	public Fichero(String FechaCreacion, String titulo, String descripcion, String palabrasClave, String estado, int tamanyo, int numPrev, int numDesc) {
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
