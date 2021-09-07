package com.rallibau.shared.infraestructure.persistence.memory;

import com.rallibau.shared.domain.Identifier;
import com.rallibau.shared.domain.StringValueObject;
import com.rallibau.shared.domain.criteria.Criteria;
import com.rallibau.shared.domain.criteria.Filter;
import com.rallibau.shared.domain.criteria.FilterOperator;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class MemoryRepository<T, Z> {
    protected final Class<T> aggregateClass;
    private List<T> store;
    private final HashMap<FilterOperator, Function<Filter, Predicate<T>>> predicateTransformers =
            new HashMap<FilterOperator, Function<Filter, Predicate<T>>>() {{
                put(FilterOperator.EQUAL, MemoryRepository.this::equalsPredicateTransformer);
                put(FilterOperator.NOT_EQUAL, MemoryRepository.this::notEqualsPredicateTransformer);
                put(FilterOperator.GT, MemoryRepository.this::greaterThanPredicateTransformer);
                put(FilterOperator.LT, MemoryRepository.this::lowerThanPredicateTransformer);
                put(FilterOperator.CONTAINS, MemoryRepository.this::containsPredicateTransformer);
                put(FilterOperator.NOT_CONTAINS, MemoryRepository.this::notContainsPredicateTransformer);
            }};

    public MemoryRepository(Class<T> aggregateClass) {
        this.aggregateClass = aggregateClass;
        store = new ArrayList<>();

    }

    protected void persist(T entity) {
        store.add(entity);
    }

    protected Optional<T> byId(Identifier id) {
        return store.stream().filter(aggregateObject -> id.value().equals("aggregateObject.")).findFirst();
    }

    protected List<T> byCriteria(Criteria criteria) {
        List<T> result = new ArrayList<>(store);
        for (Filter filter : criteria.filters().filters()) {
            List<T> storeAux = new ArrayList<>();
            List<T> resultAux = new ArrayList<>(result);
            store.stream().filter(predicateTransformers.get(filter.operator()).apply(filter)).forEach(storeAux::add);
            resultAux.stream().filter(task -> !storeAux.contains(task)).forEach(result::remove);
        }

        return result;
    }

    protected List<T> all() {
        return store;
    }

    private Predicate<T> equalsPredicateTransformer(Filter filter) {
        return task -> getValue(filter, task).isPresent() && getValue(filter, task).get().equals(filter.value().value());
    }

    private Predicate<T> notEqualsPredicateTransformer(Filter filter) {
        return task -> getValue(filter, task).isPresent() && !getValue(filter, task).get().equals(filter.value().value());
    }

    private Predicate<T> greaterThanPredicateTransformer(Filter filter) {
        return task -> getValue(filter, task).isPresent() && getValue(filter, task).get().compareTo(filter.value().value()) > 0;
    }

    private Predicate<T> lowerThanPredicateTransformer(Filter filter) {
        return task -> getValue(filter, task).isPresent() && getValue(filter, task).get().compareTo(filter.value().value()) < 0;
    }

    private Predicate<T> containsPredicateTransformer(Filter filter) {
        return task -> getValue(filter, task).isPresent() && getValue(filter, task).get().contains(filter.value().value());
    }

    private Predicate<T> notContainsPredicateTransformer(Filter filter) {
        return task -> getValue(filter, task).isPresent() && !getValue(filter, task).get().contains(filter.value().value());
    }

    private Optional<String> getValue(Filter filter, T task) {
        try {
            Method method = task.getClass().getMethod(filter.field().value());
            Object object = method.invoke(task);
            if (object instanceof StringValueObject) {
                return Optional.of(((StringValueObject) object).value());
            }
            return Optional.of(((Identifier) object).value());

        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
