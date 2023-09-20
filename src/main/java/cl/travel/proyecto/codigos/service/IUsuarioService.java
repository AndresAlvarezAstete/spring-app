package cl.travel.proyecto.codigos.service;

import java.util.List;

import cl.travel.proyecto.codigos.perfiles.Rol;
import cl.travel.proyecto.codigos.perfiles.Usuario;

public interface IUsuarioService {
	
	public List<Usuario> listarUsuarios();
	
	public Usuario guardarUsuario(Usuario usuario);

	public Usuario obtenerUsuarioPorId(Long id);
	
	public Usuario editarUsuario(Usuario usuario);
	
	public void eliminarUsuario(Long id);
	
	public List<Rol> listarRoles();
	
	public Rol guardarRol(Rol rol);
	
	public Rol obtenerRolPorId (Long id);
	
	public Rol editarRol(Rol rol);
	
}
