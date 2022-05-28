package com.Productores.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "fichero")
public class Fichero {

	@Id
	private String id;
	private String FechaCreacion;
	private String titulo;
	private String descripcion;
	private String PalabrasClave;
	private String estado;
	private String tamanyo;
	private String numPrev;
	private String numDesc;
	private String contenido;
	
	public Fichero() {}
	
	public Fichero(String FechaCreacion, String titulo, String descripcion, String PalabrasClave, String estado, String tamanyo, String numPrev, String numDesc) {
		this.FechaCreacion = FechaCreacion;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.PalabrasClave = PalabrasClave;
		this.estado = estado;
		this.tamanyo = tamanyo;
		this.numPrev = numPrev;
		this.numDesc = numDesc;
	}
	
	public String getFechaCreacion() {
		return FechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		FechaCreacion = fechaCreacion;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getPalabrasClave() {
		return PalabrasClave;
	}

	public void setPalabrasClave(String palabrasClave) {
		PalabrasClave = palabrasClave;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTamanyo() {
		return tamanyo;
	}

	public void setTamanyo(String tamanyo) {
		this.tamanyo = tamanyo;
	}

	public String getNumPrev() {
		return numPrev;
	}

	public void setNumPrev(String numPrev) {
		this.numPrev = numPrev;
	}

	public String getNumDesc() {
		return numDesc;
	}

	public void setNumDesc(String numDesc) {
		this.numDesc = numDesc;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	
	
}
