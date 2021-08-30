package com.rallibau.bpm.node.domain;

import com.rallibau.shared.domain.StringValueObject;

public class NodeType extends StringValueObject {


    public NodeType(NODE_TYPE value) {
        super(value.code);
    }
    public NodeType() {
        super("");
    }

    public static NodeType create(NODE_TYPE value) {
        return new NodeType(value);
    }


    public enum NODE_TYPE{
        START_EVENT("startEvent"),
        TASK("task");
        private String code;

        NODE_TYPE(String code) {
            this.code = code;
        }
    }
}
