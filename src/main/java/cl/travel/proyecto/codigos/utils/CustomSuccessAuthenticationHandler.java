package cl.travel.proyecto.codigos.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;

import cl.travel.proyecto.codigos.perfiles.Rol;
import cl.travel.proyecto.codigos.perfiles.Usuario;
import cl.travel.proyecto.codigos.service.UsuarioServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static cl.travel.proyecto.codigos.utils.StringConstansUtil.DOMAIN;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomSuccessAuthenticationHandler extends SimpleUrlAuthenticationSuccessHandler{

	private ApiUsuario apiUsuario;
	
	private static final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);
	
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Set<GrantedAuthority> mappedAuthorities = new HashSet<>();
        String nombre = userDetails.getUsername();
        if (nombre.contains("@")) {
            nombre = nombre.split("@")[0];
        }
        request.getSession().setAttribute("principal", nombre);
        String correo = nombre + DOMAIN;
        Date now = new Date();
        log.info("Identificando usuario");
        Usuario usuario = null;
        try {
            usuario = apiUsuario.obtenerUsuarioPorCorreo(correo);
            if (Objects.isNull(usuario)) {
                log.info("Usuario no encontrado con correo=" + correo);
                log.info("Registrando en API");
                apiUsuario.postUsuario(usuario);
            } else {
                log.info("Usuario encontrado mediante correo usuario=" + usuario);
                apiUsuario.putUsuarioUpdate(usuario, usuario.getId());
            }
        } catch (ResourceAccessException e) {
            log.warn("API Beneficios no se encuentra disponible para el registro de usuarios");
            log.error(e.getMessage());
        } catch (Exception e) {
            log.warn("Excepción no controlada al consultar API Beneficios");
            log.error(e.getMessage());
        }


        log.info("nombres=" + nombre + ", correo=" + correo + ", inicia sesión");
        if (Objects.nonNull(usuario)) {
            log.info("Usuario encontrado=" + usuario);
            log.info("usuario nombres=" + nombre + ", identificando roles con perfilId=" + usuario.getPerfiles());
            List<Rol> roles = usuario.getPerfiles() != null ? apiUsuario.obtenerRolesPorPerfilId(usuario.getPerfil().getId()) : new ArrayList<>();
            log.info("usuario nombres=" + nombre + ", seteando roles=" + roles);
            roles.stream()
                    .map(r -> "ROLE_" + r.getNombre())
                    .forEach(rol -> mappedAuthorities.add((GrantedAuthority) () -> rol));
        }
        if (Objects.nonNull(usuario)) {
            setRoles(new ArrayList<>(mappedAuthorities));
        }
        super.onAuthenticationSuccess(request, response, authentication);
  }

    private void setRoles(List<GrantedAuthority> roles) {
        Collection<SimpleGrantedAuthority> oldAuth = (Collection<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        List<GrantedAuthority> updatedAuth = new ArrayList<>();
        updatedAuth.addAll(roles);
        updatedAuth.addAll(oldAuth);

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                SecurityContextHolder.getContext().getAuthentication().getPrincipal(),
                SecurityContextHolder.getContext().getAuthentication().getCredentials(),
                updatedAuth
        ));
  }
}
