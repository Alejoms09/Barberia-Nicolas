# Etapa 1: compila el proyecto Java y genera el .jar ejecutable.
FROM maven:3.9.11-eclipse-temurin-17 AS build

# Directorio de trabajo dentro del contenedor de build.
WORKDIR /app

# Copiamos pom primero para aprovechar cache de dependencias.
COPY pom.xml .

# Descarga dependencias antes de copiar codigo para acelerar rebuilds.
RUN mvn -B -DskipTests dependency:go-offline

# Copiamos codigo fuente del backend.
COPY src ./src

# Compila y empaqueta el backend Spring Boot.
RUN mvn -B -DskipTests clean package

# Etapa 2: imagen liviana solo para ejecutar el .jar generado.
FROM eclipse-temurin:17-jre

# Directorio de ejecucion de la aplicacion.
WORKDIR /app

# Copia el .jar desde la etapa de build.
COPY --from=build /app/target/*.jar /app/app.jar

# Documenta el puerto que usa Spring Boot.
EXPOSE 8080

# Comando de arranque del backend dentro del contenedor.
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
