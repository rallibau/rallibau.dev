package com.rallibau.schedule.task.application.onStoryCreated;

import com.rallibau.shared.domain.Monitor;
import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.bus.event.DomainEventSubscriber;
import com.rallibau.shared.domain.events.scheduler.StoryCreatedDomainEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.event.EventListener;

@Service
@DomainEventSubscriber({StoryCreatedDomainEvent.class})
public class TaskOnStoryCreated {

    private static final Logger logger = LogManager.getLogger(TaskOnStoryCreated.class);

    @EventListener
    @Monitor
    public void on(StoryCreatedDomainEvent event) {


        logger.error("creado story " + event.toPrimitives().get("name"));
    }
}
