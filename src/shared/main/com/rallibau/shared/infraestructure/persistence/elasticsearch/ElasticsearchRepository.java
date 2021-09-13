package com.rallibau.shared.infraestructure.persistence.elasticsearch;

import com.rallibau.shared.domain.criteria.Criteria;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class ElasticsearchRepository<T> {
    private final ElasticsearchClient client;
    private final ElasticsearchCriteriaConverter criteriaConverter;

    public ElasticsearchRepository(ElasticsearchClient client) {
        this.client = client;
        this.criteriaConverter = new ElasticsearchCriteriaConverter();
    }

    abstract protected String moduleName();

    protected List<T> searchAllInElastic(Function<Map<String, Object>, T> unserializer) {
        return all(unserializer, new SearchSourceBuilder());
    }

    protected List<T> all(
            Function<Map<String, Object>, T> unserializer,
            SearchSourceBuilder sourceBuilder
    ) {
        SearchRequest request = new SearchRequest(client.indexFor(moduleName())).source(sourceBuilder);
        try {
            SearchResponse response = client.highLevelClient().search(request, RequestOptions.DEFAULT);

            return Arrays.stream(response.getHits().getHits())
                    .map(hit -> unserializer.apply(hit.getSourceAsMap()))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    protected List<T> byCriteria(Criteria criteria, Function<Map<String, Object>, T> unserializer) {
        return all(unserializer, criteriaConverter.convert(criteria));
    }

    protected void persist(String id, HashMap<String, Serializable> plainBody) {
        try {
            client.persist(moduleName(), id, plainBody);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
