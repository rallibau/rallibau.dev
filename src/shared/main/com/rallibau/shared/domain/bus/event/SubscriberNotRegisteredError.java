package com.rallibau.shared.domain.bus.event;

public final class SubscriberNotRegisteredError extends Exception {
    public SubscriberNotRegisteredError(String queue) {
        super(String.format("There are not registered subscribers for <%s> queue", queue));
    }
}
