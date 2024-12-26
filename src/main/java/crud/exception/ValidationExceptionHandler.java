package crud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase que maneja excepciones globales relacionadas con validaciones en el controlador.
 * Utiliza la anotación @RestControllerAdvice para interceptar excepciones lanzadas
 * por los controladores y proporcionar respuestas personalizadas.
 */
@RestControllerAdvice
public class ValidationExceptionHandler {

    /**
     * Metodo para manejar excepciones de tipo MethodArgumentNotValidException.
     * Esta excepción ocurre cuando las validaciones anotadas en las entidades (por ejemplo, @NotBlank, @Size)
     * fallan al procesar los datos enviados al backend.
     // @param ex La excepción MethodArgumentNotValidException capturada.
     // @return Un ResponseEntity que contiene un mapa de errores (campo y mensaje asociado)
     //         y un código de estado HTTP 400 (BAD_REQUEST).
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        // Mapa para almacenar los errores: nombre del campo y mensaje de error
        Map<String, String> errors = new HashMap<>();
        // Itera sobre los errores de validación y los agrega al mapa
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        // Devuelve el mapa de errores con el estado HTTP 400
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
