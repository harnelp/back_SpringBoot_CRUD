package crud.controller;

import crud.assembler.PersonModelAssembler;
import crud.model.Person;
import crud.service.PersonService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestionar las operaciones CRUD relacionadas con la entidad Person.
 */
@RestController
@RequestMapping("/api/persons")
public class PersonController {

    private final PersonService personService;
    private final PersonModelAssembler personModelAssembler;
    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

    public PersonController(PersonService personService, PersonModelAssembler personModelAssembler) {
        this.personService = personService;
        this.personModelAssembler = personModelAssembler;
    }

    /**
     * Obtener todas las personas sin paginación.
     */
    @GetMapping
    public List<Person> getAllPersons() {
        logger.info("Solicitud recibida para obtener todas las personas");
        return personService.getAllPersons();
    }

    /**
     * Obtener una lista paginada de personas.
     */
    @GetMapping("/paged")
    public ResponseEntity<PagedModel<EntityModel<Person>>> getAllPersonsPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        logger.info("Solicitud recibida para obtener personas con paginación (página: {}, tamaño: {})", page, size);

        Page<Person> personPage = personService.getAllPersons(PageRequest.of(page, size));
        PagedModel<EntityModel<Person>> pagedModel = personModelAssembler.toPagedModel(personPage);

        return ResponseEntity.ok(pagedModel);
    }

    /**
     * Obtener una persona por ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Person>> getPersonById(@PathVariable Long id) {
        logger.info("Solicitud recibida para obtener la persona con ID: {}", id);

        Person person = personService.getPersonById(id);
        if (person == null) {
            logger.warn("Persona con ID: {} no encontrada", id);
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(personModelAssembler.toModel(person));
    }

    /**
     * Crear una nueva persona.
     */
    @PostMapping
    public ResponseEntity<?> createPerson(@RequestBody @Valid Person person) {
        try {
            logger.info("Solicitud recibida para crear una nueva persona: {}", person.getName());
            Person savedPerson = personService.savePerson(person);
            return ResponseEntity.ok(personModelAssembler.toModel(savedPerson));
        } catch (Exception e) {
            logger.error("Error al crear una nueva persona", e);
            return ResponseEntity.status(500).body("{\"error\": \"Ocurrió un error al guardar la persona.\"}");
        }
    }

    /**
     * Actualizar datos de una persona existente.
     */
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Person>> updatePerson(@PathVariable Long id, @Valid @RequestBody Person personDetails) {
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

        Person updatedPerson = personService.savePerson(person);
        return ResponseEntity.ok(personModelAssembler.toModel(updatedPerson));
    }

    /**
     * Eliminar una persona existente por ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        logger.info("Solicitud recibida para eliminar la persona con ID: {}", id);
        personService.deletePerson(id);
        logger.info("Persona con ID: {} eliminada exitosamente", id);
        return ResponseEntity.noContent().build();
    }
}
