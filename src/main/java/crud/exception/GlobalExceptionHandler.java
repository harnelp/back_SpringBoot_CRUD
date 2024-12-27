package crud.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

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
    public ResponseEntity<Map<String, String>> handleEntityNotFoundException(EntityNotFoundException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Maneja excepciones genéricas.
     // Este metodo captura cualquier otra excepción que no esté específicamente manejada en la aplicación.
     // @param ex La excepción genérica capturada.
     // @return Un ResponseEntity con un mensaje genérico de error y el estado HTTP 500 (INTERNAL_SERVER_ERROR).
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGenericException(Exception ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "Ocurrió un error interno");
        errorResponse.put("details", ex.getMessage()); // Opcional: detalles adicionales del error
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}