package com.rallibau.shared.infraestructure.bus.query.rabbitmq;

import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.Utils;
import com.rallibau.shared.domain.bus.query.Query;
import com.rallibau.shared.domain.bus.query.QueryNotRegisteredError;
import com.rallibau.shared.domain.bus.query.Response;
import com.rallibau.shared.domain.bus.query.ResponseJsonParser;
import com.rallibau.shared.infraestructure.bus.query.QueryHandlersInformation;
import com.rallibau.shared.infraestructure.bus.query.QueryJsonDeserializer;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.AbstractMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Optional;

@Service
public class RabbitMqQueryConsumer {

    private final QueryJsonDeserializer queryJsonDeserializer;
    private static final String RPC_QUEUE_NAME = "rpc_queue";
    private final ApplicationContext context;
    private final HashMap<String, Optional<Object>> queriesSubscribers = new HashMap<>();
    private final QueryHandlersInformation queryHandlersInformation;

    private final String CONSUMER_NAME = "query_events_consumer";
    RabbitListenerEndpointRegistry registry;

    public RabbitMqQueryConsumer(
            QueryJsonDeserializer queryJsonDeserializer, ApplicationContext context, QueryHandlersInformation queryHandlersInformation, RabbitListenerEndpointRegistry registry
    ) {
        this.queryJsonDeserializer = queryJsonDeserializer;
        this.context = context;
        this.queryHandlersInformation = queryHandlersInformation;
        this.registry = registry;
    }

    public void consume() throws QueryNotRegisteredError {
        AbstractMessageListenerContainer container = (AbstractMessageListenerContainer) registry.getListenerContainer(
                CONSUMER_NAME
        );

        container.addQueueNames(queryHandlersInformation.rabbitMqFormattedNames());

        container.start();
    }

    @RabbitListener(id = CONSUMER_NAME, autoStartup = "true")
    public Message consumer(Message message) throws Exception {

        String serializedMessage = new String(message.getBody());

        String queue = message.getMessageProperties().getConsumerQueue();


        Optional<Object> subscriberOptional = queriesSubscribers.containsKey(queue)
                ? queriesSubscribers.get(queue)
                : subscriberFor(queue);

        if (subscriberOptional.isEmpty()) {
            throw new Exception(String.format("There are not registered subscribers for <%s> queue", queue));
        }
        Optional<Query> query = Optional.empty();
        Response response = null;
        try {
            query = queryJsonDeserializer.deserialize(serializedMessage);
            Object subscriber = subscriberOptional.get();
            if (query.isEmpty()) {
                throw new Exception("Message can not be empty");
            }
            Method commandHandleMethod = subscriber.getClass().getMethod("handle", query.get().getClass());
            response = (Response) commandHandleMethod.invoke(subscriber, query.get());
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException error) {
            throw new Exception(String.format(
                    "The subscriber <%s> should implement a method `handle` to manage query <%s>",
                    queue,
                    query.isPresent() ? query.get().className() : "UNDEFINED"
            ));

        } catch (Exception error) {
            error.printStackTrace();
        }

        return new Message(
                ResponseJsonParser.serialize(response).getBytes(),
                MessagePropertiesBuilder.newInstance()
                        .setContentEncoding("utf-8")
                        .setContentType("application/json")
                        .build()
        );
    }

    private Optional<Object> subscriberFor(String queue) {
        String[] queueParts = queue.split("\\.");
        String queueHandlerName = Utils.toCamelFirstLower(queueParts[queueParts.length - 1]).concat("Handler");

        if (context.containsBean(queueHandlerName)) {
            Optional<Object> queryHandler = Optional.of(context.getBean(queueHandlerName));
            queriesSubscribers.put(queue, queryHandler);
            return queryHandler;
        }
        return Optional.empty();

    }

}
