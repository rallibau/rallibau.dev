package com.rallibau.shared.infraestructure.bus.command.rabbitmq;

import com.rallibau.shared.domain.bus.command.Command;
import com.rallibau.shared.domain.bus.command.CommandBus;
import com.rallibau.shared.domain.bus.command.CommandHandlerExecutionError;
import com.rallibau.shared.infraestructure.bus.shared.rabbitmq.RabbitMqPublisher;

import javax.transaction.NotSupportedException;

public class RabbitCommandBus implements CommandBus {
    private final RabbitMqPublisher publisher;
    private final String exchangeName;

    public RabbitCommandBus(RabbitMqPublisher publisher) {
        this.publisher = publisher;
        this.exchangeName = "command_events";
    }


    @Override
    public void dispatch(Command command) throws CommandHandlerExecutionError {
        this.publisher.publish(command, exchangeName);
    }

    @Override
    public void asynchronDispatch(Command command) throws CommandHandlerExecutionError {
        throw new CommandHandlerExecutionError(new NotSupportedException());
    }
}
