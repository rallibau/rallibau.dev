package com.inetum.livetooling.viaje.cruce.application.find;

import com.inetum.livetooling.shared.domain.Service;
import com.inetum.livetooling.shared.domain.criteria.Criteria;
import com.inetum.livetooling.shared.domain.criteria.Filters;
import com.inetum.livetooling.shared.domain.criteria.Order;
import com.inetum.livetooling.viaje.cruce.domain.Cruce;
import com.inetum.livetooling.viaje.cruce.domain.CruceId;
import com.inetum.livetooling.viaje.cruce.domain.CruceRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CruceFinder {

    private final CruceRepository repository;

    public CruceFinder(CruceRepository repository) {
        this.repository = repository;
    }

    public CrucesResponse find() {
        CrucesResponse response = new CrucesResponse(new ArrayList<CrucesResponse.CruceResponse>());
        Criteria criteria = new Criteria(
                Filters.none(),
                Order.none(),
                Optional.empty(),
                Optional.empty()
        );
        List<Cruce> cruces = repository.matching(criteria);
        for (Cruce cruce : cruces) {
            response.addCruce(new CrucesResponse.CruceResponse(
                    cruce.id().value(),
                    cruce.ruta().value(), cruce.estado().value(), cruce.tagNumero()));
        }
        return response;
    }

    public Optional<CrucesResponse.CruceResponse> get(CruceId id) {
        Optional<Cruce> cruce = repository.get(id);
        return cruce.map(value -> new CrucesResponse.CruceResponse(
                value.id().value(),
                value.ruta().value(), value.estado().value(), value.tagNumero()));
    }


}
