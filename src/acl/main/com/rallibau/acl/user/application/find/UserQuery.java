package com.rallibau.acl.user.application.find;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rallibau.shared.domain.authentication.UserDetailResponse;
import com.rallibau.shared.domain.bus.query.Query;
import com.rallibau.shared.domain.bus.query.Response;

import java.io.Serializable;
import java.util.HashMap;

public final class UserQuery extends Query {
    private final String userName;

    public UserQuery() {
        this.userName = null;
    }

    public UserQuery(String userName) {
        this.userName = userName;
    }

    public String userName() {
        return userName;
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<>() {{
            put("userName", userName);
        }};
    }

    @Override
    public Query fromPrimitives(String aggregateId, HashMap<String, Serializable> body, String eventId, String occurredOn) {
        return new UserQuery((String) body.get("userName"));
    }

    @Override
    public Response parseResponse(String message) {
        try {
            return new ObjectMapper().readValue(message, UserDetailResponse.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
