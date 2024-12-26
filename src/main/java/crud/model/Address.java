package crud.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Objects;

/**
 * Clase que representa la entidad Address.
 * Esta entidad está mapeada a una tabla en la base de datos mediante JPA.
 * Incluye validaciones para los campos como restricciones de longitud,
 * formatos específicos y valores obligatorios.
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

    /**
     * Estado o provincia de la dirección.
     * - No puede estar vacío.
     * - Tiene un límite máximo de 50 caracteres.
     */
    @NotBlank(message = "El estado no puede estar vacío")
    @Size(max = 50, message = "El estado no puede tener más de 50 caracteres")
    private String state;

    /**
     * Código postal de la dirección.
     * - Debe contener exactamente 5 dígitos.
     */
    @Pattern(regexp = "\\d{5}", message = "El código postal debe contener 5 dígitos")
    private String postalCode;

    /**
     * País de la dirección.
     * - No puede estar vacío.
     */
    @NotBlank(message = "El país no puede estar vacío")
    private String country;

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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    // Métodos equals y hashCode para comparar objetos Address
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(id, address.id) &&
                Objects.equals(street, address.street) &&
                Objects.equals(city, address.city) &&
                Objects.equals(state, address.state) &&
                Objects.equals(postalCode, address.postalCode) &&
                Objects.equals(country, address.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, street, city, state, postalCode, country);
    }
}