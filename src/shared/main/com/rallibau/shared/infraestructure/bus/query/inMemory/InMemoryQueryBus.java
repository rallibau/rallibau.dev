package com.rallibau.shared.infraestructure.bus.query.inMemory;

import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.bus.query.*;
import com.rallibau.shared.infraestructure.bus.query.QueryHandlersInformation;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Primary;

@Primary
@Service
public class InMemoryQueryBus implements QueryBus {
    private final QueryHandlersInformation information;
    private final ApplicationContext context;

    public InMemoryQueryBus(QueryHandlersInformation information, ApplicationContext context) {
        this.information = information;
        this.context = context;
    }

    @Override
    public Response ask(Query query) throws QueryHandlerExecutionError {
        try {
            Class<? extends QueryHandler> queryHandlerClass = information.search(query.getClass());

            QueryHandler handler = context.getBean(queryHandlerClass);

            return handler.handle(query);
        } catch (Throwable error) {
            throw new QueryHandlerExecutionError(error);
        }
    }
}
