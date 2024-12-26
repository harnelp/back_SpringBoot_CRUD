package crud.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Objects;

/**
 * Clase que representa la entidad Person.
 * Esta entidad está mapeada a una tabla en la base de datos mediante JPA.
 * Incluye validaciones para los campos y una relación de uno a uno con la entidad Address.
 */
@Entity
public class Person {

    /**
     * Identificador único para cada persona (clave primaria).
     * Se genera automáticamente utilizando una estrategia de identidad.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre de la persona.
     * - No puede estar vacío.
     * - Tiene un límite máximo de 50 caracteres.
     */
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 50, message = "El nombre no puede tener más de 50 caracteres")
    private String name;

    /**
     * Número de teléfono de la persona.
     * - Debe contener exactamente 10 dígitos.
     */
    @Pattern(regexp = "\\d{10}", message = "El número de teléfono debe contener 10 dígitos")
    private String phoneNumber;

    /**
     * Dirección de correo electrónico de la persona.
     * - Debe ser válida según el formato estándar de correo electrónico.
     * - No puede estar vacío.
     */
    @Email(message = "Debe proporcionar una dirección de correo válida")
    @NotBlank(message = "El correo electrónico no puede estar vacío")
    private String emailAddress;

    /**
     * Dirección asociada a la persona.
     * - Relación uno a uno con la entidad Address.
     * - Se utiliza CascadeType.ALL para que las operaciones en Person afecten también a Address.
     * - La validación adicional de Address se realiza mediante la anotación @Valid.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    @Valid
    private Address address;

    // Constructor sin argumentos (requerido por JPA)
    public Person() {
    }

    // Getters, setters y métodos adicionales
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    // Métodos equals y hashCode para comparar objetos Person
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) &&
                Objects.equals(name, person.name) &&
                Objects.equals(phoneNumber, person.phoneNumber) &&
                Objects.equals(emailAddress, person.emailAddress) &&
                Objects.equals(address, person.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phoneNumber, emailAddress, address);
    }
}