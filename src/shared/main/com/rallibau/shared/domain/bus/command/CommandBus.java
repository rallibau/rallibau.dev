package com.rallibau.shared.domain.bus.command;

public interface CommandBus {
    void dispatch(Command command) throws CommandHandlerExecutionError;

    void asynchronousDispatch(Command command) throws  CommandHandlerExecutionError;

}
