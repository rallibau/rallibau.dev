package com.rallibau.shared.infraestructure.bus.command;

import com.rallibau.shared.domain.Utils;
import com.rallibau.shared.domain.bus.command.Command;
import com.rallibau.shared.domain.bus.event.DomainEvent;

import java.io.Serializable;
import java.util.HashMap;

public final class CommandJsonSerializer {
    public static String serialize(Command command) {
        HashMap<String, Serializable> attributes = command.toPrimitives();
        attributes.put("id", command.id());

        return Utils.jsonEncode(new HashMap<String, Serializable>() {{
            put("data", new HashMap<String, Serializable>() {{
                put("id", command.eventId());
                put("type", command.getClass().getName());
                put("occurred_on", command.occurredOn());
                put("attributes", attributes);
            }});
            put("meta", new HashMap<String, Serializable>());
        }});
    }
}
