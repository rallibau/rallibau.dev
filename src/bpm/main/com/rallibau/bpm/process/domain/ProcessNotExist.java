package com.rallibau.bpm.process.domain;

import com.rallibau.shared.domain.DomainError;

public final class ProcessNotExist extends DomainError {
    public ProcessNotExist(ProcessId id) {
        super("process_not_exist", String.format("The process <%s> doesn't exist", id.value()));
    }
}
