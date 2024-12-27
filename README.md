# Backend CRUD API - Spring Boot

Este proyecto es un backend para una aplicación CRUD que permite gestionar una lista de personas. Está desarrollado utilizando **Spring Boot** y conectado a una base de datos PostgreSQL. La API expone endpoints para operaciones CRUD (crear, leer, actualizar y eliminar), con soporte para paginación y HATEOAS.

## Características

- CRUD (Crear, Leer, Actualizar y Eliminar) para la entidad `Person`.
- Paginación para listar datos con eficiencia.
- Implementación de HATEOAS para navegabilidad de la API.
- Validación de datos en las entidades.
- Manejo global de excepciones.
- Desplegado en **Render**.

## Tecnologías utilizadas

- **Java** (JDK 17 o superior).
- **Spring Boot** (Framework principal).
  - Spring Data JPA.
  - Spring Web.
  - Spring Validation.
  - Spring HATEOAS.
- **PostgreSQL** (Base de datos relacional).
- **Maven** (Gestor de dependencias y construcción).

## Requisitos previos

1. Tener instalado:
   - [Java JDK 17+](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html).
   - [Maven](https://maven.apache.org/download.cgi).
   - [PostgreSQL](https://www.postgresql.org/download/).
2. Crear una base de datos PostgreSQL con el nombre deseado (ejemplo: `crud_java`).

## Configuración del proyecto

### 1. Configurar las propiedades en `application.properties`:

Asegúrate de que las propiedades para la conexión a la base de datos sean correctas:

```properties
# Configuración de la base de datos
spring.datasource.url=jdbc:postgresql://localhost:5432/crud_java
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña

# Configuración de JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

### 2. Ejecutar la aplicación

1. Clona este repositorio:
   ```bash
   git clone https://github.com/tu-usuario/tu-repositorio-backend.git
   cd tu-repositorio-backend
   ```

2. Compila y ejecuta la aplicación:
   ```bash
   mvn spring-boot:run
   ```

3. La aplicación estará disponible en:
   ```
   http://localhost:8080
   ```

## Endpoints de la API

### Personas (Persons)

| Método | Endpoint                  | Descripción                         |
|--------|---------------------------|-------------------------------------|
| `GET`  | `/api/persons`            | Obtiene todas las personas          |
| `GET`  | `/api/persons/paged`      | Obtiene personas con paginación     |
| `GET`  | `/api/persons/{id}`       | Obtiene una persona por su ID       |
| `POST` | `/api/persons`            | Crea una nueva persona              |
| `PUT`  | `/api/persons/{id}`       | Actualiza una persona por su ID     |
| `DELETE` | `/api/persons/{id}`     | Elimina una persona por su ID       |

### Ejemplo de solicitud `POST`

Para crear una persona, envía una solicitud `POST` a `/api/persons` con un cuerpo JSON como el siguiente:

```json
{
  "name": "Juan Pérez",
  "phoneNumber": "1234567890",
  "emailAddress": "juan.perez@example.com",
  "address": {
    "street": "Calle Falsa 123",
    "city": "Ciudad Ejemplo"
  }
}
```

### Ejemplo de respuesta `GET` con paginación

Solicitud:
```
GET /api/persons/paged?page=0&size=5
```

Respuesta:
```json
{
  "_embedded": {
    "personList": [
      {
        "id": 1,
        "name": "Juan Pérez",
        "phoneNumber": "1234567890",
        "emailAddress": "juan.perez@example.com",
        "address": {
          "street": "Calle Falsa 123",
          "city": "Ciudad Ejemplo"
        },
        "_links": {
          "self": {
            "href": "http://localhost:8080/api/persons/1"
          }
        }
      }
    ]
  },
  "_links": {
    "self": {
      "href": "http://localhost:8080/api/persons/paged?page=0&size=10"
    }
  },
  "page": {
    "size": 10,
    "totalElements": 1,
    "totalPages": 1,
    "number": 0
  }
}
```

## Desplegado

Este backend está desplegado en **Render**. Puedes acceder al API pública en:

👉 [API CRUD Backend en Render](https://springboot-crud-x14q.onrender.com/)

## Contribuciones

1. Haz un fork de este repositorio.
2. Crea tu feature branch (`git checkout -b feature/nueva-funcionalidad`).
3. Realiza tus cambios y haz commit (`git commit -m 'Agrego nueva funcionalidad'`).
4. Haz push a tu branch (`git push origin feature/nueva-funcionalidad`).
5. Crea un Pull Request.
