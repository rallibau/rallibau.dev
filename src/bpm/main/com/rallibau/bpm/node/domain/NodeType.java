package com.rallibau.bpm.node.domain;

import com.rallibau.shared.domain.StringValueObject;

import java.util.*;

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


    public enum NODE_TYPE {

        START_EVENT("START_EVENT"),
        PARALLEL_GATEWAY("PARALLEL_GATEWAY"),
        TASK("TASK"),
        END_EVENT("END_EVENT"),
        EVENT_BASED_GATEWAY("EVENT_BASED_GATEWAY"),
        INTERMEDIATE_CATCH_EVENT("INTERMEDIATE_CATCH_EVENT");

        private String code;

        private static HashMap<String, Optional<NODE_TYPE>> values = new HashMap<String, Optional<NODE_TYPE>>() {{
            put("semantic:startEvent", Optional.of(NODE_TYPE.START_EVENT));
            put("semantic:parallelGateway",Optional.of(NODE_TYPE.PARALLEL_GATEWAY));
            put("semantic:intermediateCatchEvent",Optional.of(NODE_TYPE.INTERMEDIATE_CATCH_EVENT));
            put("semantic:task",Optional.of(NODE_TYPE.TASK));
            put("semantic:endEvent",Optional.of(NODE_TYPE.END_EVENT));
            put("semantic:eventBasedGateway",Optional.of(NODE_TYPE.EVENT_BASED_GATEWAY));
            put("semantic:intermediateCatchEvent",Optional.of(NODE_TYPE.INTERMEDIATE_CATCH_EVENT));
        }};

        NODE_TYPE(String code) {
            this.code = code;
        }

        private static final List<NODE_TYPE> VALUES =
                Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = VALUES.size();
        private static final Random RANDOM = new Random();

        public static NODE_TYPE random() {
            return VALUES.get(RANDOM.nextInt(SIZE));
        }

        public static boolean existNodeType(String code){
            return  NODE_TYPE.values.containsKey(code);
        }

        public static Optional<NODE_TYPE> valueByTagName(String code) {
            if (NODE_TYPE.values.containsKey(code)) {
                return NODE_TYPE.values.get(code);
            } else {
                return Optional.empty();
            }
        }
    }
}
