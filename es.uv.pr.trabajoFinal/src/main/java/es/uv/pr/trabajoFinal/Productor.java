package es.uv.pr.trabajoFinal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table( name = "productores")
public class Productor {
	
	@Id @Column (name= "id")
	private int id;
	
	@Column(name= "nombre", unique=false, nullable = false)
	private String nombre;
	
	@Column(name= "apellidos", unique=false, nullable = false)
	private String apellidos;
	
	@Column (name ="nif", unique=true)
	private String NIF;
	
	@Column (name = "email")
	private String email;
	
	@Column (name = "password")
	private String password;
	
	@Column(name= "tipo")
	@Enumerated(EnumType.STRING)
	private Tipo tipo;
	
	public enum Tipo {
		F,J
	}
	
	@Column(name= "estado")
	@Enumerated(EnumType.STRING)
	private Estado estado;
	
	public enum Estado {
		P, I, A
	}
	
	@Column(name= "cuota")
	private Double cuota;

	public Productor() {
		super();
	}

	public Productor(int id, String nombre, String apellidos, String nIF, String email, String password, Tipo tipo,
			Estado estado, Double cuota) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.NIF = nIF;
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

	@Override
	public String toString() {
		return "Productor [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", NIF=" + NIF + ", email="
				+ email + ", password=" + password + ", tipo=" + tipo + ", estado=" + estado + ", cuota=" + cuota + "]";
	}
	
	
	
	

	

}
