package crud.service;

import crud.model.Person;
import crud.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 // Clase de servicio para gestionar las operaciones relacionadas con la entidad Person.
 //  Esta clase interactúa con el repositorio de Person para realizar operaciones
 // de creación, lectura, actualización y eliminación (CRUD).
 // Está anotada con @Service, lo que permite que Spring la detecte como un componente
 // de la capa de servicio.
 */
@Service
public class PersonService {

    // Repositorio para realizar las operaciones con la base de datos.
    private final PersonRepository personRepository;

    // Logger para registrar información sobre las operaciones realizadas.
    private static final Logger logger = LoggerFactory.getLogger(PersonService.class);

     //Constructor que inyecta el repositorio de Person.
     // @param personRepository El repositorio de Person.
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    /**
     //Obtiene una lista paginada de todas las personas.
     // @param pageable Información de paginación (número de página, tamaño, etc.).
     // @return Una página de objetos Person.
     */
    public Page<Person> getAllPersons(Pageable pageable) {
        return personRepository.findAll(pageable);
    }

    /**
     // Obtiene una lista completa de todas las personas (sin paginación).
     // @return Lista de objetos Person.
     */
    public List<Person> getAllPersons() {
        logger.info("Obteniendo todas las personas");
        return personRepository.findAll();
    }

    /**
     * Obtiene una persona específica por su ID.
     // @param id El ID de la persona que se desea obtener.
     // @return El objeto Person correspondiente al ID, o null si no se encuentra.
     */
    public Person getPersonById(Long id) {
        logger.info("Obteniendo persona con ID: {}", id);
        return personRepository.findById(id).orElse(null);
    }

    /**
     * Guarda una nueva persona o actualiza una existente en la base de datos.
     //@param person El objeto Person que se desea guardar.
     //@return El objeto Person guardado o actualizado.
     */
    public Person savePerson(Person person) {
        logger.info("Guardando nueva persona: {}", person.getName());
        return personRepository.save(person);
    }

    /**
     // Elimina una persona de la base de datos según su ID.
     // @param id El ID de la persona que se desea eliminar.
     */
    public void deletePerson(Long id) {
        logger.info("Eliminando persona con ID: {}", id);
        personRepository.deleteById(id);
    }
}
