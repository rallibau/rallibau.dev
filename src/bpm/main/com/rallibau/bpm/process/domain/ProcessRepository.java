package com.rallibau.bpm.process.domain;

import com.rallibau.bpm.node.domain.Node;
import com.rallibau.shared.domain.criteria.Criteria;

import java.util.List;
import java.util.Optional;

public interface ProcessRepository {
    void save(Process process);

    Optional<Process> get(ProcessId id);

    List<Process> searchAll();

    List<Process> matching(Criteria criteria);

}
