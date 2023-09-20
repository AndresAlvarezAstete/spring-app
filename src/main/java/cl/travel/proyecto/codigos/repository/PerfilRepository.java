package cl.travel.proyecto.codigos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.travel.proyecto.codigos.perfiles.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {

}
