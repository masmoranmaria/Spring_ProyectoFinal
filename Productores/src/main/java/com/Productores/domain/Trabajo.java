package com.Productores.domain;

public class Trabajo {
	
	private int id;
	
	
	private String id_mongo;
	
	private Productor productor;
	
	
	private Validador validador;
	
	
	private int num_prev;
	
	
	private int num_desc;

	public Trabajo() {}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getId_mongo() {
		return id_mongo;
	}

	public void setId_mongo(String id_mongo) {
		this.id_mongo = id_mongo;
	}

	public Productor getProductor() {
		return productor;
	}

	public void setProductor(Productor productor) {
		this.productor = productor;
	}

	public Validador getValidador() {
		return validador;
	}

	public void setValidador(Validador validador) {
		this.validador = validador;
	}

	public int getNum_prev() {
		return num_prev;
	}

	public void setNum_prev(int num_prev) {
		this.num_prev = num_prev;
	}

	public int getNum_desc() {
		return num_desc;
	}

	public void setNum_desc(int num_desc) {
		this.num_desc = num_desc;
	}

	public Trabajo(int id, String id_mongo, Productor productor, Validador validador, int num_prev, int num_desc) {
		super();
		this.id = id;
		this.id_mongo = id_mongo;
		this.productor = productor;
		this.validador = validador;
		this.num_prev = num_prev;
		this.num_desc = num_desc;
	}
}
