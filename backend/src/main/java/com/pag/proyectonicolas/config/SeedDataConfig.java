package com.pag.proyectonicolas.config;

import com.pag.proyectonicolas.model.Barber;
import com.pag.proyectonicolas.model.ServiceOffering;
import com.pag.proyectonicolas.repository.BarberRepository;
import com.pag.proyectonicolas.repository.ServiceOfferingRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class SeedDataConfig {

    @Bean
    CommandLineRunner seedData(BarberRepository barberRepository, ServiceOfferingRepository serviceOfferingRepository) {
        return args -> {
            if (barberRepository.count() == 0) {
                barberRepository.save(new Barber("Nicolas Vega", "Fade y diseno"));
                barberRepository.save(new Barber("Andres Rojas", "Corte clasico"));
                barberRepository.save(new Barber("Mateo Diaz", "Barba premium"));
            }

            if (serviceOfferingRepository.count() == 0) {
                serviceOfferingRepository.save(new ServiceOffering("Corte clasico", new BigDecimal("12.00"), 35));
                serviceOfferingRepository.save(new ServiceOffering("Fade + Barba", new BigDecimal("18.00"), 55));
                serviceOfferingRepository.save(new ServiceOffering("Perfilado de barba", new BigDecimal("10.00"), 25));
            }
        };
    }
}
