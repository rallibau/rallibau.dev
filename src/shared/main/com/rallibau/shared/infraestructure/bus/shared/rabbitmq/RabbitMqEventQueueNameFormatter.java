package com.rallibau.shared.infraestructure.bus.shared.rabbitmq;

import com.rallibau.shared.infraestructure.bus.event.DomainEventSubscriberInformation;

public final class RabbitMqEventQueueNameFormatter {
    public static String format(DomainEventSubscriberInformation information) {
        return information.formatRabbitMqQueueName();
    }

    public static String formatRetry(DomainEventSubscriberInformation information) {
        return String.format("retry.%s", format(information));
    }

    public static String formatDeadLetter(DomainEventSubscriberInformation information) {
        return String.format("dead_letter.%s", format(information));
    }


}
