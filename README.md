# Proyecto Barberia (Separado y Dockerizado)

Este proyecto quedo reorganizado en 3 partes separadas:
- `backend/` -> API REST con Spring Boot.
- `backend/database/` -> archivos de base de datos y Dockerfile de MySQL.
- `frontend/` -> interfaz web en HTML/CSS/JS servida con Nginx.

## Estructura
```text
.
├─ backend/
│  ├─ database/
│  │  ├─ barberia_schema.sql
│  │  └─ Dockerfile
│  ├─ src/
│  ├─ Dockerfile
│  └─ pom.xml
├─ frontend/
│  ├─ css/
│  ├─ js/
│  ├─ index.html
│  ├─ nginx.conf
│  └─ Dockerfile
└─ docker-compose.yml
```

## Docker (3 imagenes)
`docker-compose.yml` construye y levanta tres imagenes:
1. `barberia-frontend:latest`
2. `barberia-backend:latest`
3. `barberia-db:latest`

## Ejecutar con Docker
Desde la raiz del proyecto:
```bash
docker compose up --build
```

Accesos:
- Frontend: `http://localhost:8081`
- Backend API: `http://localhost:8080`
- MySQL: red interna de Docker (`db:3306`, no expuesto al host por defecto)

Para detener:
```bash
docker compose down
```

Para detener y borrar volumen de DB:
```bash
docker compose down -v
```

## Ejecutar solo backend (sin Docker)
Desde `backend/`:
```bash
./mvnw spring-boot:run
```
En Windows PowerShell puedes usar: `.\mvnw.cmd spring-boot:run`

Perfil Docker (MySQL por contenedor):
```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=docker
```
En Windows PowerShell: `.\mvnw.cmd spring-boot:run -Dspring-boot.run.profiles=docker`

## Endpoints API
- `GET /api/barbers`
- `GET /api/services`
- `GET /api/appointments`
- `POST /api/appointments`

Ejemplo body `POST /api/appointments`:
```json
{
  "clientName": "Juan Perez",
  "clientEmail": "juan@mail.com",
  "appointmentDate": "2026-03-10",
  "appointmentTime": "15:30",
  "barberId": 1,
  "serviceId": 2
}
```
