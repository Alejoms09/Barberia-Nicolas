package com.pag.proyectonicolas.service;

import com.pag.proyectonicolas.model.Barber;
import com.pag.proyectonicolas.repository.BarberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BarberService {

    private final BarberRepository barberRepository;

    public BarberService(BarberRepository barberRepository) {
        this.barberRepository = barberRepository;
    }

    public List<Barber> findAll() {
        return barberRepository.findAll();
    }
}
