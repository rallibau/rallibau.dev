package com.rallibau.shared.infraestructure.bus.shared.rabbitmq;

import com.rallibau.shared.infraestructure.bus.command.CommandHandlersInformation;
import com.rallibau.shared.infraestructure.bus.event.DomainEventSubscribersInformation;
import com.rallibau.shared.infraestructure.bus.event.DomainEventsInformation;
import com.rallibau.shared.infraestructure.bus.event.rabbitmq.RabbitMqExchangeNameFormatter;
import com.rallibau.shared.infraestructure.bus.event.rabbitmq.RabbitMqQueueNameFormatter;
import com.rallibau.shared.infraestructure.config.ParameterNotExist;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.amqp.core.*;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;
import java.util.stream.Collectors;

@Configuration
public class RabbitMqConfiguration {
    @Value("${rabbit.event.exchange}")
    private String RABBITMQ_EVENT_EXCHANGE;
    @Value("${rabbit.command.exchange}")
    private String RABBITMQ_COMMAND_EXCHANGE;
    @Value("${rabbit.host}")
    private String RABBITMQ_HOST;
    @Value("${rabbit.port}")
    private String RABBITMQ_PORT;
    @Value("${rabbit.login}")
    private String RABBITMQ_LOGIN;
    @Value("${rabbit.password}")
    private String RABBITMQ_PASSWORD;

    private final DomainEventSubscribersInformation domainEventSubscribersInformation;
    private final DomainEventsInformation domainEventsInformation;

    private final CommandHandlersInformation commandHandlersInformation;


    public RabbitMqConfiguration(
            DomainEventSubscribersInformation domainEventSubscribersInformation,
            DomainEventsInformation domainEventsInformation,
            CommandHandlersInformation commandHandlersInformation
    ) throws ParameterNotExist {
        this.domainEventSubscribersInformation = domainEventSubscribersInformation;
        this.domainEventsInformation = domainEventsInformation;
        this.commandHandlersInformation = commandHandlersInformation;
    }

    @Bean
    public CachingConnectionFactory connection() {
        CachingConnectionFactory factory = new CachingConnectionFactory();

        factory.setHost(RABBITMQ_HOST);
        factory.setPort(Integer.parseInt(RABBITMQ_PORT));
        factory.setUsername(RABBITMQ_LOGIN);
        factory.setPassword(RABBITMQ_PASSWORD);

        return factory;
    }

    @Bean
    public Declarables declaration() {
        List<Declarable> declarables = obtainsDeclarablesOfExchange(RABBITMQ_EVENT_EXCHANGE);
        declarables.addAll(obtainsDeclarablesOfExchange(RABBITMQ_COMMAND_EXCHANGE));
        return new Declarables(declarables);
    }

    private List<Declarable> obtainsDeclarablesOfExchange(String exchangeName) {
        String retryExchangeName = RabbitMqExchangeNameFormatter.retry(exchangeName);
        String deadLetterExchangeName = RabbitMqExchangeNameFormatter.deadLetter(exchangeName);

        TopicExchange exchange = new TopicExchange(exchangeName, true, false);
        TopicExchange retryExchange = new TopicExchange(retryExchangeName, true, false);
        TopicExchange deadLetterExchange = new TopicExchange(deadLetterExchangeName, true, false);
        List<Declarable> declarables = new ArrayList<>();
        declarables.add(exchange);
        declarables.add(retryExchange);
        declarables.add(deadLetterExchange);

        if (exchangeName.equals(RABBITMQ_EVENT_EXCHANGE)) {
            Collection<Declarable> queuesAndBindings = declareQueuesAndBindingsEvents(
                    exchange,
                    retryExchange,
                    deadLetterExchange
            ).stream().flatMap(Collection::stream).collect(Collectors.toList());
            declarables.addAll(queuesAndBindings);
        }

        if (exchangeName.equals(RABBITMQ_COMMAND_EXCHANGE)) {
            Collection<Declarable> queuesAndBindings = declareQueuesAndBindingsCommand(
                    exchange,
                    retryExchange,
                    deadLetterExchange
            ).stream().flatMap(Collection::stream).collect(Collectors.toList());
            declarables.addAll(queuesAndBindings);
        }

        return declarables;
    }

    private Collection<Collection<Declarable>> declareQueuesAndBindingsCommand(
            TopicExchange topicExchange,
            TopicExchange retryTopicEchange,
            TopicExchange deadLetterTopicExchange
    ) {
        Collection<Collection<Declarable>> result = new ArrayList<>();
        commandHandlersInformation.all().keySet().forEach(
                command -> {
                    try {
                        String queueName = command.newInstance().formatQueueName();
                        String retryQueueName = command.newInstance().formatQueueRetry();
                        String deadLetterQueueName = command.newInstance().formatQueueRetryLetter();


                        Queue queue = QueueBuilder.durable(queueName).build();
                        Queue retryQueue = QueueBuilder.durable(retryQueueName).withArguments(
                                retryQueueArguments(topicExchange, queueName)
                        ).build();
                        Queue deadLetterQueue = QueueBuilder.durable(deadLetterQueueName).build();

                        Binding fromExchangeSameQueueBinding = BindingBuilder
                                .bind(queue)
                                .to(topicExchange)
                                .with(queueName);

                        Binding fromRetryExchangeSameQueueBinding = BindingBuilder
                                .bind(retryQueue)
                                .to(retryTopicEchange)
                                .with(queueName);

                        Binding fromDeadLetterExchangeSameQueueBinding = BindingBuilder
                                .bind(deadLetterQueue)
                                .to(deadLetterTopicExchange)
                                .with(queueName);


                        List<Binding> fromExchangeDomainEventsBindings = Arrays.asList(
                                BindingBuilder
                                        .bind(queue)
                                        .to(topicExchange)
                                        .with(command.getCanonicalName())
                        );

                        List<Declarable> queuesAndBindings = new ArrayList<>();
                        queuesAndBindings.add(queue);
                        queuesAndBindings.add(fromExchangeSameQueueBinding);
                        queuesAndBindings.addAll(fromExchangeDomainEventsBindings);

                        queuesAndBindings.add(retryQueue);
                        queuesAndBindings.add(fromRetryExchangeSameQueueBinding);

                        queuesAndBindings.add(deadLetterQueue);
                        queuesAndBindings.add(fromDeadLetterExchangeSameQueueBinding);
                        result.add(queuesAndBindings);
                    } catch (InstantiationException e) {
                        //e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        // e.printStackTrace();
                    }
                });

        return result;
    }


    private Collection<Collection<Declarable>> declareQueuesAndBindingsEvents(
            TopicExchange topicExchange,
            TopicExchange retryTopicEchange,
            TopicExchange deadLetterTopicExchange
    ) {

        return domainEventSubscribersInformation.all().stream().map(information -> {
            String queueName = RabbitMqQueueNameFormatter.format(information);
            String retryQueueName = RabbitMqQueueNameFormatter.formatRetry(information);
            String deadLetterQueueName = RabbitMqQueueNameFormatter.formatDeadLetter(information);

            Queue queue = QueueBuilder.durable(queueName).build();
            Queue retryQueue = QueueBuilder.durable(retryQueueName).withArguments(
                    retryQueueArguments(topicExchange, queueName)
            ).build();
            Queue deadLetterQueue = QueueBuilder.durable(deadLetterQueueName).build();

            Binding fromExchangeSameQueueBinding = BindingBuilder
                    .bind(queue)
                    .to(topicExchange)
                    .with(queueName);

            Binding fromRetryExchangeSameQueueBinding = BindingBuilder
                    .bind(retryQueue)
                    .to(retryTopicEchange)
                    .with(queueName);

            Binding fromDeadLetterExchangeSameQueueBinding = BindingBuilder
                    .bind(deadLetterQueue)
                    .to(deadLetterTopicExchange)
                    .with(queueName);

            List<Binding> fromExchangeDomainEventsBindings = information.subscribedEvents().stream().map(
                    domainEventClass -> {
                        String eventName = domainEventsInformation.forClass(domainEventClass);
                        return BindingBuilder
                                .bind(queue)
                                .to(topicExchange)
                                .with(eventName);
                    }).collect(Collectors.toList());

            List<Declarable> queuesAndBindings = new ArrayList<>();
            queuesAndBindings.add(queue);
            queuesAndBindings.add(fromExchangeSameQueueBinding);
            queuesAndBindings.addAll(fromExchangeDomainEventsBindings);

            queuesAndBindings.add(retryQueue);
            queuesAndBindings.add(fromRetryExchangeSameQueueBinding);

            queuesAndBindings.add(deadLetterQueue);
            queuesAndBindings.add(fromDeadLetterExchangeSameQueueBinding);

            return queuesAndBindings;
        }).collect(Collectors.toList());
    }

    @NotNull
    @Contract("_, _ -> new")
    private HashMap<String, Object> retryQueueArguments(TopicExchange exchange, String routingKey) {
        return new HashMap<String, Object>() {{
            put("x-dead-letter-exchange", exchange.getName());
            put("x-dead-letter-routing-key", routingKey);
            put("x-message-ttl", 1000);
        }};
    }
}