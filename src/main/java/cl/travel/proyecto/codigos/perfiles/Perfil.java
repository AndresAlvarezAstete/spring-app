package cl.travel.proyecto.codigos.perfiles;

import java.io.Serializable;

public class Perfil implements Serializable {

	private static final long serialVersionUID = -210478836059490974L;
	
	private Long id;
	
	private String nombre;
	
	private String descripcion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
