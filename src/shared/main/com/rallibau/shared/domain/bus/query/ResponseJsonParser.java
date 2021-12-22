package com.rallibau.shared.domain.bus.query;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Optional;

public final class ResponseJsonParser {
    public static String serialize(Response response) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(response);

    }

    public static Optional<Response> deserialize(String body, Class<Response> serializable) throws JsonProcessingException {
        return Optional.of(new ObjectMapper().readValue(body, serializable));

    }
}
