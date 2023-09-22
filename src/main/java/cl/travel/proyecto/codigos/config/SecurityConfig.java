package cl.travel.proyecto.codigos.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import cl.travel.proyecto.codigos.utils.CustomSuccessAuthenticationHandler;
import cl.travel.proyecto.codigos.utils.TravelDomainFilter;
import lombok.RequiredArgsConstructor;

import static cl.travel.proyecto.codigos.utils.StringConstansUtil.permitAll;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private CustomSuccessAuthenticationHandler customSuccessAuthenticationHandler;
	
	@Value("${ldap.url}")
	private String ldapUrl;
	
	@Value("${ldap.domain}")
    private String ldapDomain;

    @Value("${ldap.dc}")
    private String ldapDc;
    
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        		.csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(new TravelDomainFilter("/login"), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(request -> request
                        .requestMatchers(permitAll)
                        .permitAll()
                        .anyRequest()
                        .fullyAuthenticated())
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/", false))
                        //.successHandler(customSuccessAuthenticationHandler))
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID"));
        return http.build();
    }
    
    @Bean
    public AuthenticationProvider activeDirectoryLdapAuthenticationProvider() {
        ActiveDirectoryLdapAuthenticationProvider authenticationProvider = new ActiveDirectoryLdapAuthenticationProvider(
                ldapDomain,
                ldapUrl,
                ldapDc);
        authenticationProvider.setConvertSubErrorCodesToExceptions(true);
        authenticationProvider.setUseAuthenticationRequestCredentials(true);
        authenticationProvider.setSearchFilter("(&(objectClass=user)(UserPrincipalName={0}))");
        return authenticationProvider;
 	}
}
