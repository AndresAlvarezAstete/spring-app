package cl.travel.proyecto.codigos.perfiles;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="usuarios")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 7451406137706175142L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="rut", nullable = false, length = 50)
    private String rut;
	
	@Column(name="nombre", nullable = false, length = 50)
    private String nombre;

	@Column(name="apellido", nullable = false, length = 50)
	private String apellido;
	
	@Column(name="email", nullable = false, length = 50, unique = true)
    private String correo;
	
	@ManyToMany
	private Set<Perfil> perfiles = null;
	
	@ManyToMany
	private Set<Rol> roles = null;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date actualLogin;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lastLogin;
	
	public Usuario(Long id, String rut, String nombre, String apellido, String correo, Set<Perfil> perfiles,
			Set<Rol> roles, Date actualLogin, Date lastLogin) {
		super();
		this.id = id;
		this.rut = rut;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.perfiles = perfiles;
		this.roles = roles;
		this.actualLogin = actualLogin;
		this.lastLogin = lastLogin;
	}
	
	public Usuario(String rut, String nombre, String apellido, String correo, Set<Perfil> perfiles,
			Set<Rol> roles, Date actualLogin, Date lastLogin) {
		super();
		this.rut = rut;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.perfiles = perfiles;
		this.roles = roles;
		this.actualLogin = actualLogin;
		this.lastLogin = lastLogin;
	}


	public Usuario() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Set<Perfil> getPerfiles() {
		return perfiles;
	}

	public void setPerfiles(Set<Perfil> perfiles) {
		this.perfiles = perfiles;
	}

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}

	public Date getActualLogin() {
		return actualLogin;
	}

	public void setActualLogin(Date actualLogin) {
		this.actualLogin = actualLogin;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", rut=" + rut + ", nombre=" + nombre + ", apellido=" + apellido + ", correo="
				+ correo + ", perfiles=" + perfiles + ", roles=" + roles + ", actualLogin=" + actualLogin
				+ ", lastLogin=" + lastLogin + "]";
	}
}
