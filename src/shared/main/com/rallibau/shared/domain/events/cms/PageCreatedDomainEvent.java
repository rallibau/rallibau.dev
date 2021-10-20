package com.rallibau.shared.domain.events.cms;

import com.rallibau.shared.domain.bus.event.DomainEvent;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

public final class PageCreatedDomainEvent extends DomainEvent {

    private final String pageTittle;
    private final String pageIdUser;

    public PageCreatedDomainEvent() {
        this.pageIdUser = null;
        this.pageTittle = null;
    }

    public PageCreatedDomainEvent(String aggregateId, String pageTittle, String pageIdUser) {
        super(aggregateId);
        this.pageTittle = pageTittle;
        this.pageIdUser = pageIdUser;
    }

    @Override
    public String eventName() {
        return "page.created";
    }

    public String pageTittle() {
        return pageTittle;
    }

    public String pageIdUser() {
        return pageIdUser;
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<String, Serializable>() {{
            put("userName", pageTittle);
            put("pageIdUser", pageIdUser);
        }};
    }

    @Override
    public PageCreatedDomainEvent fromPrimitives(String aggregateId,
                                                 HashMap<String, Serializable> body,
                                                 String eventId,
                                                 String occurredOn) {
        return new PageCreatedDomainEvent(aggregateId,
                (String) body.get("userName"),
                (String) body.get("pageIdUser")
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PageCreatedDomainEvent that = (PageCreatedDomainEvent) o;
        return aggregateId().equals(that.aggregateId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(aggregateId());
    }
}
