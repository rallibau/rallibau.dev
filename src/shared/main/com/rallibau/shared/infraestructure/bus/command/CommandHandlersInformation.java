package com.rallibau.shared.infraestructure.bus.command;

import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.bus.command.Command;
import com.rallibau.shared.domain.bus.command.CommandHandler;
import com.rallibau.shared.domain.bus.command.CommandNotRegisteredError;
import org.reflections.Reflections;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

@Service
public class CommandHandlersInformation {
    HashMap<Class<? extends Command>, Class<? extends CommandHandler>> indexedCommandHandlers;

    public CommandHandlersInformation() {
        Reflections reflections = new Reflections("com.rallibau");
        Set<Class<? extends CommandHandler>> classes = reflections.getSubTypesOf(CommandHandler.class);

        indexedCommandHandlers = formatHandlers(classes);
    }

    public Class<? extends CommandHandler> search(Class<? extends Command> commandClass) throws CommandNotRegisteredError {
        Class<? extends CommandHandler> commandHandlerClass = indexedCommandHandlers.get(commandClass);

        if (null == commandHandlerClass) {
            throw new CommandNotRegisteredError(commandClass);
        }

        return commandHandlerClass;
    }

    private HashMap<Class<? extends Command>, Class<? extends CommandHandler>> formatHandlers(
            Set<Class<? extends CommandHandler>> commandHandlers
    ) {
        HashMap<Class<? extends Command>, Class<? extends CommandHandler>> handlers = new HashMap<>();

        for (Class<? extends CommandHandler> handler : commandHandlers) {
            ParameterizedType paramType = (ParameterizedType) handler.getGenericInterfaces()[0];
            Class<? extends Command> commandClass = (Class<? extends Command>) paramType.getActualTypeArguments()[0];

            handlers.put(commandClass, handler);
        }

        return handlers;
    }

    public HashMap<Class<? extends Command>, Class<? extends CommandHandler>> all() {
        return indexedCommandHandlers;
    }

    public String[] rabbitMqFormattedNames() {
        ArrayList<String> queues = new ArrayList<>();
        indexedCommandHandlers.keySet().forEach(command ->{
            try {
                queues.add(command.newInstance().formatQueueName());
            } catch (InstantiationException e) {
               // e.printStackTrace();
            } catch (IllegalAccessException e) {
               // e.printStackTrace();
            }
        });
        return (String[]) queues.toArray();
    }
}