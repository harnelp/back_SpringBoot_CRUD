package crud.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Clase de configuración para habilitar CORS (Cross-Origin Resource Sharing).
 * CORS permite que las aplicaciones frontend (como Vue.js) se comuniquen con el backend
 * en dominios diferentes (por ejemplo, frontend en localhost:5500 y backend en localhost:8080).
 */
@Configuration
public class CorsConfig {

    /**
     // Configuración de CORS para habilitar solicitudes desde dominios específicos.
     // @return Un bean de configuración WebMvcConfigurer que define las reglas de CORS.
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // Configura las rutas permitidas para solicitudes CORS
                registry.addMapping("/**")// Permite todas las rutas del backend
                        .allowedOrigins("https://front-crud-spring.netlify.app", // Dominio del frontend local
                                "http://127.0.0.1:5500") // Dirección alternativa del frontend
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos HTTP permitidos
                        .allowedHeaders("*");// Permite todos los encabezados
            }
        };
    }
}
