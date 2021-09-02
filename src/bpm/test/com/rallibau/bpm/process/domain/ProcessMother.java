package com.rallibau.bpm.process.domain;

import com.rallibau.bpm.node.domain.NodeId;
import com.rallibau.bpm.node.domain.NodeIdMother;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class ProcessMother {
    public static Process create(ProcessId id, ProcessName name, List<NodeId> nodes) {
        return Process.create(id, name, nodes);
    }

    public static Process random() {
        ArrayList<NodeId> nodesIds = new ArrayList<>();
        nodesIds.add(NodeIdMother.random());
        return Process.create(ProcessIdMohter.random(), ProcessNameMother.random(), nodesIds);
    }
}
