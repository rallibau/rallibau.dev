package com.rallibau.shared.domain.bus.query;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rallibau.shared.domain.Utils;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.UUID;

abstract public class Query {
    private final String correlationId;
    private final String occurredOn;

    public Query() {
        this.correlationId = UUID.randomUUID().toString();
        this.occurredOn = Utils.dateToString(ZonedDateTime.now());
    }

    public String formatQueueName() {
        return String.format("com.rallibau.%s.%s.%s", contextName(), moduleName(), Utils.toSnake(className()));
    }

    public String contextName() {
        String[] nameParts = this.getClass().getName().split("\\.");

        return nameParts[2];
    }

    public String moduleName() {
        String[] nameParts = this.getClass().getName().split("\\.");

        return nameParts[3];
    }

    public String className() {
        String[] nameParts = this.getClass().getName().split("\\.");

        return nameParts[nameParts.length - 1];
    }

    public String correlationId() {
        return correlationId;
    }

    public String occurredOn() {
        return occurredOn;
    }

    public abstract HashMap<String, Serializable> toPrimitives();

    public  Response parseResponse(String message){
        try {
            return ResponseJsonParser.deserialize(message,Response.class).get();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public abstract Query fromPrimitives(
            String aggregateId,
            HashMap<String, Serializable> body,
            String eventId,
            String occurredOn
    );



}
