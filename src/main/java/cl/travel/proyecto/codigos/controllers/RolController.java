package cl.travel.proyecto.codigos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import cl.travel.proyecto.codigos.perfiles.Rol;
import cl.travel.proyecto.codigos.service.IUsuarioService;

@Controller
public class RolController {
	
	@Autowired
	private IUsuarioService servicio;
	
	@GetMapping("/roles")
	public String listarRoles(Model model) {
		model.addAttribute("roles", servicio.listarRoles());
		return ("admin/roles/roles");
	}
	
	@PostMapping("/roles")
	public String guardarRol(@ModelAttribute("rol") Rol rol) {
		servicio.guardarRol(rol);
		return ("redirect:/roles");
	}
	
	@PostMapping("/roles/{id}")
	public String editarRol(@PathVariable Long id, @ModelAttribute("rol") Rol rol, Model model) {
		Rol rolExistente = servicio.obtenerRolPorId(id);
		rolExistente.setId(id);
		rolExistente.setNombre(rol.getNombre());
		rolExistente.setDescripcion(rol.getDescripcion());
		servicio.editarRol(rolExistente);
		return ("redirect:/roles");
	}
	
	@GetMapping("/roles/{id}")
	public String eliminarRol(@PathVariable Long id) {
		servicio.eliminarRol(id);
		return ("redirect:/roles");
	}
}
