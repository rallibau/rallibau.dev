package com.rallibau.cms.page.application.create;

import com.rallibau.cms.page.domain.*;
import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.Utils;
import com.rallibau.shared.domain.bus.command.CommandHandler;

@Service
public class CreatePageCommandHandler implements CommandHandler<CreatePageCommand> {

    private final PageCreator pageCreator;

    public CreatePageCommandHandler(PageCreator pageCreator) {
        this.pageCreator = pageCreator;
    }


    @Override
    public void handle(CreatePageCommand command) {

        pageCreator.create(
                Page.create(
                        PageId.create(command.id()),
                        PageCreationDate.create(Utils.convertToDateViaInstant(command.creationDate())),
                        PageIdUser.create(command.idUser()),
                        PageTitle.create(command.title()),
                        PageBody.create(command.body())
                )
        );
    }
}
