package com.rallibau.shared.domain.bus.command;

import com.rallibau.shared.domain.Utils;
import com.rallibau.shared.domain.bus.event.DomainEvent;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

public abstract class Command {

    private String eventId;
    private String occurredOn;

    protected Command() {
        this.eventId = UUID.randomUUID().toString();
        this.occurredOn = Utils.dateToString(LocalDateTime.now());
    }


    public String formatQueueName() {
        return String.format("com.rallibau.%s.%s.%s", contextName(), moduleName(), Utils.toSnake(className()));
    }


    public String formatQueueRetry() {
        return String.format("retry.%s", this.formatQueueName());
    }

    public String formatQueueRetryLetter() {
        return String.format("dead_letter.%s", this.formatQueueName());
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

    public String eventId() {
        return eventId;
    }

    public String occurredOn() {
        return occurredOn;
    }

    public abstract String id();

    public abstract HashMap<String, Serializable> toPrimitives();

    public abstract Command fromPrimitives(
            String aggregateId,
            HashMap<String, Serializable> body,
            String eventId,
            String occurredOn
    );

}
