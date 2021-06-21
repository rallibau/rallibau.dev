package com.inetum.livetooling.viaje.cruce.application.find;

import com.inetum.livetooling.shared.domain.bus.query.Query;

public class FindCruceQuery implements Query {
    private final String cruceId;

    public FindCruceQuery() {
        this.cruceId = null;
    }

    public FindCruceQuery(String cruceId) {
        this.cruceId = cruceId;
    }

    public String cruceId() {
        return cruceId;
    }
}
