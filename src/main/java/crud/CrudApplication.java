package crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal que inicia la aplicación CRUD.
 * Esta clase utiliza la anotación @SpringBootApplication,
 * que marca esta clase como el punto de entrada para una aplicación Spring Boot.
 */

@SpringBootApplication
public class CrudApplication {

	public static void main(String[] args) {
		// Metodo estático que inicializa el contexto de Spring y arranca la aplicación.
		SpringApplication.run(CrudApplication.class, args);
	}

}
