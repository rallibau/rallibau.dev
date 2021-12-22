package com.rallibau.shared.infraestructure.bus.query.rabbitmq.command;


import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.bus.query.QueryNotRegisteredError;
import com.rallibau.shared.infraestructure.bus.query.rabbitmq.RabbitMqQueryConsumer;
import com.rallibau.shared.infraestructure.cli.ConsoleCommand;


@Service
public final class ConsumeRabbitMqQuery extends ConsoleCommand {
    private final RabbitMqQueryConsumer queryConsumer;

    public ConsumeRabbitMqQuery(RabbitMqQueryConsumer queryConsumer) {
        this.queryConsumer = queryConsumer;
    }

    @Override
    public void execute(String[] args) throws QueryNotRegisteredError {
        queryConsumer.consume();
    }
}
