package com.rallibau.bpm.node.application.create;

import com.rallibau.bpm.node.domain.Node;
import com.rallibau.bpm.node.domain.NodeId;
import com.rallibau.bpm.node.domain.NodeName;
import com.rallibau.bpm.node.domain.NodeType;
import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.bus.command.CommandHandler;

@Service
public class CreateNodeCommandHandler implements CommandHandler<CreateNodeCommand> {

    private final NodeCreator nodeCreator;

    public CreateNodeCommandHandler(NodeCreator nodeCreator) {
        this.nodeCreator = nodeCreator;
    }


    @Override
    public void handle(CreateNodeCommand command) {

        nodeCreator.create(Node.create(new NodeId(command.id()),
                new NodeName(command.name()), new NodeType(NodeType.NODE_TYPE.valueOf(command.nodeType()))));
    }
}
