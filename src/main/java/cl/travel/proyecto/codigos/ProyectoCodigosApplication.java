package cl.travel.proyecto.codigos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import cl.travel.proyecto.codigos.perfiles.Usuario;
import cl.travel.proyecto.codigos.repository.UsuarioRepository;

@SpringBootApplication
public class ProyectoCodigosApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoCodigosApplication.class, args);
	}
	
	@Autowired
	private UsuarioRepository repositorio;

	@Override
	public void run(String... args) throws Exception {
		/*
		Usuario usuario1 = new Usuario("16921369-0", "Andres", "Alvarez", "aalvarez@travel.cl");
		repositorio.save(usuario1);
		
		Usuario usuario2 = new Usuario("16921369-1", "Andres", "Astete", "aastete@travel.cl");
		repositorio.save(usuario2);
		*/
	}

}
