package com.rallibau.shared.infraestructure.bus.shared.rabbitmq;

import com.rallibau.shared.domain.bus.query.Query;
import com.rallibau.shared.domain.bus.query.QueryNotRegisteredError;

import java.lang.reflect.InvocationTargetException;

public final class RabbitMqQueriesQueueNameFormatter {
    public static String format(Class<? extends Query> query) throws QueryNotRegisteredError {
        try {
            Query queryInstance = obtainInstance(query);
            return queryInstance.formatQueueName();
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new QueryNotRegisteredError(query);
        }
    }

    public static String formatRetry(Class<? extends Query> query) throws QueryNotRegisteredError {
        return String.format("retry.%s", format(query));
    }

    public static String formatDeadLetter(Class<? extends Query> query) throws QueryNotRegisteredError {
        return String.format("dead_letter.%s", format(query));
    }

    private static Query obtainInstance(Class<? extends Query> query) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        try {
            return query.getDeclaredConstructor().newInstance();
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            return query.getDeclaredConstructor(String.class).newInstance("RANDOM");
        }
    }

}
