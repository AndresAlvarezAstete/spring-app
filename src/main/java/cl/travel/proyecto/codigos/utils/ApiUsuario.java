package cl.travel.proyecto.codigos.utils;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import cl.travel.proyecto.codigos.config.FeignConfig;
import cl.travel.proyecto.codigos.perfiles.Perfil;
import cl.travel.proyecto.codigos.perfiles.Rol;
import cl.travel.proyecto.codigos.perfiles.Usuario;

@FeignClient(name = "api-usuarios-client", configuration = FeignConfig.class, url = "${env.usuarios.url}")
public interface ApiUsuario {

	@GetMapping("/api/usuarios/correo/{correo}")
    Usuario obtenerUsuarioPorCorreo(@PathVariable String correo);
	
	@PostMapping("/api/usuarios")
    Usuario postUsuario(@RequestBody Usuario usuario);
	
	@PutMapping("/api/usuarios/{id}")
    Usuario putUsuarioUpdate(@RequestBody Usuario usuario, @PathVariable Long id);
	
	@GetMapping("/api/perfiles")
    List<Perfil> obtenerPerfiles();
	
	@GetMapping("/api/roles/perfiles/{id}")
    List<Rol> obtenerRolesPorPerfilId(@PathVariable Long id);
}
