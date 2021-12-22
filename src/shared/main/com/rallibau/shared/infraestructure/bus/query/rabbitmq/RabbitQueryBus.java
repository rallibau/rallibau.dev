package com.rallibau.shared.infraestructure.bus.query.rabbitmq;

import com.rallibau.shared.domain.bus.query.Query;
import com.rallibau.shared.domain.bus.query.QueryBus;
import com.rallibau.shared.domain.bus.query.QueryHandlerExecutionError;
import com.rallibau.shared.domain.bus.query.Response;
import com.rallibau.shared.infraestructure.bus.shared.rabbitmq.RabbitMqPublisher;

import java.io.IOException;

public class RabbitQueryBus implements QueryBus {
    private final RabbitMqPublisher rabbitMqPublisher;
    private final String exchangeName;

    public RabbitQueryBus(RabbitMqPublisher rabbitMqPublisher) {
        this.rabbitMqPublisher = rabbitMqPublisher;
        this.exchangeName = "query_events";
    }

    @Override
    public Response ask(Query query) throws QueryHandlerExecutionError {
        try {
            String result = rabbitMqPublisher.rpc(query, exchangeName);
            return query.parseResponse(result);
        } catch (IOException e) {
            throw new QueryHandlerExecutionError(e);
        }

    }
}
