package com.rallibau.bpm.node.infrastructure.persistence.hibernate;

import com.rallibau.bpm.node.domain.Node;
import com.rallibau.bpm.node.domain.NodeId;
import com.rallibau.bpm.node.domain.NodeRepository;
import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.criteria.Criteria;
import com.rallibau.shared.infraestructure.persistence.hibernate.HibernateRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional("bpm-transaction_manager")
public  class NodeRepositoryImp extends HibernateRepository<Node,NodeId> implements NodeRepository {

    public NodeRepositoryImp(@Qualifier("bpm-session_factory")SessionFactory sessionFactory) {
        super(sessionFactory,Node.class);
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
