package com.pag.proyectonicolas.repository;

import com.pag.proyectonicolas.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    Optional<Appointment> findByBarberIdAndAppointmentDateAndAppointmentTime(Long barberId, LocalDate appointmentDate, LocalTime appointmentTime);
}
