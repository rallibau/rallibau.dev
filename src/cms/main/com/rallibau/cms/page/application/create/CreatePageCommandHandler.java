package com.rallibau.cms.page.application.create;

import com.rallibau.cms.page.domain.*;
import com.rallibau.cms.user.application.find.UserCmsFinder;
import com.rallibau.cms.user.domain.User;
import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.Utils;
import com.rallibau.shared.domain.bus.command.CommandHandler;

@Service
public class CreatePageCommandHandler implements CommandHandler<CreatePageCommand> {

    private final PageCreator pageCreator;
    private final UserCmsFinder userCmsFinder;

    public CreatePageCommandHandler(PageCreator pageCreator, UserCmsFinder userCmsFinder) {
        this.pageCreator = pageCreator;
        this.userCmsFinder = userCmsFinder;
    }


    @Override
    public void handle(CreatePageCommand command) {
        User userCreator = userCmsFinder.find(command.idUser());

        pageCreator.create(
                Page.create(
                        PageId.create(command.id()),
                        PageCreationDate.create(Utils.convertToDateViaInstant(command.creationDate())),
                        userCreator,
                        PageTitle.create(command.title()),
                        PageBody.create(command.body())
                )
        );
    }
}
