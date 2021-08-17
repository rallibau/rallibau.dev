package com.rallibau.bpm.process.domain;

import com.rallibau.shared.domain.criteria.Criteria;

import java.util.List;
import java.util.Optional;

public interface ProcessRepository {
    void save(Process cliente);

    Optional<Process> get(ProcessId id);

    List<Process> matching(Criteria criteria);

}
