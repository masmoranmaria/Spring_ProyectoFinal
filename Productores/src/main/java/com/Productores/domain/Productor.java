package com.Productores.domain;

public class Productor {
	
	private String id;
	private String dni;
	private String name;
	private String type;
	private String email;
	private String password;
	
	public Productor() {}
	
	public Productor(String id, String dni, String name, String type, String email, String password) {
		this.id = id;
		this.dni = dni;
		this.name = name;
		this.type = type;
		this.email = email;
		this.password = password;
	}
	
	public Productor(String dni, String name, String type, String email, String password) {
		this.dni = dni;
		this.name = name;
		this.type = type;
		this.email = email;
		this.password = password;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
