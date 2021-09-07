package com.rallibau.bpm.node.infrastructure.persistence.inMemory;

import com.rallibau.bpm.node.domain.Node;
import com.rallibau.bpm.node.domain.NodeId;
import com.rallibau.bpm.node.domain.NodeRepository;
import com.rallibau.shared.domain.criteria.Criteria;
import com.rallibau.shared.infraestructure.persistence.memory.MemoryRepository;

import java.util.List;
import java.util.Optional;

public class NodeRepositoryInMemoryImpl extends MemoryRepository<Node, NodeId> implements NodeRepository {

    public NodeRepositoryInMemoryImpl() {
        super(Node.class);
    }

    @Override
    public void save(Node node) {
       persist(node);
    }

    @Override
    public Optional<Node> get(NodeId id) {
        return byId(id);
    }


    @Override
    public List<Node> searchAll() {
        return all();
    }

    @Override
    public List<Node> matching(Criteria criteria) {
        return byCriteria(criteria);
    }
}
