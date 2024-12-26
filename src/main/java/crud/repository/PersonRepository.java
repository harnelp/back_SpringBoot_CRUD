package crud.repository;

import crud.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la entidad Person.
 * Esta interfaz extiende JpaRepository, proporcionando métodos básicos para
 * realizar operaciones CRUD (Create, Read, Update, Delete) sobre la entidad Person.
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    // No es necesario declarar métodos aquí, ya que JpaRepository proporciona
    // todas las operaciones básicas automáticamente.
}