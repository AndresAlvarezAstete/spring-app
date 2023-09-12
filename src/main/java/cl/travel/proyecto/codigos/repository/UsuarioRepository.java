package cl.travel.proyecto.codigos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.travel.proyecto.codigos.perfiles.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
