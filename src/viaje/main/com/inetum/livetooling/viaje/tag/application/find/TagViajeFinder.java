package com.inetum.livetooling.viaje.tag.application.find;

import com.inetum.livetooling.shared.domain.Service;
import com.inetum.livetooling.shared.domain.criteria.*;
import com.inetum.livetooling.viaje.tag.domain.TagViajesNumero;
import com.inetum.livetooling.viaje.tag.domain.TagViajes;
import com.inetum.livetooling.viaje.tag.domain.TagViajesRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TagViajeFinder {

    private final TagViajesRepository repository;

    public TagViajeFinder(TagViajesRepository repository) {
        this.repository = repository;
    }

    public List<TagViajes> find() {
        Criteria criteria = new Criteria(
                Filters.none(),
                Order.none(),
                Optional.empty(),
                Optional.empty()
        );
        return repository.matching(criteria);

    }

    public List<TagViajes> get(TagViajesNumero numeroViaje) {
        List<Filter> filtersList = new ArrayList<>();
        Filters filters = new Filters(filtersList);
        Filter idFilter = new Filter(new FilterField("numero"),
                FilterOperator.EQUAL,
                new FilterValue(numeroViaje.value()));
        filtersList.add(idFilter);

        Criteria criteria = new Criteria(
                Filters.none(),
                Order.none(),
                Optional.empty(),
                Optional.empty()
        );
        return repository.matching(criteria);

    }


}
