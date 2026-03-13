package com.pag.proyectonicolas.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public record AppointmentResponse(
        Long id,
        String clientName,
        String clientEmail,
        LocalDate appointmentDate,
        LocalTime appointmentTime,
        Long barberId,
        String barberName,
        Long serviceId,
        String serviceName,
        BigDecimal servicePrice
) {
}
