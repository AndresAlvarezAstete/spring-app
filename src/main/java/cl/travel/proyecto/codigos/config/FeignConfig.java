package cl.travel.proyecto.codigos.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import feign.RequestInterceptor;

public class FeignConfig {

	@Value("${env.usuarios.key}")
	private String apikey;
	
	@Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("Authorization", apikey);
            requestTemplate.header("Accept", "application/json");
            if (requestTemplate.method().equals("POST")) {
                requestTemplate.header("Content-Type", "application/json");
            }
        };
    }
}
