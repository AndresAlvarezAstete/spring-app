package cl.travel.proyecto.codigos.utils;

import java.io.IOException;

import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TravelDomainFilter extends OncePerRequestFilter{

	private final RequestMatcher requestMatcher;
	private final AntPathMatcher pathMatcher = new AntPathMatcher();
	
	public TravelDomainFilter(String loginUrl) {
		this.requestMatcher = request -> pathMatcher.match(loginUrl, request.getServletPath());
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		if (requestMatcher.matches(request)) {
			String username = request.getParameter("username");
			if (username != null && username.endsWith("@travel.cl")) {
				response.sendRedirect("/login?error");
				return;
			}
		}
		filterChain.doFilter(request, response);
	}
}
