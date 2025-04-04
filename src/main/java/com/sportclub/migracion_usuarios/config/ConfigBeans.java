package com.sportclub.migracion_usuarios.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class ConfigBeans {


    @Bean(name = "messageSource")
    public MessageSource messageSource() {
        ResourceBundleMessageSource bundleMessageSource = new ResourceBundleMessageSource();
        bundleMessageSource.setBasename("messages/errors_en_US");
        bundleMessageSource.setDefaultEncoding("UTF-8");
        bundleMessageSource.setUseCodeAsDefaultMessage(true);
        return bundleMessageSource;
    }


    @Bean
    public OpenAPI customOpenAPI() {
        Info info = new Info()
                .title("API de Taller de Migración de Base de Datos en memoria")
                .description("Documentación de la API de servicio de migracion de base de datos")
                .version("1.0.0");

        return new OpenAPI()
                .info(info)
                .servers(List.of(
                        new Server().url("http://localhost:8080/api")


                ));
    }

    /**
     * Configuración de CORS para permitir solicitudes desde dominios específicos.
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // Dominios permitidos
        configuration.setAllowedOrigins(List.of(
                "https://taller-de-motos-production.up.railway.app/api",
                "http://localhost:8080/api"

        ));
        // Métodos HTTP permitidos
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        // Encabezados permitidos
        configuration.setAllowedHeaders(List.of("*")); // Permitir todos los encabezados
        // Permitir el uso de credenciales (cookies, etc.)
        configuration.setAllowCredentials(true);

        // Configuración del mapeo de rutas
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}