package cl.travel.proyecto.codigos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import cl.travel.proyecto.codigos.perfiles.Usuario;
import cl.travel.proyecto.codigos.service.IUsuarioService;

@Controller
public class UsuarioController {
	
	@Autowired
	private IUsuarioService servicio;
	
	@GetMapping("/usuarios")
	public String listarUsuarios(Model model) {
		model.addAttribute("usuarios", servicio.listarUsuarios());
		return ("admin/usuarios/usuarios");
	}
	
	@GetMapping("/usuarios/nuevo_usuario")
	public String mostrarUsuarios(Model model) {
		Usuario usuario = new Usuario();
		model.addAttribute("usuario", usuario);
		return ("admin/usuarios/crear_usuario");
	}
	
	@PostMapping("/usuarios")
	public String guardarUsuario(@ModelAttribute("usuario") Usuario usuario) {
		servicio.guardarUsuario(usuario);
		return ("redirect:usuarios");
	}
	
	@GetMapping("/usuarios/editar/{id}")
	public String mostrarFormEditar(@PathVariable Long id, Model model) {
		model.addAttribute("usuario", servicio.obtenerUsuarioPorId(id));
		return ("admin/usuarios/editar_usuario");
	}

}
