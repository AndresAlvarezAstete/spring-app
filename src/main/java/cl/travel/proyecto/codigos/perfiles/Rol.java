package cl.travel.proyecto.codigos.perfiles;

import java.io.Serializable;

public class Rol implements Serializable {

	private static final long serialVersionUID = 3340580474244784406L;
	
	private long id;
	
	private String nombre;
	
	private String descripcion;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
