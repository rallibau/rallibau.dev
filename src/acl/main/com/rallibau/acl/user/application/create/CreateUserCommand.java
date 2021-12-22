package com.rallibau.acl.user.application.create;

import com.rallibau.shared.domain.bus.command.Command;

import java.io.Serializable;
import java.util.HashMap;

public class CreateUserCommand extends Command {
    private final String id;
    private final String userName;
    private final String password;


    public CreateUserCommand(String id, String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.id = id;
    }

    public CreateUserCommand() {
        this.userName = null;
        this.password = null;
        this.id = null;
    }


    @Override
    public HashMap<String, Serializable> toPrimitives() {

        return new HashMap<>() {{
            put("ud", id);
            put("userName", userName);
            put("password", password);
        }};
    }

    @Override
    public CreateUserCommand fromPrimitives(String aggregateId, HashMap<String, Serializable> body, String eventId, String occurredOn) {
        return new CreateUserCommand(aggregateId,
                (String) body.get("userName"),
                (String) body.get("password")
        );
    }

    public String id() {
        return id;
    }

    public String userName() {
        return userName;
    }

    public String password() {
        return password;
    }
}
