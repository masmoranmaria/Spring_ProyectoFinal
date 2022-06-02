package main.java.com.example.ProyectoMongo.mongo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity 
@Table( name = "trabajos")
public class Trabajo {
	

	@Id @Column (name= "id")
	private int id;
	
	@Column (name= "id_mongo")
	private String id_mongo;
	
	@ManyToOne
	@JoinColumn(name= "id_productor")
	private Productor productor;
	
	@ManyToOne
	@JoinColumn(name= "id_validador")
	private Validador validador;
	
	@Column(name= "num_previsualizaciones")
	private int num_prev;
	
	@Column (name = "num_descargas")
	private int num_desc;

	public Trabajo() {
		super();
		// TODO Auto-generated constructor stub
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

	@Override
	public String toString() {
		return "Trabajo [id=" + id + ", id_mongo=" + id_mongo + ", productor=" + productor + ", validador=" + validador
				+ ", num_prev=" + num_prev + ", num_desc=" + num_desc + "]";
	}
}
