package com.pag.proyectonicolas.controller;

import com.pag.proyectonicolas.model.ServiceOffering;
import com.pag.proyectonicolas.service.ServiceOfferingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceOfferingController {

    private final ServiceOfferingService serviceOfferingService;

    public ServiceOfferingController(ServiceOfferingService serviceOfferingService) {
        this.serviceOfferingService = serviceOfferingService;
    }

    @GetMapping
    public List<ServiceOffering> findAll() {
        return serviceOfferingService.findAll();
    }
}
