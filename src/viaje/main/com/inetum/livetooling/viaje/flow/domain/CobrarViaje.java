package com.inetum.livetooling.viaje.flow.domain;

import com.inetum.livetooling.shared.domain.Service;
import com.inetum.livetooling.viaje.cruce.domain.*;

import java.util.Optional;

@Service
public class CobrarViaje {
    private final CruceRepository cruceRepository;

    public CobrarViaje(CruceRepository cruceRepository) {
        this.cruceRepository = cruceRepository;
    }

    public void cobrarViaje(CruceId cruceId){
        Optional<Cruce> cruce = cruceRepository.get(cruceId);
        if(cruce.isPresent()) {
           //TODO: Solicitar cobro...
        }
    }
}
