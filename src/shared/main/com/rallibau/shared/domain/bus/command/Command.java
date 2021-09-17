package com.rallibau.shared.domain.bus.command;

import com.rallibau.shared.domain.Utils;

public interface Command {
    default String formatQueueName() {
        return String.format("com.rallibau.%s.%s.%s", contextName(), moduleName(), Utils.toSnake(className()));
    }


    default String formatQueueRetry() {
        return String.format("retry.%s", this.formatQueueName());
    }

    default String formatQueueRetryLetter() {
        return String.format("dead_letter.%s", this.formatQueueName());
    }


    public default String contextName() {
        String[] nameParts = this.getClass().getName().split("\\.");

        return nameParts[2];
    }

    public default String moduleName() {
        String[] nameParts = this.getClass().getName().split("\\.");

        return nameParts[3];
    }

    public default String className() {
        String[] nameParts = this.getClass().getName().split("\\.");

        return nameParts[nameParts.length - 1];
    }

}
