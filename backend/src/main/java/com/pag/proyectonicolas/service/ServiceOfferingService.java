package com.pag.proyectonicolas.service;

import com.pag.proyectonicolas.model.ServiceOffering;
import com.pag.proyectonicolas.repository.ServiceOfferingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceOfferingService {

    private final ServiceOfferingRepository serviceOfferingRepository;

    public ServiceOfferingService(ServiceOfferingRepository serviceOfferingRepository) {
        this.serviceOfferingRepository = serviceOfferingRepository;
    }

    public List<ServiceOffering> findAll() {
        return serviceOfferingRepository.findAll();
    }
}
