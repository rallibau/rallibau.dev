package com.rallibau.bpm.process.infrastructure.persistence.inMemory;

import com.rallibau.bpm.process.domain.Process;
import com.rallibau.bpm.process.domain.ProcessId;
import com.rallibau.bpm.process.domain.ProcessRepository;
import com.rallibau.shared.domain.criteria.Criteria;
import com.rallibau.shared.infraestructure.persistence.memory.MemoryRepository;

import java.util.List;
import java.util.Optional;

public class ProcessRepositoryInMemoryImpl extends MemoryRepository<Process, ProcessId> implements ProcessRepository {

    public ProcessRepositoryInMemoryImpl() {
        super(Process.class);
    }


    @Override
    public void save(Process process) {
        persist(process);
    }

    @Override
    public Optional<Process> get(ProcessId id) {
        return byId(id);
    }

    @Override
    public List<Process> searchAll() {
        return all();
    }

    @Override
    public List<Process> matching(Criteria criteria) {
        return byCriteria(criteria);
    }
}
