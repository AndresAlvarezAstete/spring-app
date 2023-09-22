package cl.travel.proyecto.codigos.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.travel.proyecto.codigos.perfiles.Perfil;
import cl.travel.proyecto.codigos.perfiles.Rol;
import cl.travel.proyecto.codigos.perfiles.Usuario;
import cl.travel.proyecto.codigos.repository.PerfilRepository;
import cl.travel.proyecto.codigos.repository.RolRepository;
import cl.travel.proyecto.codigos.repository.UsuarioRepository;
import cl.travel.proyecto.codigos.utils.ApiUsuario;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements IUsuarioService {
	
	private static final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);
	
	@Autowired
	private UsuarioRepository repositorio; 
	
	@Autowired
	private RolRepository repositorioR;
	
	@Autowired
	private PerfilRepository repositorioP;
	
	private ApiUsuario apiUsuario;

	@Override
	public List<Usuario> listarUsuarios() {
		
		log.info("Inicio metodo Listar Usuarios");
		
		return repositorio.findallWithRoles();
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
	public Usuario obtenerUsuarioPorCorreo(String correo) {
		return apiUsuario.obtenerUsuarioPorCorreo(correo.toLowerCase());
	}
	
	@Override
	public Usuario postUsuario(Usuario usuario) {
		usuario.setCorreo(usuario.getCorreo().toLowerCase());
		return apiUsuario.postUsuario(usuario);
	}
	
	@Override
	public Usuario putUsuarioUpdate(Usuario usuario, Long id) {
		return apiUsuario.putUsuarioUpdate(usuario, id);
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

	@Override
	public List<Perfil> listarPerfiles() {
		return repositorioP.findAll();
	}

	@Override
	public List<Perfil> obtenerPerfiles() {
		return apiUsuario.obtenerPerfiles();
	}

	@Override
	public List<Rol> obtenerRolesPorPerfilId(Long id) {
		return apiUsuario.obtenerRolesPorPerfilId(id);
	}
}
