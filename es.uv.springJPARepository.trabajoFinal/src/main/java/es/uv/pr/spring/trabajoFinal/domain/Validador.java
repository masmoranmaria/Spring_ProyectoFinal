package es.uv.pr.spring.trabajoFinal.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table( name = "validadores")
public class Validador {
	
	@Id @Column (name= "id")
	private int id;
	
	@Column(name= "nombre", unique=false, nullable = false)
	private String nombre;
	
	@Column(name= "apellidos", unique=false, nullable = false)
	private String apellidos;
	
	@Column (name = "email")
	private String email;
	
	@Column (name = "password")
	private String password;

	public Validador() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Validador(int id, String nombre, String apellidos, String email, String password) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.password = password;
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

	@Override
	public String toString() {
		return "Validador [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", email=" + email
				+ ", password=" + password + "]";
	}
	
	
	
	
}