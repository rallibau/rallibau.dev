package com.rallibau.shared.domain.events.scheduler;

import com.rallibau.shared.domain.bus.event.DomainEvent;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

public class StoryCreatedDomainEvent extends DomainEvent {
    private final String storyName;
    private final String processId;

    public StoryCreatedDomainEvent() {
        this.storyName = null;
        this.processId = null;
    }

    public StoryCreatedDomainEvent(String id, String storyName, String processId) {
        super(id);
        this.storyName = storyName;
        this.processId = processId;
    }

    @Override
    public String eventName() {
        return "story.created";
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<String, Serializable>() {{
            put("name", storyName);
            put("processId", processId);
        }};
    }

    @Override
    public StoryCreatedDomainEvent fromPrimitives(String aggregateId, HashMap<String, Serializable> body, String eventId, String occurredOn) {
        return new StoryCreatedDomainEvent(aggregateId,
                (String) body.get("name"),
                (String) body.get("processId"));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StoryCreatedDomainEvent that = (StoryCreatedDomainEvent) o;
        return aggregateId().equals(that.aggregateId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(aggregateId());
    }
}
