CREATE DATABASE IF NOT EXISTS barberia_db;
USE barberia_db;

CREATE TABLE IF NOT EXISTS barbers (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(80) NOT NULL,
    specialty VARCHAR(120) NOT NULL
);

CREATE TABLE IF NOT EXISTS service_offerings (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    price DECIMAL(8,2) NOT NULL,
    duration_minutes INT NOT NULL
);

CREATE TABLE IF NOT EXISTS appointments (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    client_name VARCHAR(90) NOT NULL,
    client_email VARCHAR(130) NOT NULL,
    appointment_date DATE NOT NULL,
    appointment_time TIME NOT NULL,
    barber_id BIGINT NOT NULL,
    service_id BIGINT NOT NULL,
    created_at DATETIME NOT NULL,
    CONSTRAINT fk_appointments_barber FOREIGN KEY (barber_id) REFERENCES barbers(id),
    CONSTRAINT fk_appointments_service FOREIGN KEY (service_id) REFERENCES service_offerings(id)
);

INSERT INTO barbers (name, specialty)
SELECT * FROM (
    SELECT 'Nicolas Vega', 'Fade y diseno' UNION ALL
    SELECT 'Andres Rojas', 'Corte clasico' UNION ALL
    SELECT 'Mateo Diaz', 'Barba premium'
) AS seed
WHERE NOT EXISTS (SELECT 1 FROM barbers LIMIT 1);

INSERT INTO service_offerings (name, price, duration_minutes)
SELECT * FROM (
    SELECT 'Corte clasico', 12.00, 35 UNION ALL
    SELECT 'Fade + Barba', 18.00, 55 UNION ALL
    SELECT 'Perfilado de barba', 10.00, 25
) AS seed
WHERE NOT EXISTS (SELECT 1 FROM service_offerings LIMIT 1);
