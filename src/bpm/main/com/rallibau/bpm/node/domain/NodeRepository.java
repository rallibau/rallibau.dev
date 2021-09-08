package com.rallibau.bpm.node.domain;

import com.rallibau.shared.domain.criteria.Criteria;

import java.util.List;
import java.util.Optional;

public interface NodeRepository {
    void save(Node node);

    Optional<Node> get(NodeId id);

    List<Node> searchAll();

    List<Node> matching(Criteria criteria);

}
