package com.rallibau.bpm.process.application.onProcessCreated;

import com.rallibau.shared.domain.Monitor;
import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.bus.event.DomainEventSubscriber;
import com.rallibau.shared.domain.events.bpm.ProcessCreatedDomainEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.event.EventListener;

@Service
@DomainEventSubscriber({ProcessCreatedDomainEvent.class})
public  class NotifyProcessCreated {

    private static final Logger logger = LogManager.getLogger(NotifyProcessCreated.class);

    @EventListener
    @Monitor
    public void on(ProcessCreatedDomainEvent event){

        logger.error("creado proceso "+event.eventId());
    }
}
