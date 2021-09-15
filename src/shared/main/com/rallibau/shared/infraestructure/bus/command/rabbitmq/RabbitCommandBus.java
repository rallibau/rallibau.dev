package com.rallibau.shared.infraestructure.bus.command.rabbitmq;

import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.bus.command.Command;
import com.rallibau.shared.domain.bus.command.CommandBus;
import com.rallibau.shared.domain.bus.command.CommandHandlerExecutionError;

import javax.transaction.NotSupportedException;

@Service
public class RabbitCommandBus implements CommandBus {
    @Override
    public void dispatch(Command command) throws CommandHandlerExecutionError {
        throw new CommandHandlerExecutionError(new NotSupportedException());
    }

    @Override
    public void asynchronDispatch(Command command) throws CommandHandlerExecutionError {
        throw new CommandHandlerExecutionError(new NotSupportedException());
    }
}
