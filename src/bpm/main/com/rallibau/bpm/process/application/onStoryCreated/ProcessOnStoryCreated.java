package com.rallibau.bpm.process.application.onStoryCreated;

import com.rallibau.shared.domain.Monitor;
import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.bus.event.DomainEventSubscriber;
import com.rallibau.shared.domain.events.scheduler.StoryCreatedDomainEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.event.EventListener;

@Service
@DomainEventSubscriber({StoryCreatedDomainEvent.class})
public class ProcessOnStoryCreated {

    private static final Logger logger = LogManager.getLogger(ProcessOnStoryCreated.class);

    @EventListener
    @Monitor
    public void on(StoryCreatedDomainEvent event) {


        logger.error("creado story desde bpm " + event.toPrimitives().get("name"));
    }
}
