package com.Productores.domain;

public class Fichero {


	private String id;
	private String FechaCreacion;
	private String titulo;
	private String descripcion;
	private String PalabrasClave;
	private String estado;
	private int tamanyo;
	private int numPrev;
	private int numDesc;
	private String contenido;
	
	public Fichero() {
		
	}

	public Fichero(String id, String fechaCreacion, String titulo, String descripcion, String palabrasClave,
			String estado, int tamanyo, int numPrev, int numDesc, String contenido) {
		super();
		this.id = id;
		FechaCreacion = fechaCreacion;
		this.titulo = titulo;
		this.descripcion = descripcion;
		PalabrasClave = palabrasClave;
		this.estado = estado;
		this.tamanyo = tamanyo;
		this.numPrev = numPrev;
		this.numDesc = numDesc;
		this.contenido = contenido;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public int getTamanyo() {
		return tamanyo;
	}

	public void setTamanyo(int tamanyo) {
		this.tamanyo = tamanyo;
	}

	public int getNumPrev() {
		return numPrev;
	}

	public void setNumPrev(int numPrev) {
		this.numPrev = numPrev;
	}

	public int getNumDesc() {
		return numDesc;
	}

	public void setNumDesc(int numDesc) {
		this.numDesc = numDesc;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	@Override
	public String toString() {
		return "Fichero [id=" + id + ", FechaCreacion=" + FechaCreacion + ", titulo=" + titulo + ", descripcion="
				+ descripcion + ", PalabrasClave=" + PalabrasClave + ", estado=" + estado + ", tamanyo=" + tamanyo
				+ ", numPrev=" + numPrev + ", numDesc=" + numDesc + ", contenido=" + contenido + "]";
	}
	
	
	
	
	
	
	
}
