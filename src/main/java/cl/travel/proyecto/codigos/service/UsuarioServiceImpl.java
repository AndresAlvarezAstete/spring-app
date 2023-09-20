package cl.travel.proyecto.codigos.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.travel.proyecto.codigos.perfiles.Rol;
import cl.travel.proyecto.codigos.perfiles.Usuario;
import cl.travel.proyecto.codigos.repository.RolRepository;
import cl.travel.proyecto.codigos.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements IUsuarioService {
	
	private static final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);
	
	@Autowired
	private UsuarioRepository repositorio; 
	
	@Autowired
	private RolRepository repositorioR;

	@Override
	public List<Usuario> listarUsuarios() {
		
		log.info("Inicio metodo Listar Usuarios");
		
		return repositorio.findAll();
	}

	@Override
	public Usuario guardarUsuario(Usuario usuario) {
		return repositorio.save(usuario);
	}

	@Override
	public Usuario obtenerUsuarioPorId(Long id) {
		return repositorio.findById(id).get();
	}

	@Override
	public Usuario editarUsuario(Usuario usuario) {
		return repositorio.save(usuario);
	}

	@Override
	public void eliminarUsuario(Long id) {
		repositorio.deleteById(id);
	}

	@Override
	public List<Rol> listarRoles() {
		return repositorioR.findAll();
	}

	@Override
	public Rol guardarRol(Rol rol) {
		return repositorioR.save(rol);
	}

	@Override
	public Rol obtenerRolPorId(Long id) {
		return repositorioR.findById(id).get();
	}

	@Override
	public Rol editarRol(Rol rol) {
		return repositorioR.save(rol);
	}

	@Override
	public void eliminarRol(Long id) {
		repositorioR.deleteById(id);
	}
}
