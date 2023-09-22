package cl.travel.proyecto.codigos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cl.travel.proyecto.codigos.perfiles.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	@Query("SELECT u FROM Usuario u LEFT JOIN u.roles r")
	List<Usuario> findallWithRoles();

}
