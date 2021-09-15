package com.rallibau.shared.infraestructure.bus.query.rabbitmq;

import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.bus.query.Query;
import com.rallibau.shared.domain.bus.query.QueryBus;
import com.rallibau.shared.domain.bus.query.QueryHandlerExecutionError;

import javax.transaction.NotSupportedException;

@Service
public class RabbitQueryBus implements QueryBus {
    @Override
    public <R> R ask(Query query) throws QueryHandlerExecutionError {
        throw new QueryHandlerExecutionError(new NotSupportedException());
    }
}
