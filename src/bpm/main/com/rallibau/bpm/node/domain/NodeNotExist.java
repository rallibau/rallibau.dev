package com.rallibau.bpm.node.domain;

import com.rallibau.bpm.process.domain.ProcessId;
import com.rallibau.shared.domain.DomainError;

public final class NodeNotExist extends DomainError {
    public NodeNotExist(ProcessId id) {
        super("node_not_exist", String.format("The node <%s> doesn't exist", id.value()));
    }
}
