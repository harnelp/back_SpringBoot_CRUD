package crud.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Objects;

/**
 * Clase que representa la entidad Address.
 * Esta entidad está mapeada a una tabla en la base de datos mediante JPA.
 * Incluye validaciones para los campos como restricciones de longitud y valores obligatorios.
 */
@Entity
public class Address {

    /**
     * Identificador único para cada dirección (clave primaria).
     * Se genera automáticamente utilizando una estrategia de identidad.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Calle de la dirección.
     * - No puede estar vacía.
     * - Tiene un límite máximo de 100 caracteres.
     */
    @NotBlank(message = "La calle no puede estar vacía")
    @Size(max = 100, message = "La calle no puede tener más de 100 caracteres")
    private String street;

    /**
     * Ciudad de la dirección.
     * - No puede estar vacía.
     * - Tiene un límite máximo de 50 caracteres.
     */
    @NotBlank(message = "La ciudad no puede estar vacía")
    @Size(max = 50, message = "La ciudad no puede tener más de 50 caracteres")
    private String city;

    // Getters, setters y métodos adicionales
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    // Métodos equals y hashCode para comparar objetos Address
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(id, address.id) &&
                Objects.equals(street, address.street) &&
                Objects.equals(city, address.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, street, city);
    }
}
