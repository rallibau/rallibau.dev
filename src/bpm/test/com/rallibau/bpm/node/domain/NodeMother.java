package com.rallibau.bpm.node.domain;


public final class NodeMother {

    public static Node create(NodeId id, NodeName name, NodeType type){
        return Node.create(id,name,type);
    }

    public static Node random(){
        return Node.create(NodeIdMother.random(),NodeMameMother.random(),NodeTypeMother.random());
    }
}
