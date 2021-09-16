package com.rallibau.shared.infraestructure.bus.event;

import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.bus.event.DomainEventSubscriber;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Value;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

@Service
public class DomainEventSubscribersInformation {
    HashMap<Class<?>, DomainEventSubscriberInformation> information;
    @Value("${base.package}")
    private String BASE_PACKAGE;

    public DomainEventSubscribersInformation(HashMap<Class<?>, DomainEventSubscriberInformation> information) {
        this.information = information;
    }

    public DomainEventSubscribersInformation() {
        this(scanDomainEventSubscribers());
    }

    private static HashMap<Class<?>, DomainEventSubscriberInformation> scanDomainEventSubscribers() {
        Reflections reflections = new Reflections("com.rallibau");
        Set<Class<?>> subscribers = reflections.getTypesAnnotatedWith(DomainEventSubscriber.class);

        HashMap<Class<?>, DomainEventSubscriberInformation> subscribersInformation = new HashMap<>();

        for (Class<?> subscriberClass : subscribers) {
            DomainEventSubscriber annotation = subscriberClass.getAnnotation(DomainEventSubscriber.class);

            subscribersInformation.put(
                    subscriberClass,
                    new DomainEventSubscriberInformation(subscriberClass, Arrays.asList(annotation.value()))
            );
        }

        return subscribersInformation;
    }

    public Collection<DomainEventSubscriberInformation> all() {
        return information.values();
    }

    public String[] rabbitMqFormattedNames() {
        return information.values()
                .stream()
                .map(DomainEventSubscriberInformation::formatRabbitMqQueueName)
                .distinct()
                .toArray(String[]::new);
    }
}
