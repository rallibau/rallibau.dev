package com.rallibau.shared.infraestructure.bus.command;

import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.Utils;
import com.rallibau.shared.domain.bus.command.Command;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Optional;

@Service
public class CommandJsonDeserializer {
    private final CommandHandlersInformation information;

    public CommandJsonDeserializer(CommandHandlersInformation information) {
        this.information = information;
    }

    public Optional<Command> deserialize(String body) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        HashMap<String, Serializable> eventData = Utils.jsonDecode(body);
        HashMap<String, Serializable> data = (HashMap<String, Serializable>) eventData.get("data");
        HashMap<String, Serializable> attributes = (HashMap<String, Serializable>) data.get("attributes");
        Optional<Class<? extends Command>> commandClass = information.forName((String) data.get("type"));
        if (commandClass.isEmpty()) {
            return Optional.empty();
        }

        Command nullInstance = commandClass.get().getConstructor().newInstance();

        Method fromPrimitivesMethod = commandClass.get().getMethod(
                "fromPrimitives",
                String.class,
                HashMap.class,
                String.class,
                String.class
        );

        Object domainEvent = fromPrimitivesMethod.invoke(
                nullInstance,
                (String) attributes.get("id"),
                attributes,
                (String) data.get("id"),
                (String) data.get("occurred_on")
        );
        return Optional.of((Command) domainEvent);

    }
}
