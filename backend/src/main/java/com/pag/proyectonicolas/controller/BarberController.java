package com.pag.proyectonicolas.controller;

import com.pag.proyectonicolas.model.Barber;
import com.pag.proyectonicolas.service.BarberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/barbers")
public class BarberController {

    private final BarberService barberService;

    public BarberController(BarberService barberService) {
        this.barberService = barberService;
    }

    @GetMapping
    public List<Barber> findAll() {
        return barberService.findAll();
    }
}
