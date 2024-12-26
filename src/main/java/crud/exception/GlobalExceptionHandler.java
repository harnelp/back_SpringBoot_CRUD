package crud.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Clase que maneja excepciones globales en la aplicación.
 // Utiliza @RestControllerAdvice para interceptar excepciones lanzadas en los controladores
 * y proporcionar respuestas personalizadas para diferentes tipos de errores.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     // Maneja excepciones de tipo EntityNotFoundException.
     // Esta excepción ocurre cuando se intenta acceder a una entidad que no existe en la base de datos.
     // @param ex La excepción EntityNotFoundException capturada.
     // @return Un ResponseEntity con el mensaje de error y el estado HTTP 404 (NOT_FOUND).
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex) {
        // Devuelve el mensaje de error específico y el código de estado HTTP 404
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Maneja excepciones genéricas.
     // Este metodo captura cualquier otra excepción que no esté específicamente manejada en la aplicación.
     // @param ex La excepción genérica capturada.
     // @return Un ResponseEntity con un mensaje genérico de error y el estado HTTP 500 (INTERNAL_SERVER_ERROR).
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        // Devuelve un mensaje genérico para errores no controlados
        return new ResponseEntity<>("Ocurrió un error interno", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}