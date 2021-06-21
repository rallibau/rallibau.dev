package com.inetum.livetooling.shared.domain.bus.command;

public interface CommandBus {
    void dispatch(Command command) throws CommandHandlerExecutionError;

    void asynchronDispatch(Command command) throws  CommandHandlerExecutionError;

}
