package com.rallibau.bpm.process.application.find;

import com.rallibau.bpm.process.domain.Process;
import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.bus.query.QueryHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FindProcessQueryHandler implements QueryHandler<ProcessFindQuery, ProcessResponse> {

    private final ProcessFinder processFinder;

    public FindProcessQueryHandler(ProcessFinder processFinder) {
        this.processFinder = processFinder;
    }

    @Override
    public ProcessResponse handle(ProcessFindQuery query) {
        List<ProcessResponse> processResponseList = new ArrayList<>();
        ProcessesResponse processesResponse = new ProcessesResponse(processResponseList);
        Optional<Process> process = processFinder.find(query.getId());
        if(process.isPresent()){
            return new ProcessResponse(
                    process.get().id().value(),
                    process.get().name().value());
        }else{
            return new ProcessResponse();
        }
    }
}
