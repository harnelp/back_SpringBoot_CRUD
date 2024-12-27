# Usa una imagen base con Java 17
FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia todos los archivos del proyecto al contenedor
COPY . .

# Da permisos de ejecución al Maven Wrapper
RUN chmod +x ./mvnw

# Compila la aplicación usando Maven (se omiten las pruebas)
RUN ./mvnw clean package -DskipTests

# Expone el puerto en el contenedor (usualmente 8080 para Spring Boot)
EXPOSE 8080

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "target/demo-0.0.1-SNAPSHOT.jar"]
