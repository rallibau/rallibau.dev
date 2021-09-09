package com.rallibau.bpm.process.application.find;

import com.rallibau.bpm.node.domain.NodeId;
import com.rallibau.bpm.process.domain.Process;
import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.bus.query.QueryHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        if (process.isPresent()) {
            return new ProcessResponse(
                    process.get().id().value(),
                    process.get().name().value(), process.get().nodes().stream().map(NodeId::value).collect(Collectors.toList()));
        } else {
            return new ProcessResponse();
        }
    }
}
