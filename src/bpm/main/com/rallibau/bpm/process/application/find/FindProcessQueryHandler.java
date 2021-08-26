package com.rallibau.bpm.process.application.find;

import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.bus.query.QueryHandler;

@Service
public class FindProcessQueryHandler implements QueryHandler<ProcessFinderQuery,ProcessResponse> {

    private final ProcessFinder processFinder;

    public FindProcessQueryHandler(ProcessFinder processFinder) {
        this.processFinder = processFinder;
    }

    @Override
    public ProcessResponse handle(ProcessFinderQuery query) {
        return processFinder.findAll();
    }
}
