# Imagen base oficial de MySQL para la base de datos del proyecto.
FROM mysql:8.4

# Copia script de inicializacion:
# MySQL ejecuta automaticamente archivos *.sql de esta ruta en el primer arranque.
COPY barberia_schema.sql /docker-entrypoint-initdb.d/01_barberia_schema.sql

# Documenta el puerto del servicio MySQL dentro del contenedor.
EXPOSE 3306
