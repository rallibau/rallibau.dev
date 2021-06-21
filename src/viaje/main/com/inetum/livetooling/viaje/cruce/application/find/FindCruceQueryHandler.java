package com.inetum.livetooling.viaje.cruce.application.find;

import com.inetum.livetooling.shared.domain.Service;
import com.inetum.livetooling.shared.domain.bus.query.QueryHandler;
import com.inetum.livetooling.viaje.cruce.domain.CruceId;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class FindCruceQueryHandler implements QueryHandler<FindCruceQuery, CrucesResponse> {
    private final CruceFinder cruceFinder;

    public FindCruceQueryHandler(CruceFinder cruceFinder) {
        this.cruceFinder = cruceFinder;
    }

    @Override
    public CrucesResponse handle(FindCruceQuery query) {
        if(query.cruceId() != null && !query.cruceId().isEmpty()) {
            CrucesResponse response = new CrucesResponse(new ArrayList<>());
            Optional<CrucesResponse.CruceResponse> cruce = cruceFinder.get(new CruceId(query.cruceId()));
            cruce.ifPresent(response::addCruce);
            return response;
        }
        return cruceFinder.find();
    }
}
