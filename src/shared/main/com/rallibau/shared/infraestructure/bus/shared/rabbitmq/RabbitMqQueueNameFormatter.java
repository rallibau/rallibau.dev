package com.rallibau.shared.infraestructure.bus.shared.rabbitmq;

import com.rallibau.shared.domain.bus.command.Command;
import com.rallibau.shared.domain.bus.command.CommandNotRegisteredError;
import com.rallibau.shared.infraestructure.bus.event.DomainEventSubscriberInformation;

import java.lang.reflect.InvocationTargetException;

public final class RabbitMqQueueNameFormatter {
    public static String format(DomainEventSubscriberInformation information) {
        return information.formatRabbitMqQueueName();
    }

    public static String formatRetry(DomainEventSubscriberInformation information) {
        return String.format("retry.%s", format(information));
    }

    public static String formatDeadLetter(DomainEventSubscriberInformation information) {
        return String.format("dead_letter.%s", format(information));
    }

    public static String format(Class<? extends Command> command) throws CommandNotRegisteredError {
        try {
            Command commandInstance = command.getDeclaredConstructor().newInstance();
            return commandInstance.formatQueueName();
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new CommandNotRegisteredError(command);
        }
    }
    public static String formatRetry(Class<? extends Command> command) throws CommandNotRegisteredError {
        return String.format("retry.%s", format(command));
    }

    public static String formatDeadLetter(Class<? extends Command> command) throws CommandNotRegisteredError {
        return String.format("dead_letter.%s", format(command));
    }
}
