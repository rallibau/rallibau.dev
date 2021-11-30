package com.rallibau.cms.page.application.create;

import com.rallibau.shared.domain.Utils;
import com.rallibau.shared.domain.bus.command.Command;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashMap;

public class CreatePageCommand extends Command {
    private final String id;
    private final ZonedDateTime creationDate;
    private final String idUser;
    private final String title;
    private final String body;

    public CreatePageCommand(String id, ZonedDateTime creationDate, String idUser, String title, String body) {
        this.creationDate = creationDate;
        this.idUser = idUser;
        this.title = title;
        this.body = body;
        this.id = id;
    }

    public CreatePageCommand() {
        this.creationDate = null;
        this.idUser = null;
        this.title = null;
        this.body = null;
        this.id = null;
    }


    @Override
    public HashMap<String, Serializable> toPrimitives() {

        return new HashMap<>() {{
            put("creationDate", Utils.dateToString(creationDate));
            put("idUser", idUser);
            put("title", title);
            put("body", body);
        }};
    }

    @Override
    public CreatePageCommand fromPrimitives(String aggregateId, HashMap<String, Serializable> body, String eventId, String occurredOn) {
        return new CreatePageCommand(aggregateId,
                Utils.dateFromString((String) body.get("creationDate")),
                (String) body.get("idUser"),
                (String) body.get("title"),
                (String) body.get("body")
        );
    }

    public String id() {
        return id;
    }

    public ZonedDateTime creationDate() {
        return creationDate;
    }

    public String idUser() {
        return idUser;
    }

    public String title() {
        return title;
    }

    public String body() {
        return body;
    }
}
