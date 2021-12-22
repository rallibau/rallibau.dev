package com.rallibau.shared.infraestructure.bus.query;

import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.Utils;
import com.rallibau.shared.domain.bus.query.Query;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Optional;

@Service
public class QueryJsonDeserializer {
    private final QueryHandlersInformation information;

    public QueryJsonDeserializer(QueryHandlersInformation information) {
        this.information = information;
    }

    public Optional<Query> deserialize(String body) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException {
        HashMap<String, Serializable> eventData = Utils.jsonDecode(body);
        HashMap<String, Serializable> data = (HashMap<String, Serializable>) eventData.get("data");
        HashMap<String, Serializable> attributes = (HashMap<String, Serializable>) data.get("attributes");
        Optional<Class<? extends Query>> queryClass = information.forName((String) data.get("type"));
        if (queryClass.isEmpty()) {
            return Optional.empty();
        }

        Query nullInstance = queryClass.get().getConstructor().newInstance();

        Method fromPrimitivesMethod = queryClass.get().getMethod(
                "fromPrimitives",
                String.class,
                HashMap.class,
                String.class,
                String.class
        );

        Object query = fromPrimitivesMethod.invoke(
                nullInstance,
                (String) attributes.get("id"),
                attributes,
                (String) data.get("id"),
                (String) data.get("occurred_on")
        );
        return Optional.of((Query) query);

    }
}
