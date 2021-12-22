package com.rallibau.cms.page.application.find;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rallibau.shared.domain.bus.query.Query;
import com.rallibau.shared.domain.bus.query.Response;
import com.rallibau.shared.domain.bus.query.ResponseJsonParser;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Optional;

public final class GetAllPageQuery extends Query {

    public GetAllPageQuery() {

    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<>();
    }

    @Override
    public GetAllPageQuery fromPrimitives(String aggregateId,
                                          HashMap<String, Serializable> body,
                                          String eventId,
                                          String occurredOn) {
        return new GetAllPageQuery();
    }

    @Override
    public  Response parseResponse(String message){
        try {
            return Optional.of(new ObjectMapper().readValue(message, PagesResponse.class)).get();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
