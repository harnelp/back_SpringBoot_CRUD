package crud.controller;

import crud.model.Person;
import crud.service.PersonService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestionar las operaciones CRUD relacionadas con la entidad Person.
 * Este controlador expone endpoints para obtener, crear, actualizar y eliminar personas.
 */
@RestController
@RequestMapping("/api/persons")
public class PersonController {

    private final PersonService personService;

    // Logger para registrar las solicitudes y eventos importantes
    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

    /**
     * Constructor que inyecta el servicio de Person.
     // @param personService El servicio que maneja la lógica de negocio relacionada con Person.
     */
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    /**
     * Endpoint para obtener todas las personas sin paginación.
     // @return Una lista de todas las personas almacenadas en la base de datos.
     */
    @GetMapping
    public List<Person> getAllPersons() {
        logger.info("Solicitud recibida para obtener todas las personas");
        return personService.getAllPersons();
    }

    /**
     * Endpoint para obtener una lista paginada de personas.
     // @param page Número de la página (por defecto 0).
     // @param size Tamaño de la página (por defecto 10).
     // @return Una página que contiene un subconjunto de personas.
     */
    @GetMapping("/paged")
    public Page<Person> getAllPersonsPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        logger.info("Solicitud recibida para obtener personas con paginación (página: {}, tamaño: {})", page, size);
        return personService.getAllPersons(PageRequest.of(page, size));
    }

    /**
     * Endpoint para obtener una persona específica por su ID.
     // @param id El ID de la persona a buscar.
     // @return Un ResponseEntity que contiene la persona si existe, o un estado 404 si no se encuentra.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        logger.info("Solicitud recibida para obtener la persona con ID: {}", id);
        Person person = personService.getPersonById(id);
        return person != null ? ResponseEntity.ok(person) : ResponseEntity.notFound().build();
    }

    /**
     * Endpoint para crear una nueva persona.
     // @param person Los datos de la nueva persona.
     // @return La persona creada.
     */
    @PostMapping
    public ResponseEntity<?> createPerson(@RequestBody @Valid Person person) {
        try {
            logger.info("Solicitud recibida para crear una nueva persona: {}", person.getName());
            Person savedPerson = personService.savePerson(person);
            return ResponseEntity.ok(savedPerson);
        } catch (Exception e) {
            logger.error("Error al crear una nueva persona", e);
            return ResponseEntity.status(500).body("{\"error\": \"Ocurrió un error al guardar la persona.\"}");
        }
    }


    /**
     * Endpoint para actualizar los datos de una persona existente.
     // @param id El ID de la persona a actualizar.
     // @param personDetails Los nuevos datos de la persona.
     // @return Un ResponseEntity que contiene la persona actualizada si se encuentra, o un estado 404 si no existe.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @Valid @RequestBody Person personDetails) {
        logger.info("Solicitud recibida para actualizar la persona con ID: {}", id);
        Person person = personService.getPersonById(id);
        if (person == null) {
            logger.warn("Persona con ID: {} no encontrada", id);
            return ResponseEntity.notFound().build();
        }
        person.setName(personDetails.getName());
        person.setPhoneNumber(personDetails.getPhoneNumber());
        person.setEmailAddress(personDetails.getEmailAddress());
        person.setAddress(personDetails.getAddress());
        logger.info("Persona con ID: {} actualizada exitosamente", id);
        return ResponseEntity.ok(personService.savePerson(person));
    }

    /**
     * Endpoint para eliminar una persona existente por su ID.
     // @param id El ID de la persona a eliminar.
     // @return Un ResponseEntity con un estado 204 (NO_CONTENT) si se elimina correctamente.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        logger.info("Solicitud recibida para eliminar la persona con ID: {}", id);
        personService.deletePerson(id);
        logger.info("Persona con ID: {} eliminada exitosamente", id);
        return ResponseEntity.noContent().build();
    }
}