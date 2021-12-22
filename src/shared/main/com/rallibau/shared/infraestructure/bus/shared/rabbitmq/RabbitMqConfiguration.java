package com.rallibau.shared.infraestructure.bus.shared.rabbitmq;

import com.rallibau.shared.domain.bus.command.Command;
import com.rallibau.shared.domain.bus.command.CommandNotRegisteredError;
import com.rallibau.shared.domain.bus.query.Query;
import com.rallibau.shared.domain.bus.query.QueryNotRegisteredError;
import com.rallibau.shared.infraestructure.bus.command.CommandHandlersInformation;
import com.rallibau.shared.infraestructure.bus.event.DomainEventSubscribersInformation;
import com.rallibau.shared.infraestructure.bus.event.DomainEventsInformation;
import com.rallibau.shared.infraestructure.bus.query.QueryHandlersInformation;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;
import java.util.stream.Collectors;

@Configuration
public class RabbitMqConfiguration {
    public static final String EVENT_STORE_QUEUE_NAME = "com.rallibau.share.event.store";
    public static final String QUERY_QUEUE_NAME = "rpc_queue";
    @Value("${rabbit.event.exchange}")
    private String RABBITMQ_EVENT_EXCHANGE;
    @Value("${rabbit.command.exchange}")
    private String RABBITMQ_COMMAND_EXCHANGE;
    @Value("${rabbit.store.exchange}")
    private String RABBITMQ_STORE_EXCHANGE;
    private String RABBITMQ_QUERY_EXCHANGE = "query_events";
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
    private final QueryHandlersInformation queryHandlersInformation;


    public RabbitMqConfiguration(
            DomainEventSubscribersInformation domainEventSubscribersInformation,
            DomainEventsInformation domainEventsInformation,
            CommandHandlersInformation commandHandlersInformation,
            QueryHandlersInformation queryHandlersInformation) {
        this.domainEventSubscribersInformation = domainEventSubscribersInformation;
        this.domainEventsInformation = domainEventsInformation;
        this.commandHandlersInformation = commandHandlersInformation;
        this.queryHandlersInformation = queryHandlersInformation;
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
    public Declarables declaration() throws CommandNotRegisteredError, QueryNotRegisteredError {
        List<Declarable> declarable = obtainsDeclarableOfExchange(RABBITMQ_EVENT_EXCHANGE);
        declarable.addAll(obtainsDeclarableOfExchange(RABBITMQ_COMMAND_EXCHANGE));
        declarable.addAll(obtainsDeclarableOfExchange(RABBITMQ_QUERY_EXCHANGE));
        declarable.addAll(obtainsDeclarableOfEventStore());
        // declarable.addAll(obtainsDeclarableOfRPC());
        return new Declarables(declarable);
    }

    private List<Declarable> obtainsDeclarableOfEventStore() {
        List<Declarable> declarable = new ArrayList<>();
        TopicExchange eventStoreExchange = new TopicExchange(RABBITMQ_STORE_EXCHANGE, true, false);
        Queue queue = QueueBuilder.durable(EVENT_STORE_QUEUE_NAME).build();
        Binding fromExchangeSameQueueBinding = BindingBuilder
                .bind(queue)
                .to(eventStoreExchange)
                .with(EVENT_STORE_QUEUE_NAME);
        declarable.add(eventStoreExchange);
        declarable.add(queue);
        declarable.add(fromExchangeSameQueueBinding);
        return declarable;
    }

    private List<Declarable> obtainsDeclarableOfRPC() {
        List<Declarable> declarable = new ArrayList<>();
        TopicExchange rpcExchange = new TopicExchange(RABBITMQ_QUERY_EXCHANGE, true, false);
        Queue queue = QueueBuilder.durable(QUERY_QUEUE_NAME).build();
        Binding fromExchangeSameQueueBinding = BindingBuilder
                .bind(queue)
                .to(rpcExchange)
                .with(QUERY_QUEUE_NAME);
        declarable.add(rpcExchange);
        declarable.add(queue);
        declarable.add(fromExchangeSameQueueBinding);
        return declarable;
    }

    private List<Declarable> obtainsDeclarableOfExchange(String exchangeName) throws CommandNotRegisteredError,
            QueryNotRegisteredError {
        String retryExchangeName = RabbitMqExchangeNameFormatter.retry(exchangeName);
        String deadLetterExchangeName = RabbitMqExchangeNameFormatter.deadLetter(exchangeName);

        TopicExchange exchange = new TopicExchange(exchangeName, true, false);
        TopicExchange retryExchange = new TopicExchange(retryExchangeName, true, false);
        TopicExchange deadLetterExchange = new TopicExchange(deadLetterExchangeName, true, false);
        List<Declarable> declarable = new ArrayList<>();
        declarable.add(exchange);
        declarable.add(retryExchange);
        declarable.add(deadLetterExchange);

        if (exchangeName.equals(RABBITMQ_EVENT_EXCHANGE)) {
            Collection<Declarable> queuesAndBindings = declareQueuesAndBindingsEvents(
                    exchange,
                    retryExchange,
                    deadLetterExchange
            ).stream().flatMap(Collection::stream).collect(Collectors.toList());
            declarable.addAll(queuesAndBindings);
        }

        if (exchangeName.equals(RABBITMQ_COMMAND_EXCHANGE)) {
            Collection<Declarable> queuesAndBindings = declareQueuesAndBindingsCommand(
                    exchange,
                    retryExchange,
                    deadLetterExchange
            ).stream().flatMap(Collection::stream).collect(Collectors.toList());
            declarable.addAll(queuesAndBindings);
        }

        if (exchangeName.equals(RABBITMQ_QUERY_EXCHANGE)) {
            Collection<Declarable> queuesAndBindings = declareQueuesAndBindingsQueries(
                    exchange,
                    retryExchange,
                    deadLetterExchange
            ).stream().flatMap(Collection::stream).collect(Collectors.toList());
            declarable.addAll(queuesAndBindings);
        }


        return declarable;
    }

    private Collection<Collection<Declarable>> declareQueuesAndBindingsQueries(
            TopicExchange topicExchange,
            TopicExchange retryTopicExchange,
            TopicExchange deadLetterTopicExchange
    ) throws QueryNotRegisteredError {
        Collection<Collection<Declarable>> result = new ArrayList<>();
        for (Class<? extends Query> query : queryHandlersInformation.all().keySet()) {
            String queueName = RabbitMqQueriesQueueNameFormatter.format(query);
            String retryQueueName = RabbitMqQueriesQueueNameFormatter.formatRetry(query);
            String deadLetterQueueName = RabbitMqQueriesQueueNameFormatter.formatDeadLetter(query);


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
                    .to(retryTopicExchange)
                    .with(queueName);

            Binding fromDeadLetterExchangeSameQueueBinding = BindingBuilder
                    .bind(deadLetterQueue)
                    .to(deadLetterTopicExchange)
                    .with(queueName);


            List<Binding> fromExchangeDomainEventsBindings = Collections.singletonList(
                    BindingBuilder
                            .bind(queue)
                            .to(topicExchange)
                            .with(query.getCanonicalName())
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

        }

        return result;
    }

    private Collection<Collection<Declarable>> declareQueuesAndBindingsCommand(
            TopicExchange topicExchange,
            TopicExchange retryTopicExchange,
            TopicExchange deadLetterTopicExchange
    ) throws CommandNotRegisteredError {
        Collection<Collection<Declarable>> result = new ArrayList<>();
        for (Class<? extends Command> command : commandHandlersInformation.all().keySet()) {
            String queueName = RabbitMqCommandQueueNameFormatter.format(command);
            String retryQueueName = RabbitMqCommandQueueNameFormatter.formatRetry(command);
            String deadLetterQueueName = RabbitMqCommandQueueNameFormatter.formatDeadLetter(command);


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
                    .to(retryTopicExchange)
                    .with(queueName);

            Binding fromDeadLetterExchangeSameQueueBinding = BindingBuilder
                    .bind(deadLetterQueue)
                    .to(deadLetterTopicExchange)
                    .with(queueName);


            List<Binding> fromExchangeDomainEventsBindings = Collections.singletonList(
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

        }

        return result;
    }


    private Collection<Collection<Declarable>> declareQueuesAndBindingsEvents(
            TopicExchange topicExchange,
            TopicExchange retryTopicExchange,
            TopicExchange deadLetterTopicExchange
    ) {

        return domainEventSubscribersInformation.all().stream().map(information -> {
            String queueName = RabbitMqEventQueueNameFormatter.format(information);
            String retryQueueName = RabbitMqEventQueueNameFormatter.formatRetry(information);
            String deadLetterQueueName = RabbitMqEventQueueNameFormatter.formatDeadLetter(information);

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
                    .to(retryTopicExchange)
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

    private HashMap<String, Object> retryQueueArguments(TopicExchange exchange, String routingKey) {
        return new HashMap<>() {{
            put("x-dead-letter-exchange", exchange.getName());
            put("x-dead-letter-routing-key", routingKey);
            put("x-message-ttl", 1000);
        }};
    }
}
