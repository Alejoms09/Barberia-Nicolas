package com.pag.proyectonicolas.service;

import com.pag.proyectonicolas.dto.AppointmentRequest;
import com.pag.proyectonicolas.dto.AppointmentResponse;
import com.pag.proyectonicolas.model.Appointment;
import com.pag.proyectonicolas.model.Barber;
import com.pag.proyectonicolas.model.ServiceOffering;
import com.pag.proyectonicolas.repository.AppointmentRepository;
import com.pag.proyectonicolas.repository.BarberRepository;
import com.pag.proyectonicolas.repository.ServiceOfferingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final BarberRepository barberRepository;
    private final ServiceOfferingRepository serviceOfferingRepository;

    public AppointmentService(AppointmentRepository appointmentRepository,
                              BarberRepository barberRepository,
                              ServiceOfferingRepository serviceOfferingRepository) {
        this.appointmentRepository = appointmentRepository;
        this.barberRepository = barberRepository;
        this.serviceOfferingRepository = serviceOfferingRepository;
    }

    public List<AppointmentResponse> findAll() {
        return appointmentRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public AppointmentResponse create(AppointmentRequest request) {
        Barber barber = barberRepository.findById(request.getBarberId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Barbero no encontrado"));

        ServiceOffering service = serviceOfferingRepository.findById(request.getServiceId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Servicio no encontrado"));

        appointmentRepository
                .findByBarberIdAndAppointmentDateAndAppointmentTime(
                        request.getBarberId(),
                        request.getAppointmentDate(),
                        request.getAppointmentTime()
                )
                .ifPresent(existing -> {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Ese horario ya esta reservado para este barbero");
                });

        Appointment appointment = new Appointment();
        appointment.setClientName(request.getClientName());
        appointment.setClientEmail(request.getClientEmail());
        appointment.setAppointmentDate(request.getAppointmentDate());
        appointment.setAppointmentTime(request.getAppointmentTime());
        appointment.setBarber(barber);
        appointment.setService(service);
        appointment.setCreatedAt(LocalDateTime.now());

        return toResponse(appointmentRepository.save(appointment));
    }

    private AppointmentResponse toResponse(Appointment appointment) {
        return new AppointmentResponse(
                appointment.getId(),
                appointment.getClientName(),
                appointment.getClientEmail(),
                appointment.getAppointmentDate(),
                appointment.getAppointmentTime(),
                appointment.getBarber().getId(),
                appointment.getBarber().getName(),
                appointment.getService().getId(),
                appointment.getService().getName(),
                appointment.getService().getPrice()
        );
    }
}
