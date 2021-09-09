package com.rallibau.bpm.process.application.find;

import com.rallibau.bpm.process.domain.Process;
import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.bus.query.QueryHandler;

import java.util.ArrayList;
import java.util.List;

@Service
public class FindProcessQueryHandler implements QueryHandler<ProcessFinderQuery, ProcessesResponse> {

    private final ProcessFinder processFinder;

    public FindProcessQueryHandler(ProcessFinder processFinder) {
        this.processFinder = processFinder;
    }

    @Override
    public ProcessesResponse handle(ProcessFinderQuery query) {
        List<ProcessResponse> processResponseList = new ArrayList<>();
        ProcessesResponse processesResponse = new ProcessesResponse(processResponseList);
        List<Process> processes = processFinder.findAll();
        processes.forEach(process -> processResponseList.add(new ProcessResponse(
                process.id().value(),
                process.name().value())));

        return processesResponse;
    }
}
