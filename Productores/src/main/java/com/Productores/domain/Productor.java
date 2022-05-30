package com.Productores.domain;

public class Productor {

	private int id;

	private String nombre;

	private String apellidos;

	private String NIF;

	private String email;

	private String password;

	private Tipo tipo;

	public enum Tipo {
		F, J
	}

	private Estado estado;

	public enum Estado {
		P, I, A
	}

	//Cantidad de MB que puede subir en un año
	private Double cuota;
	
	

	public Productor() {

	}

	public Productor(int id, String nombre, String apellidos, String nIF, String email, String password, Tipo tipo,
			Estado estado, Double cuota) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		NIF = nIF;
		this.email = email;
		this.password = password;
		this.tipo = tipo;
		this.estado = estado;
		this.cuota = cuota;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getNIF() {
		return NIF;
	}

	public void setNIF(String nIF) {
		NIF = nIF;
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

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Double getCuota() {
		return cuota;
	}

	public void setCuota(Double cuota) {
		this.cuota = cuota;
	}
	
	
}
